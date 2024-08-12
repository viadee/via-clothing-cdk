package de.viadee.cloud;

import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.ecr.IRepository;
import software.amazon.awscdk.services.ecr.Repository;
import software.amazon.awscdk.services.events.targets.LambdaFunction;
import software.amazon.awscdk.services.iam.Effect;
import software.amazon.awscdk.services.iam.PolicyStatement;
import software.amazon.awscdk.services.lambda.*;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.lambda.eventsources.SqsEventSource;
import software.amazon.awscdk.services.sqs.IQueue;
import software.amazon.awsconstructs.services.apigatewaysqs.ApiGatewayToSqs;
import software.constructs.Construct;

import java.util.HashMap;
import java.util.List;

public class ViaClothingInfrastructureStack extends Stack {

    public static final String CLOTHING_ORDER_FUNCTION_NAME = "ClothingOrderService";

    public static final String SHIRT_ORDER_DYNAMODB_TABLE_NAME = "ClothingOrders";
    public static final String SHIRT_ORDER_TABLE_PARTITION_KEY_NAME = "orderId";

    public ViaClothingInfrastructureStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public ViaClothingInfrastructureStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        final Attribute partitionKey = Attribute.builder()
                .name(SHIRT_ORDER_TABLE_PARTITION_KEY_NAME)
                .type(AttributeType.STRING)
                .build();

        final Table viaClothingTable = Table.Builder.create(this, "ViaClothingOrderTable")
                .tableName(SHIRT_ORDER_DYNAMODB_TABLE_NAME)
                .partitionKey(partitionKey)
                .removalPolicy(RemovalPolicy.DESTROY)
                .build();

        final ApiGatewayToSqs viaClothingApiGatewayToSqs = ApiGatewayToSqs.Builder
                .create(this, "ViaClothingApiGateway")
                .allowCreateOperation(true)
                .allowDeleteOperation(true)
                .allowReadOperation(false)
                .deployDeadLetterQueue(true)
                .build();

        final IQueue viaClothingOrderDlq = viaClothingApiGatewayToSqs.getDeadLetterQueue().getQueue();

        final IRepository viaClothingOrderServiceImage = Repository
                .fromRepositoryName(this, "ViaClothingOrderServiceImage", "via-clothing-order-service");

        final Function viaClothingOrderServiceFunction = Function.Builder.create(this, "ViaClothingOrderServiceFunction")
                .functionName(CLOTHING_ORDER_FUNCTION_NAME)
                .code(Code.fromEcrImage(viaClothingOrderServiceImage))
                .handler(Handler.FROM_IMAGE)
                .runtime(Runtime.FROM_IMAGE)
                .environment(new HashMap<>() {{
                    put("DYNAMODB_TABLE_NAME", viaClothingTable.getTableName());
                    put("SQS_DLQ_NAME", viaClothingOrderDlq.getQueueUrl());
                }})
                .build();

        SqsEventSource sqsEventSource = new SqsEventSource(viaClothingApiGatewayToSqs.getSqsQueue());
        viaClothingOrderServiceFunction.addEventSource(sqsEventSource);

        final LambdaFunction lambdaFunction = LambdaFunction.Builder
                .create(viaClothingOrderServiceFunction)
                .build();

        final PolicyStatement dynamoDbPutItemPolicy = PolicyStatement.Builder.create()
                .effect(Effect.ALLOW)
                .actions(List.of("dynamodb:PutItem"))
                .resources(List.of(viaClothingTable.getTableArn()))
                .build();

        final PolicyStatement sqsSendMessageToDlqPolicy = PolicyStatement.Builder.create()
                .effect(Effect.ALLOW)
                .actions(List.of("sqs:SendMessage"))
                .resources(List.of(viaClothingOrderDlq.getQueueArn()))
                .build();

        viaClothingOrderServiceFunction.addToRolePolicy(dynamoDbPutItemPolicy);
        viaClothingOrderServiceFunction.addToRolePolicy(sqsSendMessageToDlqPolicy);

    }
}
