package de.viadee.cloud.clothing.domain.shirts;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public record Shirt(Type type, Color color, Size size, Collection collection,
                    LogoPosition logoPosition, LogoColor logoColor,
                    Optional<Name> name, Optional<Slogan> slogan) {

    public Shirt {
        Objects.requireNonNull(type);
        Objects.requireNonNull(color);
        Objects.requireNonNull(size);
        Objects.requireNonNull(collection);
        Objects.requireNonNull(logoPosition);
        Objects.requireNonNull(logoColor);
    }

    public boolean canBeCreated() {
        return hasPrintableNameOrNone() && hasValidSloganAndTypeCombinationOrNone() &&
                hasMatchingColors() && hasValidTypeAndCollectionAndLogoCombination();
    }


    public boolean hasMatchingColors() {
        // If shirt color is equal to logo color the colors are not matching
        return (!this.color.equals(Color.BLACK) || !this.logoColor.equals(LogoColor.BLACK)) &&
                (!this.color.equals(Color.WHITE) || !this.logoColor.equals(LogoColor.WHITE));
    }

    public boolean hasPrintableNameOrNone() {
        return (this.type.isTShirtOrPoloShirt() && (this.name.isEmpty() || this.name.get().length() <= 20)) ||
                (this.type.isSweatShirt() && this.name.isEmpty());
    }

    public boolean hasValidSloganAndTypeCombinationOrNone() {
        return (this.slogan.isPresent() && this.type.isTShirtOrPoloShirt()) || this.slogan.isEmpty();
    }


    public boolean hasValidTypeAndCollectionAndLogoCombination() {
        return hasValidClassicCombination() || hasValidSparkCombination() || hasValidVLineCombination();
    }

    private boolean hasValidSparkCombination() {
        return this.collection.isViadeeSpark() && this.type.isSweatShirt() &&
                Set.of(LogoPosition.FRONT, LogoPosition.BACK, LogoPosition.FRONT_AND_BACK).contains(this.logoPosition);
    }

    private boolean hasValidClassicCombination() {
        return this.collection.isViadeeClassic() &&
                Set.of(LogoPosition.FRONT, LogoPosition.BACK, LogoPosition.FRONT_AND_BACK).contains(this.logoPosition);
    }

    public boolean hasValidVLineCombination() {
        return this.type.isSweatShirt() && this.collection.isViadeeVLine() &&
                Set.of(LogoPosition.FRONT_AND_DOMAIN_ON_SLEEVE, LogoPosition.BACK_AND_DOMAIN_ON_BACK,
                        LogoPosition.FRONT_AND_BACK_AND_DOMAIN_ON_BACK).contains(this.logoPosition);
    }




    public static Shirt.Builder builder() {
        return new Shirt.Builder();
    }

    public static class Builder {

        Type type;

        Color color;

        Size size;

        LogoPosition logoPosition;

        LogoColor logoColor;

        Collection collection;

        Optional<Name> name = Optional.empty();

        Optional<Slogan> slogan = Optional.empty();


        public Shirt.Builder withType(Type type) {
            this.type = type;
            return this;
        }

        public Shirt.Builder withSize(Size size) {
            this.size = size;
            return this;
        }

        public Shirt.Builder withColor(Color color) {
            this.color = color;
            return this;
        }

        public Shirt.Builder withLogoPosition(LogoPosition logoPosition) {
            this.logoPosition = logoPosition;
            return this;
        }

        public Shirt.Builder withLogoColor(LogoColor logoColor) {
            this.logoColor = logoColor;
            return this;
        }

        public Shirt.Builder withCollection(Collection collection) {
            this.collection = collection;
            return this;
        }

        public Shirt.Builder withName(Name name) {
            this.name = Optional.of(name);
            return this;
        }

        public Shirt.Builder withSlogan(Slogan slogan) {
            this.slogan = Optional.of(slogan);
            return this;
        }

        public Shirt build() {
            return new Shirt(type, color, size, collection, logoPosition, logoColor, name, slogan);
        }
    }

}
