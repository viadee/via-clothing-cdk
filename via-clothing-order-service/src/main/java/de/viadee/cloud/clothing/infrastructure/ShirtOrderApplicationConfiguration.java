package de.viadee.cloud.clothing.infrastructure;

import de.viadee.cloud.clothing.application.shirts.VerifyShirtCombinationService;
import jakarta.enterprise.context.ApplicationScoped;

public class ShirtOrderApplicationConfiguration {

    @ApplicationScoped
    public VerifyShirtCombinationService verifyShirtOrderService() {
        return new VerifyShirtCombinationService();
    }

}
