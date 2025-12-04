package gtihub.britooo.avaliacao_continuada.quadrinho;

import gtihub.britooo.avaliacao_continuada.autor.Autor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Quadrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String isbn;
    private Double nota;
    private LocalDate dataLancamento;
    @ManyToOne
    private Autor autor;
}
