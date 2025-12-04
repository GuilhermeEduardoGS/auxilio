package gtihub.britooo.avaliacao_continuada.autor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorResponseDto {
    private Integer id;
    private String nome;
    private Autor autorId;
}
