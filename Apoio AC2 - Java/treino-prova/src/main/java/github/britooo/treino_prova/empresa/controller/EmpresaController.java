package github.britooo.treino_prova.empresa.controller;

import github.britooo.treino_prova.empresa.dto.EmpresaMapper;
import github.britooo.treino_prova.empresa.dto.EmpresaRequestDto;
import github.britooo.treino_prova.empresa.dto.EmpresaResponseDto;
import github.britooo.treino_prova.empresa.service.EmpresaService;
import github.britooo.treino_prova.empresa.entity.Empresa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService service;

    @GetMapping
    public ResponseEntity<List<EmpresaResponseDto>> listar() {
        List<Empresa> empresas = service.listar();
        if (empresas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EmpresaResponseDto> response = EmpresaMapper.toResponse(empresas);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDto> buscarPorId(@PathVariable long id) {
        Empresa empresa = service.buscarPorId(id);
        EmpresaResponseDto response = EmpresaMapper.toResponse(empresa);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<EmpresaResponseDto> criar(@RequestBody EmpresaRequestDto dto) {
        Empresa entity = EmpresaMapper.toEntity(dto);
        Empresa empresaCriada = service.cadastrar(entity);
        EmpresaResponseDto response = EmpresaMapper.toResponse(empresaCriada);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDto> atualizar(
            @PathVariable long id,
            @RequestBody EmpresaRequestDto dto) {
        Empresa entity = EmpresaMapper.toEntity(dto, id);
        Empresa empresaAtualizada = service.atualizar(entity);
        EmpresaResponseDto response = EmpresaMapper.toResponse(empresaAtualizada);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable long id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
