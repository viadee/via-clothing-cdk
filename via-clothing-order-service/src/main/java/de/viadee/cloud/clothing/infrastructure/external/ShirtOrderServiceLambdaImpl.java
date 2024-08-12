package de.viadee.cloud.clothing.infrastructure.external;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.viadee.cloud.clothing.application.shirts.ShirtNotCreatableException;
import de.viadee.cloud.clothing.application.shirts.VerifyShirtCombinationResponse;
import de.viadee.cloud.clothing.application.shirts.VerifyShirtCombinationService;
import de.viadee.cloud.clothing.infrastructure.messasing.InvalidShirtOrderMessage;
import de.viadee.cloud.clothing.infrastructure.messasing.InvalidShirtOrderMessageNotSendableException;
import de.viadee.cloud.clothing.infrastructure.messasing.InvalidShirtOrderMessageSender;
import de.viadee.cloud.clothing.infrastructure.persistence.ShirtOrder;
import de.viadee.cloud.clothing.infrastructure.persistence.ShirtOrderRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.time.Instant;

@ApplicationScoped
public class ShirtOrderServiceLambdaImpl implements ShirtOrderService, RequestHandler<SQSEvent, ShirtOrderResponse> {

    private static final Logger LOG = Logger.getLogger(ShirtOrderServiceLambdaImpl.class);
    private static final String SENT_TIMESTAMP_ATTRIBUTE = "SentTimestamp";
    final ObjectMapper objectMapper;

    final VerifyShirtCombinationService verifyShirtOrderService;

    final ShirtOrderRepository shirtOrderRepository;

    final InvalidShirtOrderMessageSender invalidShirtOrderMessageSender;


    @Inject
    public ShirtOrderServiceLambdaImpl(ObjectMapper objectMapper,
                                       VerifyShirtCombinationService verifyShirtOrderService,
                                       ShirtOrderRepository shirtOrderRepository,
                                       InvalidShirtOrderMessageSender invalidShirtOrderMessageSender) {
        this.objectMapper = objectMapper;
        this.verifyShirtOrderService = verifyShirtOrderService;
        this.shirtOrderRepository = shirtOrderRepository;
        this.invalidShirtOrderMessageSender = invalidShirtOrderMessageSender;
    }

    @Override
    public ShirtOrderResponse handleRequest(SQSEvent sqsEvent, Context context) {
        try {
            SQSEvent.SQSMessage record = sqsEvent.getRecords().getFirst();
            ShirtOrderRequest shirtOrderRequest = this.readShirtOrderRequestFrom(record.getBody());
            VerifyShirtCombinationResponse verifyShirtCombinationResponse = this.verifyShirtOrderService
                    .verifyShirtIsCreatable(shirtOrderRequest.createVerifyShirtCombinationRequest());

            if (!verifyShirtCombinationResponse.isShirtCreatable()) {
                this.sendInvalidShirtOrderMessage(InvalidShirtOrderMessage.of(verifyShirtCombinationResponse));
                return ShirtOrderResponse.failed(shirtOrderRequest);
            }

            ShirtOrder shirtOrder = shirtOrderRequest.createShirtOrder();
            shirtOrder.setId(record.getMessageId());
            shirtOrder.setOrderTimestamp(determineOrderTimestampOf(record));

            this.persistValidShirtOrder(shirtOrder);
            return ShirtOrderResponse.succeed(shirtOrderRequest);

        } catch (InvalidShirtOrderException e) {
            this.sendInvalidShirtOrderMessage(InvalidShirtOrderMessage.of(e));
        } catch (ShirtNotCreatableException e) {
            this.sendInvalidShirtOrderMessage(InvalidShirtOrderMessage.of(e));
        }

        return ShirtOrderResponse.failed();
    }

    @Override
    public ShirtOrderRequest readShirtOrderRequestFrom(String body) throws InvalidShirtOrderException {
        try {
            return this.objectMapper.readValue(body, ShirtOrderRequest.class);
        } catch (JsonProcessingException e) {
            throw new InvalidShirtOrderException(e.getMessage());
        }
    }

    @Override
    public void sendInvalidShirtOrderMessage(InvalidShirtOrderMessage invalidShirtOrderMessage) {
        try {
            this.invalidShirtOrderMessageSender.sendInvalidOrderMessage(invalidShirtOrderMessage);
        } catch (InvalidShirtOrderMessageNotSendableException e) {
            LOG.fatal("Could not send message to dead letter queue of invalid shirt orders", e);
        }
    }

    @Override
    public void persistValidShirtOrder(ShirtOrder shirtOrder) {
        this.shirtOrderRepository.persist(shirtOrder);
    }


    private String determineOrderTimestampOf(SQSEvent.SQSMessage sqsMessage) {
        String sentTimestampAttributeValue = sqsMessage.getAttributes()
                .get(SENT_TIMESTAMP_ATTRIBUTE);
        if (sentTimestampAttributeValue != null) {
            return sentTimestampAttributeValue;
        } else {
            LOG.fatal("Order does not include attribute " + SENT_TIMESTAMP_ATTRIBUTE + ". " +
                    "The current timestamp will be used");
            return String.valueOf(Instant.now().getEpochSecond());
        }
    }
}
