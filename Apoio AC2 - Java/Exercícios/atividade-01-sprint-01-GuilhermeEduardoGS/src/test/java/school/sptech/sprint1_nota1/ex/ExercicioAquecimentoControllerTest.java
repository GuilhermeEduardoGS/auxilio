package school.sptech.sprint1_nota1.ex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("0. Mais fácil que passar manteiga no pão \uD83E\uDDC8")
@WebMvcTest(ExercicioAquecimentoController.class)
class ExercicioAquecimentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Quando o número for par, então deve retornar true")
    void quandoNumeroForPar_entaoRetornaTrue() throws Exception {
        mockMvc.perform(get("/ex-00/42"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("Quando o número for ímpar, então deve retornar false")
    void quandoNumeroForImpar_entaoRetornaFalse() throws Exception {
        mockMvc.perform(get("/ex-00/51"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    @DisplayName("Quando o número negativo for par, então deve retornar true")
    void quandoNumeroNegativoForPar_entaoRetornaTrue() throws Exception {
        mockMvc.perform(get("/ex-00/-42"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("Quando o número negativo for ímpar, então deve retornar false")
    void quandoNumeroNegativoForImpar_entaoRetornaTrue() throws Exception {
        mockMvc.perform(get("/ex-00/-13"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    @DisplayName("Quando o número for zero, então deve retornar true")
    void quandoNumeroForZero_entaoRetornaTrue() throws Exception {
        mockMvc.perform(get("/ex-00/0"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}