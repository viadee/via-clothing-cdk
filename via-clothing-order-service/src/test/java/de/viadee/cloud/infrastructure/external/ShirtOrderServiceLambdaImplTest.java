package de.viadee.cloud.infrastructure.external;


import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.viadee.cloud.clothing.infrastructure.external.ShirtOrderRequest;
import de.viadee.cloud.clothing.infrastructure.messasing.InvalidShirtOrderMessageSender;
import de.viadee.cloud.clothing.infrastructure.messasing.InvalidShirtOrderSqsMessageSenderImpl;
import de.viadee.cloud.clothing.infrastructure.persistence.ShirtOrderDynamoDbRepositoryImpl;
import de.viadee.cloud.clothing.infrastructure.persistence.ShirtOrderRepository;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class ShirtOrderServiceLambdaImplTest {

    @Inject
    ObjectMapper objectMapper;


    @BeforeAll
    static void setup() {
        ShirtOrderDynamoDbRepositoryImpl mockShirtOrderRepository = Mockito
                .mock(ShirtOrderDynamoDbRepositoryImpl.class);
        InvalidShirtOrderSqsMessageSenderImpl invalidShirtOrderMessageSender =
                Mockito.mock(InvalidShirtOrderSqsMessageSenderImpl.class);

        QuarkusMock.installMockForType(mockShirtOrderRepository, ShirtOrderRepository.class);
        QuarkusMock.installMockForType(invalidShirtOrderMessageSender, InvalidShirtOrderMessageSender.class);
    }


    @Test
    void testSuccessfulShirtOrder() throws Exception {

        ShirtOrderRequest shirtOrderRequest = new ShirtOrderRequest("T_SHIRT", "M",
                "GREY", "VIADEE_CLASSIC", "FRONT", "BLACK",
                "Matthias", "TEAM_ARCHITECT");

        SQSEvent.SQSMessage sqsMessage = new SQSEvent.SQSMessage();
        String messageBody = objectMapper.writeValueAsString(shirtOrderRequest);
        sqsMessage.setBody(messageBody);
        sqsMessage.setAttributes(new HashMap<>() {{
            put("SentTimestamp", String.valueOf(Instant.now().getEpochSecond()));
        }});

        SQSEvent sqsEvent = new SQSEvent();
        sqsEvent.setRecords(List.of(sqsMessage));


        given()
                .contentType("application/json")
                .accept("application/json")
                .body(sqsEvent)
                .when()
                .post()
                .then()
                .statusCode(200)
                .assertThat()
                .body("orderSuccessful", is(true));
    }
}
