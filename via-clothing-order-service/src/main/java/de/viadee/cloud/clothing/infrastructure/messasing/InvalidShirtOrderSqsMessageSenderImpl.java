package de.viadee.cloud.clothing.infrastructure.messasing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@ApplicationScoped
public class InvalidShirtOrderSqsMessageSenderImpl implements InvalidShirtOrderMessageSender {

    @ConfigProperty(name = "clothing.orders.invalid.order.dlq.url")
    String deadLetterQueueUrl;
    final ObjectMapper objectMapper;
    final SqsAsyncClient sqsAsyncClient;

    @Inject
    public InvalidShirtOrderSqsMessageSenderImpl(SqsAsyncClient sqsAsyncClient,
                                                 ObjectMapper objectMapper) {
        this.sqsAsyncClient = sqsAsyncClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendInvalidOrderMessage(InvalidShirtOrderMessage invalidShirtOrderMessage)
            throws InvalidShirtOrderMessageNotSendableException {
        try {
            final String message = objectMapper.writeValueAsString(invalidShirtOrderMessage);
            sqsAsyncClient.sendMessage(m -> m.queueUrl(deadLetterQueueUrl).messageBody(message));
        } catch (JsonProcessingException e) {
            throw new InvalidShirtOrderMessageNotSendableException(e.getMessage());
        }
    }
}
