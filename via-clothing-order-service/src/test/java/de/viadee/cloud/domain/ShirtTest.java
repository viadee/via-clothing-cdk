package de.viadee.cloud.domain;

import de.viadee.cloud.clothing.domain.shirts.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShirtTest {

    @Test
    void testShirtBuilderSucceeds() {
        Shirt.Builder builder = Shirt.builder();
        builder.withType(Type.T_SHIRT);
        builder.withSize(Size.M);
        builder.withColor(Color.BLACK);
        builder.withCollection(Collection.VIADEE_CLASSIC);
        builder.withLogoPosition(LogoPosition.FRONT);
        builder.withLogoColor(LogoColor.BLACK);
        builder.withName(Name.of("John Doe"));
        builder.withSlogan(Slogan.TEAM_ARCHITECT);

        Shirt shirt = builder.build();
        assertThat(shirt, notNullValue());
    }

    @Test
    void testShirtBuilderSucceedsWithoutName() {
        Shirt.Builder builder = Shirt.builder();
        builder.withType(Type.T_SHIRT);
        builder.withSize(Size.M);
        builder.withColor(Color.BLACK);
        builder.withCollection(Collection.VIADEE_CLASSIC);
        builder.withLogoPosition(LogoPosition.FRONT);
        builder.withLogoColor(LogoColor.BLACK);
        builder.withSlogan(Slogan.TEAM_ARCHITECT);

        Shirt shirt = builder.build();
        assertThat(shirt, notNullValue());
    }

    @Test
    void testShirtBuilderSucceedsWithoutSlogan() {
        Shirt.Builder builder = Shirt.builder();
        builder.withType(Type.T_SHIRT);
        builder.withSize(Size.M);
        builder.withColor(Color.BLACK);
        builder.withCollection(Collection.VIADEE_CLASSIC);
        builder.withLogoPosition(LogoPosition.FRONT);
        builder.withLogoColor(LogoColor.BLACK);
        builder.withName(Name.of("John Doe"));

        Shirt shirt = builder.build();
        assertThat(shirt, notNullValue());
    }

    @Test
    void testShirtBuilderSucceedsWithoutNameAndSlogan() {
        Shirt.Builder builder = Shirt.builder();
        builder.withType(Type.T_SHIRT);
        builder.withSize(Size.M);
        builder.withColor(Color.BLACK);
        builder.withCollection(Collection.VIADEE_CLASSIC);
        builder.withLogoPosition(LogoPosition.FRONT);
        builder.withLogoColor(LogoColor.BLACK);

        Shirt shirt = builder.build();
        assertThat(shirt, notNullValue());
    }

    @Test
    void testShirtBuilderFailsWithoutType() {
        Shirt.Builder builder = Shirt.builder();
        builder.withSize(Size.M);
        builder.withColor(Color.BLACK);
        builder.withCollection(Collection.VIADEE_CLASSIC);
        builder.withLogoPosition(LogoPosition.FRONT);
        builder.withLogoColor(LogoColor.BLACK);
        builder.withName(Name.of("John Doe"));
        builder.withSlogan(Slogan.TEAM_ARCHITECT);

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    void testShirtBuilderFailsWithoutSize() {
        Shirt.Builder builder = Shirt.builder();
        builder.withType(Type.T_SHIRT);
        builder.withColor(Color.BLACK);
        builder.withCollection(Collection.VIADEE_CLASSIC);
        builder.withLogoPosition(LogoPosition.FRONT);
        builder.withLogoColor(LogoColor.BLACK);
        builder.withName(Name.of("John Doe"));
        builder.withSlogan(Slogan.TEAM_ARCHITECT);

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    void testShirtBuilderFailsWithoutColor() {
        Shirt.Builder builder = Shirt.builder();
        builder.withType(Type.T_SHIRT);
        builder.withSize(Size.M);
        builder.withCollection(Collection.VIADEE_CLASSIC);
        builder.withLogoPosition(LogoPosition.FRONT);
        builder.withLogoColor(LogoColor.BLACK);
        builder.withName(Name.of("John Doe"));
        builder.withSlogan(Slogan.TEAM_ARCHITECT);

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    void testShirtBuilderFailsWithoutCollection() {
        Shirt.Builder builder = Shirt.builder();
        builder.withType(Type.T_SHIRT);
        builder.withSize(Size.M);
        builder.withColor(Color.BLACK);
        builder.withLogoPosition(LogoPosition.FRONT);
        builder.withLogoColor(LogoColor.BLACK);
        builder.withName(Name.of("John Doe"));
        builder.withSlogan(Slogan.TEAM_ARCHITECT);

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    void testShirtBuilderFailsWithoutLogoPosition() {
        Shirt.Builder builder = Shirt.builder();
        builder.withType(Type.T_SHIRT);
        builder.withSize(Size.M);
        builder.withColor(Color.BLACK);
        builder.withCollection(Collection.VIADEE_CLASSIC);
        builder.withLogoColor(LogoColor.BLACK);
        builder.withName(Name.of("John Doe"));
        builder.withSlogan(Slogan.TEAM_ARCHITECT);

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    void testShirtBuilderFailsWithoutLogoColor() {
        Shirt.Builder builder = Shirt.builder();
        builder.withType(Type.T_SHIRT);
        builder.withSize(Size.M);
        builder.withColor(Color.BLACK);
        builder.withCollection(Collection.VIADEE_CLASSIC);
        builder.withLogoPosition(LogoPosition.FRONT);
        builder.withName(Name.of("John Doe"));
        builder.withSlogan(Slogan.TEAM_ARCHITECT);

        assertThrows(NullPointerException.class, builder::build);
    }


}
