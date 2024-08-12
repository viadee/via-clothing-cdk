package de.viadee.cloud.clothing.infrastructure.external;

import de.viadee.cloud.clothing.application.shirts.VerifyShirtCombinationRequest;
import de.viadee.cloud.clothing.infrastructure.persistence.ShirtOrder;

import java.time.Instant;

public record ShirtOrderRequest(String type, String size, String color, String collection, String logoPosition,
                                String logoColor, String name, String slogan) {

    public VerifyShirtCombinationRequest createVerifyShirtCombinationRequest() {
        return new VerifyShirtCombinationRequest(type, size, color, collection, logoPosition, logoColor, name, slogan);
    }

    public ShirtOrder createShirtOrder() {
        return new ShirtOrder(type, size, color, collection, logoPosition, logoColor, name, slogan);
    }
}
