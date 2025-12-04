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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class QuadrinhoValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuadrinhoRepository quadrinhoRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // ==================== Testes de Validação - POST ====================

    @Test
    @DisplayName("POST /quadrinhos deve retornar 400 quando titulo for nulo")
    void cadastrarDeveRetornar400QuandoTituloForNulo() throws Exception {
        Autor autor = criarAutor("Autor Teste");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", null);
        setFieldValue(dto, "isbn", "ISBN-TEST");
        setFieldValue(dto, "nota", 8.0);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2020, 1, 1));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/quadrinhos")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /quadrinhos deve retornar 400 quando isbn for nulo")
    void cadastrarDeveRetornar400QuandoIsbnForNulo() throws Exception {
        Autor autor = criarAutor("Autor Teste");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Titulo Teste");
        setFieldValue(dto, "isbn", null);
        setFieldValue(dto, "nota", 8.0);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2020, 1, 1));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/quadrinhos")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /quadrinhos deve retornar 400 quando nota for nula")
    void cadastrarDeveRetornar400QuandoNotaForNula() throws Exception {
        Autor autor = criarAutor("Autor Teste");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Titulo Teste");
        setFieldValue(dto, "isbn", "ISBN-TEST");
        setFieldValue(dto, "nota", null);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2020, 1, 1));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/quadrinhos")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /quadrinhos deve retornar 400 quando dataLancamento for nula")
    void cadastrarDeveRetornar400QuandoDataLancamentoForNula() throws Exception {
        Autor autor = criarAutor("Autor Teste");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Titulo Teste");
        setFieldValue(dto, "isbn", "ISBN-TEST");
        setFieldValue(dto, "nota", 8.0);
        setFieldValue(dto, "dataLancamento", null);
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/quadrinhos")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /quadrinhos deve retornar 400 quando autorId for nulo")
    void cadastrarDeveRetornar400QuandoAutorIdForNulo() throws Exception {
        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Titulo Teste");
        setFieldValue(dto, "isbn", "ISBN-TEST");
        setFieldValue(dto, "nota", 8.0);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2020, 1, 1));
        setFieldValue(dto, "autorId", null);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/quadrinhos")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /quadrinhos deve retornar 400 quando nota for menor que 0")
    void cadastrarDeveRetornar400QuandoNotaForMenorQueZero() throws Exception {
        Autor autor = criarAutor("Autor Teste");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Titulo Teste");
        setFieldValue(dto, "isbn", "ISBN-TEST");
        setFieldValue(dto, "nota", -0.1);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2020, 1, 1));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/quadrinhos")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /quadrinhos deve retornar 400 quando nota for maior que 10")
    void cadastrarDeveRetornar400QuandoNotaForMaiorQueDez() throws Exception {
        Autor autor = criarAutor("Autor Teste");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Titulo Teste");
        setFieldValue(dto, "isbn", "ISBN-TEST");
        setFieldValue(dto, "nota", 10.1);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2020, 1, 1));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/quadrinhos")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /quadrinhos deve aceitar nota igual a 0")
    void cadastrarDeveAceitarNotaIgualAZero() throws Exception {
        Autor autor = criarAutor("Autor Teste");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Titulo Teste");
        setFieldValue(dto, "isbn", "ISBN-ZERO");
        setFieldValue(dto, "nota", 0.0);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2020, 1, 1));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/quadrinhos")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /quadrinhos deve aceitar nota igual a 10")
    void cadastrarDeveAceitarNotaIgualADez() throws Exception {
        Autor autor = criarAutor("Autor Teste");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Titulo Teste");
        setFieldValue(dto, "isbn", "ISBN-DEZ");
        setFieldValue(dto, "nota", 10.0);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2020, 1, 1));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/quadrinhos")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated());
    }

    // ==================== Testes de Validação - PUT ====================

    @Test
    @DisplayName("PUT /quadrinhos/{id} deve retornar 400 quando titulo for nulo")
    void atualizarDeveRetornar400QuandoTituloForNulo() throws Exception {
        Autor autor = criarAutor("Autor Teste");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        Quadrinho quadrinho = criarQuadrinho("Original", "ISBN-ORIG", 8.0,
                LocalDate.of(2020, 1, 1), autor);
        Integer quadrinhoId = getFieldValue(quadrinho, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", null);
        setFieldValue(dto, "isbn", "ISBN-UPDATED");
        setFieldValue(dto, "nota", 9.0);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2021, 1, 1));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/quadrinhos/{id}", quadrinhoId)
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /quadrinhos/{id} deve retornar 400 quando nota for menor que 0")
    void atualizarDeveRetornar400QuandoNotaForMenorQueZero() throws Exception {
        Autor autor = criarAutor("Autor Teste");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        Quadrinho quadrinho = criarQuadrinho("Original", "ISBN-ORIG", 8.0,
                LocalDate.of(2020, 1, 1), autor);
        Integer quadrinhoId = getFieldValue(quadrinho, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Titulo Atualizado");
        setFieldValue(dto, "isbn", "ISBN-UPDATED");
        setFieldValue(dto, "nota", -1.0);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2021, 1, 1));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/quadrinhos/{id}", quadrinhoId)
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /quadrinhos/{id} deve retornar 400 quando nota for maior que 10")
    void atualizarDeveRetornar400QuandoNotaForMaiorQueDez() throws Exception {
        Autor autor = criarAutor("Autor Teste");
        Integer autorId = getFieldValue(autor, "id", Integer.class);

        Quadrinho quadrinho = criarQuadrinho("Original", "ISBN-ORIG", 8.0,
                LocalDate.of(2020, 1, 1), autor);
        Integer quadrinhoId = getFieldValue(quadrinho, "id", Integer.class);

        QuadrinhoRequestDto dto = new QuadrinhoRequestDto();
        setFieldValue(dto, "titulo", "Titulo Atualizado");
        setFieldValue(dto, "isbn", "ISBN-UPDATED");
        setFieldValue(dto, "nota", 11.0);
        setFieldValue(dto, "dataLancamento", LocalDate.of(2021, 1, 1));
        setFieldValue(dto, "autorId", autorId);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/quadrinhos/{id}", quadrinhoId)
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    // ==================== Helpers ====================

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