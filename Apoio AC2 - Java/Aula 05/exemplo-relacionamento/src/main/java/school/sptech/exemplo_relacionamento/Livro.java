package school.sptech.exemplo_relacionamento;

import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;

    // fetchType = LAZY...EAGER
    // cascade
    // @ManyToOne - Muitos para um
    // @OneToMany - Um para todos
    // @OneToOne - Um para um
    // @ManyToMany - Muitos para muitos (associativa é virtualizada)

    @ManyToOne
    private Autor autor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
