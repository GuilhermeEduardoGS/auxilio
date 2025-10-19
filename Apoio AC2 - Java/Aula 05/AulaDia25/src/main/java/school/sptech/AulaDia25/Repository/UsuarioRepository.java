package school.sptech.AulaDia25.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.AulaDia25.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
