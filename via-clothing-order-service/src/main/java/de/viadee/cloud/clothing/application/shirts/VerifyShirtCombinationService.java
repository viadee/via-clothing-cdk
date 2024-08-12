package de.viadee.cloud.clothing.application.shirts;

import de.viadee.cloud.clothing.domain.shirts.*;

public class VerifyShirtCombinationService {

    public VerifyShirtCombinationResponse verifyShirtIsCreatable(VerifyShirtCombinationRequest verifyShirtCombinationRequest)
            throws ShirtNotCreatableException {

        Shirt.Builder shirtBuilder = Shirt.builder();

        try {
            shirtBuilder
                    .withType(Type.valueOf(verifyShirtCombinationRequest.type()))
                    .withSize(Size.valueOf(verifyShirtCombinationRequest.size()))
                    .withColor(Color.valueOf(verifyShirtCombinationRequest.color()))
                    .withLogoPosition(LogoPosition.valueOf(verifyShirtCombinationRequest.logoPosition()))
                    .withLogoColor(LogoColor.valueOf(verifyShirtCombinationRequest.logoColor()))
                    .withCollection(Collection.valueOf(verifyShirtCombinationRequest.collection()));

            String name = verifyShirtCombinationRequest.name();
            if (name != null && !name.isEmpty()) {
                shirtBuilder.withName(Name.of(name));
            }

            String slogan = verifyShirtCombinationRequest.slogan();
            if (slogan != null) {
                shirtBuilder.withSlogan(Slogan.valueOf(slogan));
            }

        } catch (IllegalArgumentException e) {
            throw new ShirtNotCreatableException(e.getMessage());
        }


        Shirt shirt = shirtBuilder.build();

        return shirt.canBeCreated() ?
                VerifyShirtCombinationResponse.isCreatableResponse() :
                VerifyShirtCombinationResponse.isNotCreatableResponse(shirt);
    }
}

