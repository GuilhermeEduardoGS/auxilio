package github.britooo.treino_prova.jogo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
public class JogoRequestDto {
    private String nome;
    private String categoria;
    private LocalDate dataLancamento;
    private Double nota;
    @NotNull
    private Long empresaId;
}
