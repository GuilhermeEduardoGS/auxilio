package school.sptech.AulaDia25.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.AulaDia25.Entity.Usuario;
import school.sptech.AulaDia25.Repository.UsuarioRepository;
import school.sptech.AulaDia25.dto.UsuarioMapper;
import school.sptech.AulaDia25.dto.UsuarioRequestDto;
import school.sptech.AulaDia25.dto.UsuarioResponseDto;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> buscarTodos(){
        List<Usuario> usuarios = repository.findAll();

        if (usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<UsuarioResponseDto> dtos = UsuarioMapper.toResponseDto(usuarios);
        return ResponseEntity.status(200).body(dtos);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrar(@RequestBody UsuarioRequestDto usuarioRequestDto){

        Usuario paraRegistrar = UsuarioMapper.toEntity(usuarioRequestDto);

        Usuario usuarioRegistrado = repository.save(paraRegistrar);

        UsuarioResponseDto resposta = UsuarioMapper.toResponseDto(usuarioRegistrado);

        return ResponseEntity.status(201).body(resposta);
    }
}
