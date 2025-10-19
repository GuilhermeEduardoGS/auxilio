package school.sptech.exemplo_lombok;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

// Lombok
//@Data // "A resposta pra vida, universo e td mais" (GERA TUDO! NÃO USAR PQ É PERIGOSO!!! - Não usar @Data em entity do JPA)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor // serve pra inicializar o constructor com as dependencias definidas (ex: colocar o final em algum atributo)
//@ToString
//@EqualsAndHashCode
@Builder

// JPA
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
}
