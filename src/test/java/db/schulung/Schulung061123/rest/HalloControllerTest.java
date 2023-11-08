package db.schulung.Schulung061123.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(HalloController.class)
class HalloControllerTest {
    @Autowired
    MockMvc mockMvc;

    static Stream<Object[]> n_moins() {
        return Stream.of(
                new Object[]{0, ""},
                new Object[]{1, "moin "},
                new Object[]{2, "moin moin "},
                new Object[]{3, "moin moin moin "}
        );
    }

    @ParameterizedTest
    @MethodSource("n_moins")
    void testSayHello(int n, String moins) throws Exception {

        RequestBuilder get =
                MockMvcRequestBuilders.get("/hallo/Riccarda")
                        .param("n", String.valueOf(n));

        mockMvc.perform(get)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(moins + "Riccarda"));
    }
}
