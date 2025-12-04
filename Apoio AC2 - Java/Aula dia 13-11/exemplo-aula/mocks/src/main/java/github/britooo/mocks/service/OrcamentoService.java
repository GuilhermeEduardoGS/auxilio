package github.britooo.mocks.service;

import github.britooo.mocks.dto.OrcamentoRequest;
import github.britooo.mocks.dto.OrcamentoResponse;
import github.britooo.mocks.entity.Orcamento;
import github.britooo.mocks.exception.OrcamentoNaoEncontradoException;
import github.britooo.mocks.repository.OrcamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrcamentoService {

    private final OrcamentoRepository repository;

    public OrcamentoService(OrcamentoRepository repository) {
        this.repository = repository;
    }

    public List<Orcamento> listarOrcamentos() {
        return repository.findAll();
    }

    public Orcamento buscarPorCodigo(String codigo) {

        return repository.findById(Long.valueOf(codigo))
                .orElseThrow(
                        () -> new
                                OrcamentoNaoEncontradoException
                                ("Orçamento não encontrado")
                );

    }

    // se qtd > 10 = 10% de desconto
    public OrcamentoResponse criarOrcamento(OrcamentoRequest req) {

        OrcamentoResponse res = new OrcamentoResponse();

        Double total = req.getPrecoUnitario() * req.getQuantidade();

        if (req.getQuantidade() > 10){
            total *= 0.9;
        }

        res.setValorTotal(total);
        String codigo = "ORC" + listarOrcamentos().size() + 1;
        res.setCodigo(codigo);
        return res;

    }
}
