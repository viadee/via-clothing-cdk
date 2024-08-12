package de.viadee.cloud.clothing.domain.shirts;

public enum Collection {

    VIADEE_CLASSIC, VIADEE_SPARK, VIADEE_V_LINE;

    boolean isViadeeClassic() {
        return this.equals(VIADEE_CLASSIC);
    }

    boolean isViadeeSpark() {
        return this.equals(VIADEE_SPARK);
    }

    boolean isViadeeVLine() {
        return this.equals(VIADEE_V_LINE);
    }
}
