package school.sptech.exemplo_curso.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import school.sptech.exemplo_curso.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Curso c WHERE c.preco < :preco")

    void removerPorPrecoMenorQue(Double preco);
}
