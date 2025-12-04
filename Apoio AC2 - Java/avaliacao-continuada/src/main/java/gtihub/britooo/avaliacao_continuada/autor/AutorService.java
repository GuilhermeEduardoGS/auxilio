package gtihub.britooo.avaliacao_continuada.autor;

import gtihub.britooo.avaliacao_continuada.exception.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    private final String NAO_ENCONTRADO = "Autor não encontrado";

    public Autor cadastrar(Autor autorParaCadastrar) {
        return autorRepository.save(autorParaCadastrar);
    }

    public Autor buscarPorId(Integer id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(NAO_ENCONTRADO));
    }

    public List<Autor> listar() {
        return autorRepository.findAll();
    }

    public Autor atualizar(Autor autorParaAtualizar) {
        if (!autorRepository.existsById(autorParaAtualizar.getId())) {
            throw new EntidadeNaoEncontradaException(NAO_ENCONTRADO);
        }
        return autorRepository.save(autorParaAtualizar);
    }

    public void removerPorId(Integer id) {
        if (!autorRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException(NAO_ENCONTRADO);
        }
        autorRepository.deleteById(id);
    }
}
