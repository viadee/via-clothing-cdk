package de.viadee.cloud.clothing.infrastructure.external;

import java.util.Optional;

public record ShirtOrderResponse(Boolean orderSuccessful, Optional<ShirtOrderRequest> shirtOrderRequest) {

    public static ShirtOrderResponse succeed(ShirtOrderRequest shirtOrderRequest) {
        return new ShirtOrderResponse(Boolean.TRUE, Optional.of(shirtOrderRequest));
    }

    public static ShirtOrderResponse failed() {
        return new ShirtOrderResponse(Boolean.FALSE, Optional.empty());
    }

    public static ShirtOrderResponse failed(ShirtOrderRequest shirtOrderRequest) {
        return new ShirtOrderResponse(Boolean.FALSE, Optional.of(shirtOrderRequest));
    }
}
