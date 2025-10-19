package school.sptech.ex_carro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Integer> {

    Boolean existsByPlaca(String placa);

    List<Carro> findByMarcaContainingIgnoreCase(String marca);
    Carro findByPlaca(String placa);

}
