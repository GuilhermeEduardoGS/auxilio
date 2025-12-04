package gtihub.britooo.avaliacao_continuada.quadrinho;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// COMPLETAR
public interface QuadrinhoRepository extends JpaRepository<Quadrinho, Integer> {

    Boolean existsByIsbn(String Isbn);

    Optional<Quadrinho> findByIsbn(String isbn);

    @Modifying
    @Transactional
    @Query("DELETE FROM Quadrinho q WHERE q.autor.id = :autorId")
    void removerQuadrinhosPorAutorId(Integer autorId);

    @Query("SELECT q FROM Quadrinho q ORDER BY q.nota DESC")
    List<Quadrinho> buscarTop3();

    @Query("SELECT q FROM Quadrinho q WHERE q.dataLancamento BETWEEN :inicio AND :fim")
    List<Quadrinho> buscarPorPeriodo(LocalDate inicio, LocalDate fim);

    @Query("SELECT q FROM Quadrinho q WHERE LOWER(q.autor.nome) LIKE LOWER(CONCAT('%', :nomeAutor, '%'))")
    List<Quadrinho> buscarPorAutor(String nomeAutor);

    // talvez tenha que usar @Param nos parametros...
}
