package de.viadee.cloud.clothing.application.shirts;


public record VerifyShirtCombinationRequest(String type, String size, String color, String collection,
                                            String logoPosition, String logoColor,
                                            String name, String slogan) {
}
