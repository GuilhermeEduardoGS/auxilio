package github.britooo.treino_prova.jogo.entity;

import github.britooo.treino_prova.empresa.entity.Empresa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String categoria;
    private LocalDate dataLancamento;
    private Double nota;

    @ManyToOne
    private Empresa empresa;
}
