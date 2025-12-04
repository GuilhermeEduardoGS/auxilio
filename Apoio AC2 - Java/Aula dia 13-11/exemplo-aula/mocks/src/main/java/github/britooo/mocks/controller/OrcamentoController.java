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

        OrcamentoResponse orcamentoRegistrado = service.criarOrcamento(request);

        return ResponseEntity.status(201).body(orcamentoRegistrado);

    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Orcamento> buscarPorCodigo(@PathVariable String codigo) {
        Orcamento orcamentoEncontrado = service.buscarPorCodigo(codigo);
        return ResponseEntity.status(200).body(orcamentoEncontrado);
    }

    @GetMapping
    public ResponseEntity<List<Orcamento>> listar() {
        List<Orcamento> todos = service.listarOrcamentos();

        if (todos.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(todos);
    }
}
