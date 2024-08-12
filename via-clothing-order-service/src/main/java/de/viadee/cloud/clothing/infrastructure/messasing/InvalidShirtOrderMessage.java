package de.viadee.cloud.clothing.infrastructure.messasing;

import de.viadee.cloud.clothing.application.shirts.ShirtNotCreatableException;
import de.viadee.cloud.clothing.application.shirts.VerifyShirtCombinationResponse;
import de.viadee.cloud.clothing.infrastructure.external.InvalidShirtOrderException;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record InvalidShirtOrderMessage(String notCreatableReason) {

    private InvalidShirtOrderMessage() {
        this("");
    }

    public InvalidShirtOrderMessage(String notCreatableReason) {
        this.notCreatableReason = notCreatableReason;
    }

    public static InvalidShirtOrderMessage of(ShirtNotCreatableException shirtNotCreatableException) {
            return new InvalidShirtOrderMessage(shirtNotCreatableException.getReason());
    }

    public static InvalidShirtOrderMessage of(InvalidShirtOrderException shirtNotCreatableException) {
        return new InvalidShirtOrderMessage(shirtNotCreatableException.getMessage());
    }

    public static InvalidShirtOrderMessage of(VerifyShirtCombinationResponse verifyShirtCombinationResponse) {
        if (verifyShirtCombinationResponse.isShirtCreatable() &&
                verifyShirtCombinationResponse.notCreatableReason().isPresent()) {
            return new InvalidShirtOrderMessage(verifyShirtCombinationResponse.notCreatableReason().get());
        }
        throw new IllegalArgumentException();
    }
}
