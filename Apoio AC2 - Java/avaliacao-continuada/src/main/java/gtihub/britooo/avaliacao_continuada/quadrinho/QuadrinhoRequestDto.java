package gtihub.britooo.avaliacao_continuada.quadrinho;

import gtihub.britooo.avaliacao_continuada.autor.Autor;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// COMPLEMENTAR
@Data
@Getter
@Setter
public class QuadrinhoRequestDto {
    @NotBlank
    private String titulo;

    @NotBlank
    private String isbn;

    @NotNull
    @PositiveOrZero
    @Min(0)
    @Max(10)
    private Double nota;

    @NotNull
    private LocalDate dataLancamento;

    @NotNull
    private Integer autorId;
}
