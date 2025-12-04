package github.britooo.mocks.controller;

import github.britooo.mocks.dto.OrcamentoRequest;
import github.britooo.mocks.dto.OrcamentoResponse;
import github.britooo.mocks.entity.Orcamento;
import github.britooo.mocks.service.OrcamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {

    private final OrcamentoService service;

    public OrcamentoController(OrcamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrcamentoResponse> criar(@RequestBody OrcamentoRequest request) {
        return ResponseEntity.ok(service.criarOrcamento(request));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<OrcamentoResponse> buscarPorCodigo(@PathVariable String codigo) {
        var orcamento = service.buscarPorCodigo(codigo);
        var response = new OrcamentoResponse(orcamento.getCodigo(), orcamento.getValorTotal());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<OrcamentoResponse>> listar() {

        List<Orcamento> orcamentos = service.listarOrcamentos();

        if (orcamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<OrcamentoResponse> responses = orcamentos.stream()
                .map(orcamento -> new OrcamentoResponse(orcamento.getCodigo(), orcamento.getValorTotal()))
                .toList();

        return ResponseEntity.ok().body(responses);
    }
}
