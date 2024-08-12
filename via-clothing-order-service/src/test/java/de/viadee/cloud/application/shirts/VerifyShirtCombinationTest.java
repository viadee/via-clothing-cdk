package de.viadee.cloud.application.shirts;

import de.viadee.cloud.clothing.application.shirts.ShirtNotCreatableException;
import de.viadee.cloud.clothing.application.shirts.VerifyShirtCombinationRequest;
import de.viadee.cloud.clothing.application.shirts.VerifyShirtCombinationResponse;
import de.viadee.cloud.clothing.application.shirts.VerifyShirtCombinationService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VerifyShirtCombinationTest {

    final VerifyShirtCombinationService underTest = new VerifyShirtCombinationService();

    @Test
    void testClassicTShirtIsCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "XL", "WHITE", "VIADEE_CLASSIC",
                        "FRONT", "BLACK", null, null);

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(true));
    }

    @Test
    void testClassicPoloShirtIsCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("POLO_SHIRT", "L", "WHITE", "VIADEE_CLASSIC",
                        "FRONT", "BLACK", null, null);

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(true));
    }

    @Test
    void testClassicSweatShirtIsCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("SWEAT_SHIRT", "S", "WHITE", "VIADEE_CLASSIC",
                        "FRONT", "BLACK", null, null);

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(true));
    }

    @Test
    void testSparkSweatShirtIsCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("SWEAT_SHIRT", "S", "BLACK", "VIADEE_SPARK",
                        "FRONT", "WHITE", null, null);

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(true));
    }

    @Test
    void testVLineSweatShirtIsCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("SWEAT_SHIRT", "S", "BLACK", "VIADEE_V_LINE",
                        "BACK_AND_DOMAIN_ON_BACK", "WHITE", null, null);

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(true));
    }

    @Test
    void testVLineSweatShirtWithLogoOnFrontIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("SWEAT_SHIRT", "S", "BLACK", "VIADEE_V_LINE",
                        "FRONT", "WHITE", null, null);

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(false));
    }

    @Test
    void testVLineSweatShirtWithLogoOnBACKIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("SWEAT_SHIRT", "S", "GREY", "VIADEE_V_LINE",
                        "BACK", "WHITE", null, null);

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(false));
    }

    @Test
    void testClassicTShirtWithSloganIsCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "M", "WHITE", "VIADEE_CLASSIC",
                        "FRONT", "BLACK", null, "TEAM_ARCHITECT");

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(true));
    }

    @Test
    void testClassicShirtWithNameIsCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "M", "WHITE", "VIADEE_CLASSIC",
                        "FRONT", "BLACK", "John Doe", null);

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(true));
    }

    @Test
    void testClassicShirtWithNameAndSloganIsCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "M", "WHITE", "VIADEE_CLASSIC",
                        "FRONT", "BLACK", "John Doe", "TEAM_ARCHITECT");

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(true));
    }

    @Test
    void testClassicPoloShirtWithNameAndSloganIsCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("POLO_SHIRT", "M", "GREY", "VIADEE_CLASSIC",
                        "FRONT", "BLACK", "John Doe", "SOFTWARE_TAILOR");

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(true));
    }

    @Test
    void testClassicPoloShirtWithNameIsCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("POLO_SHIRT", "M", "GREY", "VIADEE_CLASSIC",
                        "FRONT", "BLACK", "John Doe", null);

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(true));
    }

    @Test
    void testClassicPoloShirtWithSloganIsCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("POLO_SHIRT", "M", "GREY", "VIADEE_CLASSIC",
                        "FRONT", "BLACK", null, "INTEGRATION_ARTIST");

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(true));
    }


    @Test
    void testClassicTShirtWithSameColorForShirtAndLogoIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "S", "WHITE", "VIADEE_CLASSIC",
                        "FRONT", "WHITE", null, null);

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(false));
    }

    @Test
    void testClassicSweatShirtWithNameIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "S", "WHITE", "VIADEE_CLASSIC",
                        "FRONT", "WHITE", "John Doe", null);

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(false));
    }

    @Test
    void testClassicSweatShirtWithSloganIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "S", "WHITE", "VIADEE_CLASSIC",
                        "FRONT", "WHITE", null, "TEAM_ARCHITECT");

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(false));
    }

    @Test
    void testSparkSweatShirtWithNameIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "S", "WHITE", "VIADEE_SPARK",
                        "FRONT", "WHITE", "John Doe", null);

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(false));
    }

    @Test
    void testSparkSweatShirtWithSloganIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("SWEAT_SHIRT", "S", "GREY", "VIADEE_SPARK",
                        "FRONT", "WHITE", null, "SYSTEM_THERAPIST");

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(false));
    }

    @Test
    void testSparkSweatShirtWithNameAndSloganIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("SWEAT_SHIRT", "S", "GREY", "VIADEE_SPARK",
                        "FRONT", "WHITE", "John Doe", "SOFTWARE_TAILOR");

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(false));
    }

    @Test
    void testTShirtWithLongNameIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "S", "WHITE", "VIADEE_CLASSIC",
                        "FRONT", "BLACK", "Bartholomäus Langname", null);

        VerifyShirtCombinationResponse verifyShirtCombinationResponse =
                underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest);

        assertThat(verifyShirtCombinationResponse.isShirtCreatable(), is(false));
    }

    @Test
    void testUnknownTypeIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("TANK_TOP", "S", "GREY", "VIADEE_CLASSIC",
                        "FRONT", "WHITE", null, null);

        assertThrows(ShirtNotCreatableException.class,
                () -> underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest));
    }

    @Test
    void testUnknownSizeIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "XXS", "GREY", "VIADEE_CLASSIC",
                        "FRONT", "WHITE", null, null);

        assertThrows(ShirtNotCreatableException.class,
                () -> underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest));
    }

    @Test
    void testUnknownColorIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "S", "PINK", "VIADEE_CLASSIC",
                        "FRONT", "WHITE", null, null);

        assertThrows(ShirtNotCreatableException.class,
                () -> underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest));
    }

    @Test
    void testUnknownCollectionIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "S", "WHITE", "ADESSO_CLASSIC",
                        "FRONT", "WHITE", null, null);

        assertThrows(ShirtNotCreatableException.class,
                () -> underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest));
    }

    @Test
    void testUnknownLogoPositionIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "S", "WHITE", "VIADEE_CLASSIC",
                        "SLEEVE", "WHITE", null, null);

        assertThrows(ShirtNotCreatableException.class,
                () -> underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest));
    }

    @Test
    void testUnknownLogoColorIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "S", "WHITE", "VIADEE_CLASSIC",
                        "FRONT", "GREEN", null, null);

        assertThrows(ShirtNotCreatableException.class,
                () -> underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest));
    }

    @Test
    void testUnknownSloganIsNotCreatable() throws Exception {
        VerifyShirtCombinationRequest verifyShirtCombinationRequest =
                new VerifyShirtCombinationRequest("T_SHIRT", "S", "WHITE", "VIADEE_CLASSIC",
                        "FRONT", "BLACK", null, "CODE_RUNNER");

        assertThrows(ShirtNotCreatableException.class,
                () -> underTest.verifyShirtIsCreatable(verifyShirtCombinationRequest));
    }

}
