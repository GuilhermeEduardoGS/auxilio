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
@DisplayName("Dado que o endpoint GET /marca seja chamado")
public class BuscaPorMarcaTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE carro RESTART IDENTITY",
            value = "/listar/listar_24_carros.json")
    @ExpectedDataSet(value = "/listar/listar_24_carros.json")
    @DisplayName("[1] - Quando busca por marca, então deve retornar status 200")
    void buscarPorMarca() throws Exception {
        var marca = "Volkswagen";

        mockMvc.perform(get("/carros/marca")
                        .param("marca", marca))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].marca").value(marca));
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE carro RESTART IDENTITY",
            value = "/listar/listar_24_carros.json")
    @DisplayName("[2] - Quando o carro por marca não existir, então deve retornar status 204")
    void buscarPorMarcaNaoExistente() throws Exception {
        var marca = "Tesla";

        mockMvc.perform(get("/carros/marca")
                        .param("marca", marca))
                .andExpect(status().isNoContent());
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE carro RESTART IDENTITY",
            value = "/listar/listar_24_carros.json")
    @DisplayName("[3] - Quando a marca for parcial, então deve retornar status 200 com resultados aproximados")
    void buscarPorMarcaNaoCapitalizado() throws Exception {
        var marca = "volks";

        mockMvc.perform(get("/carros/marca")
                        .param("marca", marca))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].marca").value("Volkswagen"));
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE carro RESTART IDENTITY",
            value = "/listar/listar_24_carros.json")
    @DisplayName("[4] - Quando a marca estiver com maiúsculas e minúsculas, então deve retornar status 200 com resultados aproximados")
    void buscarPorMarcaCapitalizado() throws Exception {
        var marca = "VoLkS";

        mockMvc.perform(get("/carros/marca")
                        .param("marca", marca))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].marca").value("Volkswagen"));
    }
}
