package github.britooo.treino_prova.empresa.service;

import github.britooo.treino_prova.empresa.entity.Empresa;
import github.britooo.treino_prova.empresa.repository.EmpresaRepository;
import github.britooo.treino_prova.exception.EntidadeConflitoException;
import github.britooo.treino_prova.exception.EntidadeNaoEncontradaException;
import github.britooo.treino_prova.jogo.repository.JogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository repository;
    private final JogoRepository jogoRepository;

    public List<Empresa> listar() {
        return repository.findAll();
    }

    public Empresa buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Empresa de id %d não encontrada".formatted(id)));
    }

    public Empresa cadastrar(Empresa empresaParaCadastro) {
        if (repository.existsByCnpj(empresaParaCadastro.getCnpj())){
            throw new EntidadeConflitoException(
                    "CNPJ %s já utilizado.".formatted(empresaParaCadastro.getCnpj())
            );
        }

        return repository.save(empresaParaCadastro);
    }

    public Empresa atualizar(Empresa empresaParaAtualizar){
        if (repository.existsByCnpjAndIdNot(
                empresaParaAtualizar.getCnpj(),
                empresaParaAtualizar.getId()
        )){
            throw new EntidadeConflitoException(
                    "CNPJ %s já utilizado.".formatted(empresaParaAtualizar.getCnpj())
            );
        }

        return repository.save(empresaParaAtualizar);
    }

    public void deletarPorId(Long id){
        if (!repository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Empresa não encontrada");
        }
        jogoRepository.removerJogosPorEmpresaID(id);
        repository.deleteById(id);
    }

}
