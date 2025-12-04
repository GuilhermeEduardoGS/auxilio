package github.britooo.treino_prova.jogo.controller;

import github.britooo.treino_prova.jogo.dto.JogoMapper;
import github.britooo.treino_prova.jogo.dto.JogoRequestDto;
import github.britooo.treino_prova.jogo.dto.JogoResponseDto;
import github.britooo.treino_prova.jogo.entity.Jogo;
import github.britooo.treino_prova.jogo.service.JogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jogos")
public class JogoController {

    private final JogoService service;

    @GetMapping
    public ResponseEntity<List<JogoResponseDto>> listar() {
        List<Jogo> todos = service.listar();
        if (todos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<JogoResponseDto> response = JogoMapper.toResponse(todos);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{id}") // 404
    public ResponseEntity<JogoResponseDto> buscarPorId(@PathVariable long id) {
        Jogo jogo = service.buscarPorId(id);
        JogoResponseDto response = JogoMapper.toResponse(jogo);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping
    public ResponseEntity<JogoResponseDto> cadastrar(
            @RequestBody JogoRequestDto dto
    ) {
        Jogo jogoParaRegitrar = JogoMapper.toEntity(dto);

        Jogo jogoRegistrado = service.cadastrar(jogoParaRegitrar, dto.getEmpresaId());

        JogoResponseDto response = JogoMapper.toResponse(jogoRegistrado);

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogoResponseDto> atualizar(
            @PathVariable long id,
            @RequestBody JogoRequestDto dto
    ) {
        Jogo entity = JogoMapper.toEntity(dto, id);
        Jogo jogoAtualizado = service.atualizarPorId(entity);
        JogoResponseDto response = JogoMapper.toResponse(jogoAtualizado);
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(
            @PathVariable long id) {
        service.deletarPorId(id);
        return ResponseEntity.status(204).build();
    }
}
