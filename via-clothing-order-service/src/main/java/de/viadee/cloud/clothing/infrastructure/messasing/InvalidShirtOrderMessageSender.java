package de.viadee.cloud.clothing.infrastructure.messasing;

public interface InvalidShirtOrderMessageSender {
    void sendInvalidOrderMessage(InvalidShirtOrderMessage message) throws InvalidShirtOrderMessageNotSendableException;
}
