package github.britooo.treino_prova.jogo.service;

import github.britooo.treino_prova.empresa.entity.Empresa;
import github.britooo.treino_prova.empresa.repository.EmpresaRepository;
import github.britooo.treino_prova.exception.EntidadeNaoEncontradaException;
import github.britooo.treino_prova.jogo.entity.Jogo;
import github.britooo.treino_prova.jogo.repository.JogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Bean || Componente do framework -> Singleton
@RequiredArgsConstructor // Construtor (somente com os argumentos que são constantes)
public class JogoService {

    private final JogoRepository repository;
    private final EmpresaRepository empresaRepository;

    public List<Jogo> listar() {
        return repository.findAll();
    }

    public Jogo cadastrar(Jogo jogoParaCadastro, Long idEmpresa) {
        Optional<Empresa> empresaOpt = empresaRepository.findById(idEmpresa);
        if (empresaOpt.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Empresa não encontrada");
        }
        Empresa empresa = empresaOpt.get();
        jogoParaCadastro.setEmpresa(empresa);
        Jogo jogoRegistrado = repository.save(jogoParaCadastro);
        return jogoRegistrado;
    }

    public Jogo buscarPorId(Long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new EntidadeNaoEncontradaException(
//                        "Jogo de id %d não encontrado.".formatted(id)));

        Optional<Jogo> possivelJogo = repository.findById(id);

        if (possivelJogo.isPresent()) {
            Jogo jogoEncontrado = possivelJogo.get();
            return jogoEncontrado;
        }

        throw new EntidadeNaoEncontradaException(
                "Jogo de id %d não encontrado.".formatted(id));
    }

    public void deletarPorId(Long id) {
        boolean existe = repository.existsById(id);

        if (existe) {
            repository.deleteById(id);
        } else {
            throw new EntidadeNaoEncontradaException("Jogo de id %d não encontrado.".formatted(id));
        }
    }

    // Que id ja foi colocado dentro do obj
    public Jogo atualizarPorId(Jogo jogoParaAtualizar) {
        Long idJogo = jogoParaAtualizar.getId();
        if (!repository.existsById(idJogo)) {
            throw new EntidadeNaoEncontradaException(
                    "Jogo de id %d não encontrado.".formatted(idJogo));
        }

        return repository.save(jogoParaAtualizar);
    }
}
