package school.sptech.ex_carro;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBUnit(allowEmptyFields = true, cacheConnection = false, alwaysCleanAfter = true, alwaysCleanBefore = true)
@DisplayName("Dado que o endpoint GET /carros seja chamado")
class ListarCarroTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            value = "/listar/listar_4_carros.json")
    @DisplayName("[1] - Quando houver 4 carros, então deve retornar status 200")
    void listarCarros() throws Exception {

        var expected = """
                [
                    {
                       "id": 1,
                       "marca": "Volkswagen",
                       "modelo": "Fusca",
                       "cor": "Azul",
                       "placa": "ANO4S56",
                       "preco": 30000.00,
                       "ano": 1972
                     },
                     {
                       "id": 2,
                       "marca": "Chevrolet",
                       "modelo": "Opala",
                       "cor": "Preto",
                       "placa": "BRA5IL",
                       "preco": 50000.00,
                       "ano": 1979
                     },
                     {
                       "id": 3,
                       "marca": "Ford",
                       "modelo": "Corcel",
                       "cor": "Branco",
                       "placa": "CAR4RO",
                       "preco": 40000.00,
                       "ano": 1975
                     },
                     {
                       "id": 4,
                       "marca": "Fiat",
                       "modelo": "147",
                       "cor": "Vermelho",
                       "placa": "D4T4S",
                       "preco": 20000.00,
                       "ano": 1978
                     }
                ]
                """;

        mockMvc.perform(get("/carros"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    @DisplayName("[2] - Quando não houver carros, então deve retornar status 204")
    void listarCarrosVazio() throws Exception {
        mockMvc.perform(get("/carros"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            value = "/listar/listar_24_carros.json")
    @DisplayName("[3] - Quando houver 24 carros, então deve retornar status 200")
void listarCarros24() throws Exception {
        mockMvc.perform(get("/carros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(24));
    }
}