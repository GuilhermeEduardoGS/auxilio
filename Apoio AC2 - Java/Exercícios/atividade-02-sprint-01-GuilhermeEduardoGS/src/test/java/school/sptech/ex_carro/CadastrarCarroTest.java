package school.sptech.ex_carro;

import com.github.database.rider.core.api.configuration.DBUnit;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBUnit(allowEmptyFields = true, cacheConnection = false, alwaysCleanAfter = true, alwaysCleanBefore = true)
@DisplayName("Dado que o endpoint POST /carros seja chamado")
public class CadastrarCarroTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(
          cleanBefore = true,
          cleanAfter = true,
          executeStatementsBefore = "TRUNCATE TABLE carro RESTART IDENTITY",
          value = "/datasets/listar/listar_24_carros.json")
    @ExpectedDataSet({
            "/datasets/listar/listar_24_carros.json",
          "/datasets/cadastrar/novo_carro.json"})
    @DisplayName("[1] - Quando o carro for cadastrado com sucesso, então deve retornar status 201 e id do carro")
    void cadastrarCarro() throws Exception {
        var body = """
              {
                "marca": "Volkswagen",
                "modelo": "Fusca",
                "cor": "Azul",
                "placa": "ANO4S56",
                "preco": 30000.00,
                "ano": 1972
              }
              """;

        mockMvc.perform(post("/carros")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
              .andExpect(status().isCreated())
              .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    @DataSet(
          cleanBefore = true,
          cleanAfter = true,
          executeStatementsBefore = "TRUNCATE TABLE carro RESTART IDENTITY",
          value = "/datasets/listar/listar_24_carros.json")
    @ExpectedDataSet({
            "/datasets/listar/listar_24_carros.json",
            "/datasets/cadastrar/novo_carro.json"
    })
    @DisplayName("[2] - Caso o id seja informado, então deve ser ignorado e gerado um novo id")
    void cadastrarCarroComId() throws Exception {
        var body = """
              {
                "id": 1,
                "marca": "Volkswagen",
                "modelo": "Fusca",
                "cor": "Azul",
                "placa": "ANO4S56",
                "preco": 30000.00,
                "ano": 1972
              }
              """;

        mockMvc.perform(post("/carros")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
              .andExpect(status().isCreated())
              .andExpect(jsonPath("$.id").isNumber());
    }
}
