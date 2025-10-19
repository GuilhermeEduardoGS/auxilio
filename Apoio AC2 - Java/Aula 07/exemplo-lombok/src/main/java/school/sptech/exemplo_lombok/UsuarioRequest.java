package school.sptech.exemplo_lombok;


import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioRequest (
        @Schema(name = "nome", example = "Guilherme", description = "Esse campo representa o nome")
        String nome,
        @Schema(name = "email", example = "guilherme@email.com", description = "Esse campo representa o e-mail")
        String email
){}

