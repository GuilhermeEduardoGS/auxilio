package github.britooo.mocks.repository;

import github.britooo.mocks.entity.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
    Optional<Orcamento> findByCodigo(String codigo);
}
