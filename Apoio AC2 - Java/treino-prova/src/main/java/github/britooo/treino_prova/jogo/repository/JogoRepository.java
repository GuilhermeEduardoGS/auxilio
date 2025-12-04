package github.britooo.treino_prova.jogo.repository;

import github.britooo.treino_prova.jogo.entity.Jogo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Jogo j WHERE j.empresa.id = :empresaId")
    void removerJogosPorEmpresaID(Long empresaId);
}
