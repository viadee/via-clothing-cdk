package de.viadee.cloud.clothing.infrastructure.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ShirtOrderDynamoDbRepositoryImpl implements ShirtOrderRepository {

    private static final String ORDER_ID_COL = "orderId";
    private static final String ORDER_TIMESTAMP_COL = "orderTimestamp";
    private static final String SHIRTS_TYPE_COL = "shirtType";
    private static final String SHIRTS_SIZE_COL = "shirtSize";
    private static final String SHIRTS_COLOR_COL = "shirtColor";
    private static final String SHIRTS_COLLECTION_COL = "shirtCollection";
    private static final String SHIRTS_LOGO_POSITION_COL = "shirtLogoPosition";
    private static final String SHIRTS_LOGO_COLOR_COL = "shirtLogoColor";
    private static final String SLOGAN_ON_NAME_COL = "nameOnShirt";
    private static final String SLOGAN_ON_SHIRT_COL = "sloganOnShirt";

    DynamoDbClient dynamoDbClient;

    @ConfigProperty(name = "clothing.orders.shirts.dynamodb.table.name")
    String dynamoDbTableName;

    @Inject
    public ShirtOrderDynamoDbRepositoryImpl(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public void persist(ShirtOrder shirtOrder) {
        this.dynamoDbClient.putItem(putRequest(shirtOrder));
    }

    protected PutItemRequest putRequest(ShirtOrder shirtOrder) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(ORDER_ID_COL, AttributeValue.builder().s(shirtOrder.id).build());
        item.put(ORDER_TIMESTAMP_COL, AttributeValue.builder().n(shirtOrder.orderTimestamp).build());
        item.put(SHIRTS_TYPE_COL, AttributeValue.builder().s(shirtOrder.type).build());
        item.put(SHIRTS_SIZE_COL, AttributeValue.builder().s(shirtOrder.size).build());
        item.put(SHIRTS_COLOR_COL, AttributeValue.builder().s(shirtOrder.color).build());
        item.put(SHIRTS_LOGO_POSITION_COL, AttributeValue.builder().s(shirtOrder.logoPosition).build());
        item.put(SHIRTS_LOGO_COLOR_COL, AttributeValue.builder().s(shirtOrder.logoColor).build());
        item.put(SHIRTS_COLLECTION_COL, AttributeValue.builder().s(shirtOrder.collection).build());

        if (shirtOrder.getName() != null) {
            item.put(SLOGAN_ON_NAME_COL, AttributeValue.builder().s(shirtOrder.getName()).build());
        }

        if (shirtOrder.getSlogan() != null) {
            item.put(SLOGAN_ON_SHIRT_COL, AttributeValue.builder().s(shirtOrder.getSlogan()).build());
        }

        return PutItemRequest.builder()
                .tableName(dynamoDbTableName)
                .item(item)
                .build();
    }
}
