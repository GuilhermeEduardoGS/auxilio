package school.sptech.exemplo_strategy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.exemplo_strategy.entity.Frete;

public interface FreteRepository extends JpaRepository<Frete, Integer> {
}
