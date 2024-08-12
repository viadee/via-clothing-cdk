package de.viadee.cloud.clothing.infrastructure.external;

import de.viadee.cloud.clothing.application.shirts.ShirtNotCreatableException;
import de.viadee.cloud.clothing.application.shirts.VerifyShirtCombinationResponse;

public class InvalidShirtOrderException extends Exception {

    public InvalidShirtOrderException(String message) {
        super(message);
    }
    public InvalidShirtOrderException(ShirtNotCreatableException shirtNotCreatableException) {
        this(shirtNotCreatableException.getReason());
    }

    public InvalidShirtOrderException(VerifyShirtCombinationResponse verifyShirtCombinationResponse) {
        super(verifyShirtCombinationResponse.notCreatableReason().isPresent() ? verifyShirtCombinationResponse.notCreatableReason().get() : "");
    }


}
