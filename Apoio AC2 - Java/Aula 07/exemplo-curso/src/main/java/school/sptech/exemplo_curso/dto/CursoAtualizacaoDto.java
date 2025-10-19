package school.sptech.exemplo_curso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoAtualizacaoDto{
    @NotBlank String nome;
    @NotBlank String descricao;
    @NotNull @Positive Double preco;
    @NotBlank String categoria;
}
