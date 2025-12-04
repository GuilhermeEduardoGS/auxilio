package github.britooo.mocks.service;

import github.britooo.mocks.dto.OrcamentoRequest;
import github.britooo.mocks.dto.OrcamentoResponse;
import github.britooo.mocks.entity.Orcamento;
import github.britooo.mocks.exception.OrcamentoInvalidoException;
import github.britooo.mocks.exception.OrcamentoNaoEncontradoException;
import github.britooo.mocks.repository.OrcamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        return repository.findByCodigo(codigo)
                .orElseThrow(() -> new OrcamentoNaoEncontradoException("Orçamento não encontrado"));
    }

    public OrcamentoResponse criarOrcamento(OrcamentoRequest req) {

        if (req.getQuantidade() <= 0) {
            throw new OrcamentoInvalidoException("Quantidade inválida");
        }

        if (req.getPrecoUnitario() <= 0) {
            throw new OrcamentoInvalidoException("Preço inválido");
        }

        double total = req.getQuantidade() * req.getPrecoUnitario();

        if (req.getQuantidade() >= 10) {
            total *= 0.9;
        }

        String codigo = "ORC-" + UUID.randomUUID().toString().substring(0, 8);

        Orcamento entity = new Orcamento(
                codigo,
                req.getQuantidade(),
                req.getPrecoUnitario(),
                total
        );

        Orcamento salvo = repository.save(entity);

        return new OrcamentoResponse(
                salvo.getCodigo(),
                salvo.getValorTotal()
        );
    }
}
