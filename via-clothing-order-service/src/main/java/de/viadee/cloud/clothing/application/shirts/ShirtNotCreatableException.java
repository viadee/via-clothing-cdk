package de.viadee.cloud.clothing.application.shirts;

public class ShirtNotCreatableException extends Exception {

    public ShirtNotCreatableException(String reason) {
        super(reason);
    }

    public String getReason() {
        return this.getMessage();
    }
}
