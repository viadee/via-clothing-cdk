package de.viadee.cloud.clothing.domain.shirts;

public enum Slogan {
    
    TEAM_ARCHITECT("Mannschaftsarchitekt"), INTEGRATION_ARTIST("Integrationskünstler"),
    SYSTEM_THERAPIST("Systemtherapeut"), SOFTWARE_TAILOR("Massschneider");

    private final String slogan;

    Slogan(String slogan) {
        this.slogan = slogan;
    }

    public String getSlogan() {
        return slogan;
    }
}
