package gtihub.britooo.avaliacao_continuada.quadrinho;

import com.fasterxml.jackson.databind.ObjectMapper;
import gtihub.britooo.avaliacao_continuada.autor.Autor;
import gtihub.britooo.avaliacao_continuada.autor.AutorRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class QuadrinhoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuadrinhoRepository quadrinhoRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // ==================== GET /quadrinhos ====================

    @Test
    @DisplayName("GET /quadrinhos deve retornar 204 quando não existir nenhum quadrinho")
    void listarDeveRetornar204QuandoNaoExistirQuadrinho() throws Exception {
        mockMvc.perform(get("/quadrinhos"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /quadrinhos deve retornar 200 e lista de quadrinhos quando existir registro")
    void listarDeveRetornar200EListaQuandoExistirQuadrinho() throws Exception {
        Autor autor = criarAutor("Alan Moore");
        criarQuadrinho("Watchmen", "ISBN-1", 9.8, LocalDate.of(1986, 9, 1), autor);
        criarQuadrinho("V de Vingança", "ISBN-2", 9.5, LocalDate.of(1988, 3, 1), autor);

        mockMvc.perform(get("/quadrinhos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].titulo").value("Watchmen"))
                .andExpect(jsonPath("$[0].autor.nome").value("Alan Moore"))
                .andExpect(jsonPath("$[1].titulo").value("V de Vingança"));
    }

    // ==================== GET /quadrinhos/{id} ====================

    @Test
    @DisplayName("GET /quadrinhos/{id} deve retornar 200 e o quadrinho correspondente")
    void buscarPorIdDeveRetornar200EQuadrinho() throws Exception {
        Autor autor = criarAutor("Neil Gaiman");
        Quadrinho quadrinho = criarQuadrinho(
                "Sandman",
                "ISBN-SAND",
                9.2,
                LocalDate.of(1989, 1, 1),
                autor
        );

        Integer quadrinhoId = getFieldValue(quadrinho, "id", Integer.class);
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        mockMvc.perform(get("/quadrinhos/{id}", quadrinhoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(quadrinhoId))
                .andExpect(jsonPath("$.titulo").value("Sandman"))
                .andExpect(jsonPath("$.autor.id").value(autorId))
                .andExpect(jsonPath("$.autor.nome").value("Neil Gaiman"));
    }

    @Test
    @DisplayName("GET /quadrinhos/{id} deve retornar 404 quando quadrinho não existir")
    void buscarPorIdDeveRetornar404QuandoNaoExistir() throws Exception {
        mockMvc.perform(get("/quadrinhos/{id}", 9999))
                .andExpect(status().isNotFound());
    }

    // ==================== POST /quadrinhos ====================

    @Test
    @DisplayName("POST /quadrinhos deve retornar 201 e salvar o quadrinho quando ISBN ainda não existe")
    void cadastrarDeveRetornar201ESalvarQuadrinhoQuandoIsbnNaoExiste() throws Exception {
        Autor autor = criarAutor("Frank Miller");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Cavaleiro das Trevas");
        setFieldValue(dto, "isbn", "ISBN-CAVALEIRO");
        setFieldValue(dto, "nota", 9.0);
        setFieldValue(dto, "dataLancamento", LocalDate.of(1986, 2, 1));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/quadrinhos")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.titulo").value("Cavaleiro das Trevas"))
                .andExpect(jsonPath("$.isbn").value("ISBN-CAVALEIRO"))
                .andExpect(jsonPath("$.autor.id").value(autorId))
                .andExpect(jsonPath("$.autor.nome").value("Frank Miller"));

        assertThat(quadrinhoRepository.findAll())
                .anyMatch(q -> {
                    String titulo = getFieldValue(q, "titulo", String.class);
                    String isbn = getFieldValue(q, "isbn", String.class);
                    Autor autorQ = getFieldValue(q, "autor", Autor.class);
                    Integer autorQId = autorQ != null ? getFieldValue(autorQ, "id", Integer.class) : null;

                    return "Cavaleiro das Trevas".equals(titulo)
                            && "ISBN-CAVALEIRO".equals(isbn)
                            && autorQ != null
                            && autorId.equals(autorQId);
                });
    }

    @Test
    @DisplayName("POST /quadrinhos deve retornar 409 quando já existir quadrinho com mesmo ISBN")
    void cadastrarDeveRetornar409QuandoIsbnJaExiste() throws Exception {
        Autor autor = criarAutor("Alan Moore");
        Integer autorId = getFieldValue(autor, "id", Integer.class);
        criarQuadrinho("Watchmen", "ISBN-DUPLICADO", 9.8, LocalDate.of(1986, 9, 1), autor);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Outro Quadrinho");
        setFieldValue(dto, "isbn", "ISBN-DUPLICADO");
        setFieldValue(dto, "nota", 8.0);
        setFieldValue(dto, "dataLancamento", LocalDate.of(1990, 1, 1));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/quadrinhos")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isConflict());
    }

    // ==================== GET /quadrinhos/top3 ====================

    @Test
    @DisplayName("GET /quadrinhos/top3 deve retornar 204 quando não houver quadrinhos")
    void top3DeveRetornar204QuandoNaoHouverQuadrinhos() throws Exception {
        mockMvc.perform(get("/quadrinhos/top3"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /quadrinhos/top3 deve retornar 200 e exatamente 3 quadrinhos com melhor nota")
    void top3DeveRetornar200Com3Melhores() throws Exception {
        Autor autor = criarAutor("Vários");

        criarQuadrinho("Q1", "ISBN-1", 7.0, LocalDate.of(2020, 1, 1), autor);
        criarQuadrinho("Q2", "ISBN-2", 9.5, LocalDate.of(2020, 1, 2), autor);
        criarQuadrinho("Q3", "ISBN-3", 8.5, LocalDate.of(2020, 1, 3), autor);
        criarQuadrinho("Q4", "ISBN-4", 9.8, LocalDate.of(2020, 1, 4), autor);
        criarQuadrinho("Q5", "ISBN-5", 6.5, LocalDate.of(2020, 1, 5), autor);
        criarQuadrinho("Q6", "ISBN-6", 9.0, LocalDate.of(2020, 1, 6), autor);

        mockMvc.perform(get("/quadrinhos/top3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].nota").value(9.8))
                .andExpect(jsonPath("$[1].nota").value(9.5))
                .andExpect(jsonPath("$[2].nota").value(9.0));
    }

    // ==================== GET /quadrinhos/por-periodo ====================

    @Test
    @DisplayName("GET /quadrinhos/por-periodo deve retornar 204 quando não houver quadrinhos no intervalo")
    void buscarPorPeriodoDeveRetornar204QuandoSemResultados() throws Exception {
        Autor autor = criarAutor("Autor Período");
        criarQuadrinho("Fora do Período", "ISBN-FORA", 7.0,
                LocalDate.of(2000, 1, 1), autor);

        mockMvc.perform(get("/quadrinhos/por-periodo")
                        .param("inicio", "2010-01-01")
                        .param("fim", "2010-12-31"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /quadrinhos/por-periodo deve retornar 200 e quadrinhos dentro do intervalo")
    void buscarPorPeriodoDeveRetornar200EFiltrarPorData() throws Exception {
        Autor autor = criarAutor("Autor Período");

        criarQuadrinho("Antes", "ISBN-A", 7.0,
                LocalDate.of(2009, 12, 31), autor);
        Quadrinho dentro1 = criarQuadrinho("Dentro 1", "ISBN-D1", 8.0,
                LocalDate.of(2010, 1, 10), autor);
        Quadrinho dentro2 = criarQuadrinho("Dentro 2", "ISBN-D2", 9.0,
                LocalDate.of(2010, 6, 15), autor);
        criarQuadrinho("Depois", "ISBN-DPS", 7.5,
                LocalDate.of(2011, 1, 1), autor);

        Integer id1 = getFieldValue(dentro1, "id", Integer.class);
        Integer id2 = getFieldValue(dentro2, "id", Integer.class);

        mockMvc.perform(get("/quadrinhos/por-periodo")
                        .param("inicio", "2010-01-01")
                        .param("fim", "2010-12-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(id1))
                .andExpect(jsonPath("$[1].id").value(id2));
    }

    // ==================== GET /quadrinhos/autor ====================

    @Test
    @DisplayName("GET /quadrinhos/autor deve retornar 204 quando nenhum quadrinho for encontrado para o autor")
    void buscarPorAutorDeveRetornar204QuandoNaoEncontrar() throws Exception {
        Autor autor = criarAutor("Outro Autor");
        criarQuadrinho("Quadrinho X", "ISBN-X", 7.0,
                LocalDate.of(2010, 1, 1), autor);

        mockMvc.perform(get("/quadrinhos/autor")
                        .param("nome", "Inexistente"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /quadrinhos/autor deve retornar 200 e lista filtrada pelo nome do autor (case insensitive)")
    void buscarPorAutorDeveRetornar200EFiltrarPorNome() throws Exception {
        Autor moore = criarAutor("Alan Moore");
        Autor gaiman = criarAutor("Neil Gaiman");

        criarQuadrinho("Watchmen", "ISBN-W", 9.8,
                LocalDate.of(1986, 9, 1), moore);
        criarQuadrinho("V de Vingança", "ISBN-V", 9.5,
                LocalDate.of(1988, 3, 1), moore);
        criarQuadrinho("Sandman", "ISBN-S", 9.2,
                LocalDate.of(1989, 1, 1), gaiman);

        mockMvc.perform(get("/quadrinhos/autor")
                        .param("nome", "moore"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].autor.nome").value("Alan Moore"))
                .andExpect(jsonPath("$[1].autor.nome").value("Alan Moore"));
    }

    // ==================== DELETE /quadrinhos/{id} ====================

    @Test
    @DisplayName("DELETE /quadrinhos/{id} deve retornar 204 e remover o quadrinho")
    void removerPorIdDeveRetornar204ERemoverQuadrinho() throws Exception {
        Autor autor = criarAutor("Autor Deletar");
        Quadrinho quadrinho = criarQuadrinho("Para Deletar", "ISBN-DEL",
                7.5, LocalDate.of(2015, 1, 1), autor);

        Integer quadrinhoId = getFieldValue(quadrinho, "id", Integer.class);

        mockMvc.perform(delete("/quadrinhos/{id}", quadrinhoId))
                .andExpect(status().isNoContent());

        assertThat(quadrinhoRepository.findById(quadrinhoId)).isEmpty();
    }

    @Test
    @DisplayName("DELETE /quadrinhos/{id} deve retornar 404 quando quadrinho não existir")
    void removerPorIdDeveRetornar404QuandoNaoExistir() throws Exception {
        mockMvc.perform(delete("/quadrinhos/{id}", 9999))
                .andExpect(status().isNotFound());
    }

    // ==================== PUT /quadrinhos/{id} ====================

    @Test
    @DisplayName("PUT /quadrinhos/{id} deve retornar 200 e atualizar quadrinho e autor")
    void atualizarDeveRetornar200EAtualizarQuadrinhoEAutor() throws Exception {
        Autor autorOriginal = criarAutor("Autor Original");
        Autor autorNovo = criarAutor("Autor Novo");

        Quadrinho quadrinho = criarQuadrinho(
                "Titulo Original",
                "ISBN-ORIG",
                8.0,
                LocalDate.of(2000, 1, 1),
                autorOriginal
        );

        Integer quadrinhoId = getFieldValue(quadrinho, "id", Integer.class);
        Integer autorNovoId = getFieldValue(autorNovo, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Titulo Atualizado");
        setFieldValue(dto, "isbn", "ISBN-ATUAL");
        setFieldValue(dto, "nota", 9.5);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2001, 2, 2));
        setFieldValue(dto, "autorId", autorNovoId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/quadrinhos/{id}", quadrinhoId)
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(quadrinhoId))
                .andExpect(jsonPath("$.titulo").value("Titulo Atualizado"))
                .andExpect(jsonPath("$.isbn").value("ISBN-ATUAL"))
                .andExpect(jsonPath("$.nota").value(9.5))
                .andExpect(jsonPath("$.autor.id").value(autorNovoId))
                .andExpect(jsonPath("$.autor.nome").value("Autor Novo"));

        Quadrinho qBanco = quadrinhoRepository.findById(quadrinhoId).orElseThrow();
        assertThat(getFieldValue(qBanco, "titulo", String.class)).isEqualTo("Titulo Atualizado");
        assertThat(getFieldValue(qBanco, "isbn", String.class)).isEqualTo("ISBN-ATUAL");
        assertThat(getFieldValue(qBanco, "nota", Double.class)).isEqualTo(9.5);

        Autor autorBanco = getFieldValue(qBanco, "autor", Autor.class);
        assertThat(getFieldValue(autorBanco, "id", Integer.class)).isEqualTo(autorNovoId);
        assertThat(getFieldValue(autorBanco, "nome", String.class)).isEqualTo("Autor Novo");
    }

    @Test
    @DisplayName("PUT /quadrinhos/{id} deve retornar 409 quando ISBN já existir em outro quadrinho")
    void atualizarDeveRetornar409QuandoIsbnJaExistirEmOutroQuadrinho() throws Exception {
        Autor autor = criarAutor("Autor Único");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        Quadrinho q1 = criarQuadrinho(
                "Quadrinho 1",
                "ISBN-1",
                8.0,
                LocalDate.of(2010, 1, 1),
                autor
        );

        Quadrinho q2 = criarQuadrinho(
                "Quadrinho 2",
                "ISBN-2",
                7.5,
                LocalDate.of(2011, 1, 1),
                autor
        );

        Integer q2Id = getFieldValue(q2, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Quadrinho 2 Atualizado");
        setFieldValue(dto, "isbn", "ISBN-1");
        setFieldValue(dto, "nota", 9.0);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2012, 2, 2));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/quadrinhos/{id}", q2Id)
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("PUT /quadrinhos/{id} deve retornar 404 quando autor informado não existir")
    void atualizarDeveRetornar404QuandoAutorNaoExistir() throws Exception {
        Autor autorExistente = criarAutor("Autor Existente");

        Quadrinho quadrinho = criarQuadrinho(
                "Quadrinho",
                "ISBN-UNI",
                8.0,
                LocalDate.of(2010, 1, 1),
                autorExistente
        );

        Integer quadrinhoId = getFieldValue(quadrinho, "id", Integer.class);
        Integer autorInexistenteId = 9999;

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Quadrinho Atualizado");
        setFieldValue(dto, "isbn", "ISBN-UNI-ATUAL");
        setFieldValue(dto, "nota", 9.0);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2011, 1, 1));
        setFieldValue(dto, "autorId", autorInexistenteId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/quadrinhos/{id}", quadrinhoId)
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /quadrinhos/{id} deve retornar 404 quando quadrinho não existir")
    void atualizarDeveRetornar404QuandoQuadrinhoNaoExistir() throws Exception {
        Autor autor = criarAutor("Autor para Atualização");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Qualquer");
        setFieldValue(dto, "isbn", "ISBN-QUALQUER");
        setFieldValue(dto, "nota", 7.0);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2020, 1, 1));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/quadrinhos/{id}", 9999)
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isNotFound());
    }

    // ==================== helpers ====================

    private Autor criarAutor(String nome) {
        Autor autor = new Autor();
        setFieldValue(autor, "nome", nome);
        return autorRepository.save(autor);
    }

    private Quadrinho criarQuadrinho(String titulo,
                                     String isbn,
                                     Double nota,
                                     LocalDate dataLancamento,
                                     Autor autor) {
        Quadrinho q = new Quadrinho();
        setFieldValue(q, "titulo", titulo);
        setFieldValue(q, "isbn", isbn);
        setFieldValue(q, "nota", nota);
        setFieldValue(q, "dataLancamento", dataLancamento);
        setFieldValue(q, "autor", autor);
        return quadrinhoRepository.save(q);
    }

    // ==================== Reflection Utilities ====================

    private <T> T getFieldValue(Object obj, String fieldName, Class<T> fieldType) {
        try {
            Field field = findField(obj.getClass(), fieldName);
            field.setAccessible(true);
            return fieldType.cast(field.get(obj));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao acessar campo '" + fieldName + "' via reflection", e);
        }
    }

    private void setFieldValue(Object obj, String fieldName, Object value) {
        try {
            Field field = findField(obj.getClass(), fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao definir campo '" + fieldName + "' via reflection", e);
        }
    }

    private Field findField(Class<?> clazz, String fieldName) {
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            try {
                return currentClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                currentClass = currentClass.getSuperclass();
            }
        }
        throw new RuntimeException("Campo '" + fieldName + "' não encontrado na classe " + clazz.getName());
    }


}