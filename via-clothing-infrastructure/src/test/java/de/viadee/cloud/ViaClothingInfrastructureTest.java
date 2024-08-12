package de.viadee.cloud;

import software.amazon.awscdk.App;
import software.amazon.awscdk.assertions.Template;

import java.io.IOException;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class ViaClothingInfrastructureTest {

    @Test
    public void testStack() {
        App app = new App();
        ViaClothingInfrastructureStack stack = new ViaClothingInfrastructureStack(app, "test");

        Template template = Template.fromStack(stack);

        template.findResources("AWS::ApiGateway::RestApi");

        template.findResources("AWS::SQS::Queue");

        template.hasResourceProperties("AWS::DynamoDB::Table", new HashMap<String, String>() {{
            put("TableName", "ClothingOrders");
        }});

        template.hasResourceProperties("AWS::Lambda::Function", new HashMap<String, String>() {{
            put("FunctionName", "ClothingOrderService");
        }});
    }
}
