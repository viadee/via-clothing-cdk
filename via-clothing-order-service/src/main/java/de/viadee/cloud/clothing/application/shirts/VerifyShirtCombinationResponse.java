package de.viadee.cloud.clothing.application.shirts;

import de.viadee.cloud.clothing.domain.shirts.Shirt;

import java.util.Objects;
import java.util.Optional;

public record VerifyShirtCombinationResponse(Boolean isShirtCreatable, Optional<String> notCreatableReason) {

    public VerifyShirtCombinationResponse {
        Objects.requireNonNull(isShirtCreatable);
    }

    private VerifyShirtCombinationResponse() {
        this(true, Optional.empty());
    }

    private VerifyShirtCombinationResponse(String notCreatableReason) {
        this(false, Optional.of(notCreatableReason));
    }

    public static VerifyShirtCombinationResponse isCreatableResponse() {
        return new VerifyShirtCombinationResponse();
    }

    public static VerifyShirtCombinationResponse isNotCreatableResponse(Shirt shirt) {
        return new VerifyShirtCombinationResponse(determineShirtNotCreatableReason(shirt));
    }

    private static String determineShirtNotCreatableReason(Shirt shirt) {
        if (shirt.canBeCreated()) {
            throw new IllegalArgumentException("Shirt is actually creatable");
        }

        return !shirt.hasMatchingColors() ? "Shirt does not have matching colors" :
                !shirt.hasPrintableNameOrNone() ? "Shirt does not have printable name" :
                        !shirt.hasValidTypeAndCollectionAndLogoCombination() ? "Shirt does not have a valid logo and collection combination" :
                                !shirt.hasValidSloganAndTypeCombinationOrNone() ? "Shirt does not have a valid slogan and type combination" :
                                        "Shirt is not creatable for an unknown reason";
    }

}
