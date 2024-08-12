package de.viadee.cloud.clothing.domain.shirts;

public enum Type {

    T_SHIRT, POLO_SHIRT, SWEAT_SHIRT;

    boolean isTShirtOrPoloShirt() {
        return this.equals(T_SHIRT) || this.equals(POLO_SHIRT);
    }

    boolean isSweatShirt() {
        return this.equals(SWEAT_SHIRT);
    }
}
