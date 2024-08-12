package de.viadee.cloud.clothing.domain.shirts;

public class Name {

    final String name;


    private Name(final String name) {
        this.name = name;
    }

    public Integer length() {
        return this.name.length();
    }

    public static Name of(String name) {
        return new Name(name);
    }
}
