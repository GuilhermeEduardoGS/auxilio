package school.sptech.ex_carro;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.CompareOperation;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBUnit(allowEmptyFields = true, cacheConnection = false, alwaysCleanAfter = true, alwaysCleanBefore = true)
@DisplayName("Dado que o endpoint PUT /albuns/{id} seja chamado")
public class AtualizarCarroTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE carro RESTART IDENTITY",
            value = "/listar/listar_24_carros.json")
    @ExpectedDataSet(value = "/atualizar/carro_atualizado.json", compareOperation = CompareOperation.CONTAINS)
    @DisplayName("[1] - Quando o carro existir, então deve retornar status 200")
    void atualizarCarro() throws Exception {
        var body = """
                {
                  "marca": "Volks",
                  "modelo": "Fusca",
                  "cor": "Azul",
                  "placa": "ANO4S56",
                  "preco": 30000.00,
                  "ano": 1972
                }
                  """;

        mockMvc.perform(put("/carros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.marca").value("Volks"))
                .andExpect(jsonPath("$.modelo").value("Fusca"));
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE carro RESTART IDENTITY",
            value = "/listar/listar_24_carros.json")
    @ExpectedDataSet("/listar/listar_24_carros.json")
    @DisplayName("[2] - Quando o carro não existir, então deve retornar status 404")
    void atualizarCarroNaoExistente() throws Exception {
        var body = """
                {
                  "marca": "Volks",
                  "modelo": "Fusca",
                  "cor": "Azul",
                  "placa": "ANO4S56",
                  "preco": 30000.00,
                  "ano": 1972
                }
                """;

        mockMvc.perform(put("/carros/42")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNotFound());
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE carro RESTART IDENTITY",
            value = "/listar/listar_24_carros.json")
    @ExpectedDataSet(value = "/atualizar/carro_atualizado.json", compareOperation = CompareOperation.CONTAINS)
    @DisplayName("[3] - Quando o id do carro for enviado diferente do id da URL, entao deve considerar o id da URL")
    void atualizarCarroComIdDiferente() throws Exception {
        var body = """
                {
                    "id": 42,
                    "marca": "Volks",
                    "modelo": "Fusca",
                    "cor": "Azul",
                    "placa": "ANO4S56",
                    "preco": 30000.00,
                    "ano": 1972
                }
                """;

        mockMvc.perform(put("/carros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").value(1));
    }
}
