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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBUnit(allowEmptyFields = true, cacheConnection = false, alwaysCleanAfter = true, alwaysCleanBefore = true)
@DisplayName("Dado que o endpoint GET /carros/{id} seja chamado")
public class BuscarCarroPorIdTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE carro RESTART IDENTITY",
            value = "/listar/listar_24_carros.json")
    @DisplayName("[1] - Quando o carro existir, então deve retornar status 200")
    void buscarCarro() throws Exception {
        mockMvc.perform(get("/carros/22"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.marca").value("Chevrolet"))
                .andExpect(jsonPath("$.modelo").value("Opala"));

    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE carro RESTART IDENTITY",
            value = "/listar/listar_24_carros.json")
    @DisplayName("[2] - Quando o carros não existir, então deve retornar status 404")
    void buscarCarroNaoExistente() throws Exception {
        mockMvc.perform(get("/carros/42"))
                .andExpect(status().isNotFound());
    }
}
