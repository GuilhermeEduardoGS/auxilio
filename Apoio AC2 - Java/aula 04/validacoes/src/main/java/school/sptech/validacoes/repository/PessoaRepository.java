package school.sptech.validacoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.validacoes.entity.Pessoa;

public interface PessoaRepository extends JpaRepository <Pessoa, Long> {

    Boolean existsByCpf(String cpf);



}
