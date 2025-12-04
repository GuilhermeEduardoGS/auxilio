package gtihub.britooo.avaliacao_continuada.autor;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AutorRequestDto {
    private Integer id;

    @NotBlank
    private String nome;
}
