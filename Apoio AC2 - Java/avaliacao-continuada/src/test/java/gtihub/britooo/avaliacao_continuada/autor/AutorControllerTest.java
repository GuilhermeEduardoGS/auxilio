package gtihub.britooo.avaliacao_continuada.autor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /autores deve retornar 204 quando não existir nenhum autor")
    void listarDeveRetornar204QuandoNaoExistirAutor() throws Exception {
        // banco vazio (rollback garante isso)

        mockMvc.perform(get("/autores"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /autores deve retornar 200 e lista de autores quando existir registro")
    void listarDeveRetornar200EListaQuandoExistirAutor() throws Exception {
        // arrange
        criarAutor("Autor 1");
        criarAutor("Autor 2");

        // act + assert
        mockMvc.perform(get("/autores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Autor 1"))
                .andExpect(jsonPath("$[1].nome").value("Autor 2"));
    }

    @Test
    @DisplayName("GET /autores/{id} deve retornar 200 e o autor correspondente")
    void buscarPorIdDeveRetornar200EAutor() throws Exception {
        // arrange
        Autor autor = criarAutor("Autor Busca");

        // act + assert
        mockMvc.perform(get("/autores/{id}", autor.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(autor.getId()))
                .andExpect(jsonPath("$.nome").value("Autor Busca"));
    }

    @Test
    @DisplayName("POST /autores deve retornar 201 e salvar o autor")
    void cadastrarDeveRetornar201ESalvarAutor() throws Exception {
        // arrange
        AutorRequestDto dto = new AutorRequestDto();
        dto.setNome("Novo Autor");

        String json = objectMapper.writeValueAsString(dto);

        // act + assert
        mockMvc.perform(post("/autores")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("Novo Autor"));

        // garante que foi persistido
        assertThat(autorRepository.findAll())
                .anyMatch(a -> "Novo Autor".equals(a.getNome()));
    }

    @Test
    @DisplayName("PUT /autores/{id} deve retornar 200 e atualizar o autor")
    void atualizarDeveRetornar200EAutorAtualizado() throws Exception {
        // arrange
        Autor autor = criarAutor("Nome Original");

        AutorRequestDto dtoAtualizado = new AutorRequestDto();
        dtoAtualizado.setNome("Nome Atualizado");

        String json = objectMapper.writeValueAsString(dtoAtualizado);

        // act + assert
        mockMvc.perform(put("/autores/{id}", autor.getId())
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(autor.getId()))
                .andExpect(jsonPath("$.nome").value("Nome Atualizado"));

        // verifica no banco
        Autor autorBanco = autorRepository.findById(autor.getId()).orElseThrow();
        assertThat(autorBanco.getNome()).isEqualTo("Nome Atualizado");
    }

    @Test
    @DisplayName("DELETE /autores/{id} deve retornar 204 e remover o autor")
    void removerPorIdDeveRetornar204ERemoverAutor() throws Exception {
        // arrange
        Autor autor = criarAutor("Autor Para Deletar");

        // act + assert
        mockMvc.perform(delete("/autores/{id}", autor.getId()))
                .andExpect(status().isNoContent());

        assertThat(autorRepository.findById(autor.getId())).isEmpty();
    }

    // ========= helpers =========

    private Autor criarAutor(String nome) {
        Autor autor = new Autor();
        autor.setNome(nome);
        return autorRepository.save(autor);
    }
}