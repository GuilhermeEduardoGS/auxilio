package gtihub.britooo.avaliacao_continuada.quadrinho;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quadrinhos")
public class QuadrinhoController {

    private final QuadrinhoService quadrinhoService;

    // COMPLEMENTAR
    @GetMapping
    public ResponseEntity<List<QuadrinhoResponseDto>> listar() {

        List<Quadrinho> todos = quadrinhoService.listar();

        if (todos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<QuadrinhoResponseDto> response = QuadrinhoMapper.toResponseDto(todos);
        return ResponseEntity.status(200).body(response);

    }

    // COMPLEMENTAR
    @GetMapping("/{id}")
    public ResponseEntity<QuadrinhoResponseDto> buscarPorId(@PathVariable Integer id) {

        Quadrinho quadrinho = quadrinhoService.buscarPorId(id);

        QuadrinhoResponseDto response = QuadrinhoMapper.toResponseDto(quadrinho);

        return ResponseEntity.status(200).body(response);

    }

    // COMPLEMENTAR
    @PostMapping
    public ResponseEntity<QuadrinhoResponseDto> cadastrar(@RequestBody @Valid QuadrinhoRequestDto dto) {

        Quadrinho quadrinhoParaRegistrar = QuadrinhoMapper.toEntity(dto);

        Quadrinho quadrinhoRegistrado = quadrinhoService.cadastrar(quadrinhoParaRegistrar, dto.getAutorId());

        QuadrinhoResponseDto response = QuadrinhoMapper.toResponseDto(quadrinhoRegistrado);

        return ResponseEntity.status(201).body(response);

    }

    // COMPLEMENTAR
    @PutMapping("/{id}")
    public ResponseEntity<QuadrinhoResponseDto> atualizar(@PathVariable Integer id, @RequestBody @Valid QuadrinhoRequestDto dto) {

        Quadrinho quadrinhoParaAtualizar = QuadrinhoMapper.toEntity(dto);

        quadrinhoParaAtualizar.setId(id);

        Quadrinho quadrinhoAtualizado = quadrinhoService.atualizar(quadrinhoParaAtualizar, dto.getAutorId());

        QuadrinhoResponseDto response = QuadrinhoMapper.toResponseDto(quadrinhoAtualizado);

        return ResponseEntity.status(200).body(response);

    }

    // COMPLEMENTAR
    @GetMapping("/top3")
    public ResponseEntity<List<QuadrinhoResponseDto>> buscarMelhoresAvaliados() {

        List<Quadrinho> lista = quadrinhoService.buscarMelhoresAvaliados();

        if (lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<QuadrinhoResponseDto> response = QuadrinhoMapper.toResponseDto(lista);
        return ResponseEntity.status(200).body(response);

    }

    // COMPLEMENTAR
    @GetMapping("/por-periodo")
    public ResponseEntity<List<QuadrinhoResponseDto>> buscarPorPeriodo(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim
    ) {

        List<Quadrinho> lista = quadrinhoService.buscarPorPeriodo(inicio, fim);

        if (lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<QuadrinhoResponseDto> response = QuadrinhoMapper.toResponseDto(lista);

        return ResponseEntity.status(200).body(response);
    }

    // COMPLEMENTAR
    @GetMapping("/autor")
    public ResponseEntity<List<QuadrinhoResponseDto>> buscarPorAutor(
            @RequestParam String nome
    ) {

        List<Quadrinho> lista = quadrinhoService.buscarPorAutor(nome);

        if (lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<QuadrinhoResponseDto> response = QuadrinhoMapper.toResponseDto(lista);

        return ResponseEntity.status(200).body(response);

    }

    // COMPLEMENTAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {

        quadrinhoService.removerPorId(id);

        return ResponseEntity.status(204).build();
    }
}
