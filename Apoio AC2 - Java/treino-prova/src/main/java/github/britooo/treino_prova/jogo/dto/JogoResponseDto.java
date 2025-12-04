package github.britooo.treino_prova.jogo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class JogoResponseDto {
    private Long id;
    private String nome;
    private String categoria;
    private LocalDate dataLancamento;
    private Double nota;
    private EmpresaInfoDto empresa;
}
