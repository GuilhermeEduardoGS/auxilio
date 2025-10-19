package school.sptech.validacoes.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import school.sptech.validacoes.entity.Pessoa;
import school.sptech.validacoes.exception.EntidadeConflietoException;
import school.sptech.validacoes.exception.EntidadeNaoEncontradaException;
import school.sptech.validacoes.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;

// Controller - Service - Repository - Entity
@Service // indicar pro Spring que é um componente dele
public class PessoaService {
    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Pessoa cadastrar(Pessoa pessoaParaCadastro){
        if (repository.existsByCpf(pessoaParaCadastro.getCpf())){
            throw new EntidadeConflietoException("Conflito no campo CPF");
        }

        Pessoa pessoaRegistrada = repository.save(pessoaParaCadastro);
        return pessoaRegistrada;
    }

    public List<Pessoa> listar(){
        return repository.findAll();
    }

    public Pessoa buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(
                        () -> new
                                EntidadeNaoEncontradaException
                                ("Pessoa não encontrada")
                );
    }
}
