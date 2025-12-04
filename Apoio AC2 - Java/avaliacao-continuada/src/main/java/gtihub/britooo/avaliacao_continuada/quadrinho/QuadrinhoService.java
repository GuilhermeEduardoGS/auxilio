package gtihub.britooo.avaliacao_continuada.quadrinho;

import gtihub.britooo.avaliacao_continuada.autor.Autor;
import gtihub.britooo.avaliacao_continuada.autor.AutorRepository;
import gtihub.britooo.avaliacao_continuada.autor.AutorResponseDto;
import gtihub.britooo.avaliacao_continuada.exception.EntidadeConflitoException;
import gtihub.britooo.avaliacao_continuada.exception.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuadrinhoService {

    private final String NAO_ENCONTRADO_QUADRINHO = "Quadrinho não encontrado";
    private final String NAO_ENCONTRADO_AUTOR = "Autor não encontrado";
    private final String CONFLITO = "Já existe um quadrinho com ISBN informado";

    private final QuadrinhoRepository qRepository;
    private final AutorRepository aRepository;

    public Quadrinho cadastrar(Quadrinho quadrinhoParaCadastro, Integer autorId) {
        Optional<Autor> autorOpt = aRepository.findById(autorId);

        if (qRepository.existsByIsbn(quadrinhoParaCadastro.getIsbn())){
            throw new EntidadeConflitoException(CONFLITO);
        }

        if (autorOpt.isEmpty()){
            throw new EntidadeNaoEncontradaException(NAO_ENCONTRADO_AUTOR);
        }

        Autor autor = autorOpt.get();
        quadrinhoParaCadastro.setAutor(autor);
        Quadrinho quadrinhoRegistrado = qRepository.save(quadrinhoParaCadastro);
        return quadrinhoRegistrado;

    }

    // COMPLETAR
    public List<Quadrinho> listar() {
        return qRepository.findAll();
    }

    // COMPLETAR
    public Quadrinho buscarPorId(Integer id) {
        return qRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(NAO_ENCONTRADO_QUADRINHO));
    }

    // COMPLETAR
    public Quadrinho atualizar(Quadrinho quadrinhoParaAtualizacao, Integer autorId) {
       Integer quadrinhoId = quadrinhoParaAtualizacao.getId();

        if (!qRepository.existsById(quadrinhoId)){
            throw new EntidadeNaoEncontradaException(NAO_ENCONTRADO_QUADRINHO);
        }

        if (!aRepository.existsById(autorId)){
            throw new EntidadeNaoEncontradaException(NAO_ENCONTRADO_AUTOR);
        }

        Quadrinho quadrinhoOriginal = qRepository.findById(quadrinhoId).get();

        Optional<Quadrinho> quadrinhoComMesmoIsbn = qRepository.findByIsbn(quadrinhoParaAtualizacao.getIsbn());

        if (quadrinhoComMesmoIsbn.isPresent()
                && !quadrinhoComMesmoIsbn.get().getId().equals(quadrinhoId)){
            throw new EntidadeConflitoException(CONFLITO);
        }

        quadrinhoOriginal.setTitulo(quadrinhoParaAtualizacao.getTitulo());
        quadrinhoOriginal.setIsbn(quadrinhoParaAtualizacao.getIsbn());
        quadrinhoOriginal.setDataLancamento(quadrinhoParaAtualizacao.getDataLancamento());
        quadrinhoOriginal.setNota(quadrinhoParaAtualizacao.getNota());

        Autor autor = aRepository.findById(autorId).get();
        quadrinhoOriginal.setAutor(autor);
        return qRepository.save(quadrinhoOriginal);

    }

    // COMPLETAR
    public List<Quadrinho> buscarMelhoresAvaliados() {

        List<Quadrinho> listaTop3 = qRepository.buscarTop3();

        return listaTop3.stream().limit(3).toList();
    }

    // COMPLETAR
    public List<Quadrinho> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return qRepository.buscarPorPeriodo(inicio, fim);
    }

    // COMPLETAR
    public List<Quadrinho> buscarPorAutor(String nomeAutor) {
        return qRepository.buscarPorAutor(nomeAutor);
    }

    // COMPLETAR
    public void removerPorId(Integer id) {
        Boolean existe = qRepository.existsById(id);

        if (!existe){
            throw new EntidadeNaoEncontradaException(NAO_ENCONTRADO_QUADRINHO);
        }
        qRepository.removerQuadrinhosPorAutorId(id);
        qRepository.deleteById(id);
    }
}
