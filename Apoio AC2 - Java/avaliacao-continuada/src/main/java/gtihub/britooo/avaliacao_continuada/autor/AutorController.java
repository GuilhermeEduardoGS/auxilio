package gtihub.britooo.avaliacao_continuada.autor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorResponseDto>> listar() {
        List<Autor> autores = autorService.listar();
        if (autores.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<AutorResponseDto> response = AutorMapper.toResponseDto(autores);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDto> buscarPorId(@PathVariable Integer id) {
        Autor autor = autorService.buscarPorId(id);
        AutorResponseDto responseDto = AutorMapper.toResponseDto(autor);
        return ResponseEntity.status(200).body(responseDto);
    }

    @PostMapping
    public ResponseEntity<AutorResponseDto> cadastrar(@RequestBody AutorRequestDto dto) {
        Autor autorCriado = autorService.cadastrar(AutorMapper.toEntity(dto));
        return ResponseEntity.status(201).body(AutorMapper.toResponseDto(autorCriado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDto> atualizar(
            @PathVariable Integer id, @RequestBody AutorRequestDto dto) {
        Autor entity = AutorMapper.toEntity(dto, id);
        Autor autorCriado = autorService.atualizar(entity);
        return ResponseEntity.status(200).body(AutorMapper.toResponseDto(autorCriado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        autorService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }
}
