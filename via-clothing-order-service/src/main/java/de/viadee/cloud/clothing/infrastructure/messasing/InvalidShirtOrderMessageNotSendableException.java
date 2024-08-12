package de.viadee.cloud.clothing.infrastructure.messasing;

public class InvalidShirtOrderMessageNotSendableException extends Exception {

    public InvalidShirtOrderMessageNotSendableException(String message) {
        super(message);
    }
}
