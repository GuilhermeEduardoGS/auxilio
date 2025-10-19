package school.sptech.exemplo_lombok;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //JPA
@RequestMapping("/usuarios") //JPA
@RequiredArgsConstructor // Lombok
public class UsuarioController {

    private final UsuarioRepository repository;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        List<Usuario> usuarios = repository.findAll();
        if (usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody UsuarioRequest dto){

        Usuario entity = UsuarioMapper.toEntity(dto);
        Usuario usuarioRegistrado = repository.save(entity);
        return ResponseEntity.status(201).body(usuarioRegistrado);
    }


}
