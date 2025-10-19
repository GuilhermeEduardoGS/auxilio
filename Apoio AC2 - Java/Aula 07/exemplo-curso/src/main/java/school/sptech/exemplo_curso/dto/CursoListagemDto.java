package school.sptech.exemplo_curso.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoListagemDto {
    Integer id;
    String nome;
    String descricao;
    Double preco;
    String categoria;
}
