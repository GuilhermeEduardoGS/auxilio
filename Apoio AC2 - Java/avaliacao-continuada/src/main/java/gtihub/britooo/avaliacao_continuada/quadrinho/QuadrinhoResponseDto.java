package gtihub.britooo.avaliacao_continuada.quadrinho;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// COMPLEMENTAR
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuadrinhoResponseDto {
    private Integer id;
    private String titulo;
    private String isbn;
    private Double nota;
    private LocalDate dataLancamento;
    private AutorInfoDto autor;

    // COMPLEMENTAR
    @Data
    public static class AutorInfoDto {

        private Integer id;
        private String nome;

    }
}
