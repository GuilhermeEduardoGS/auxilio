package school.sptech.exercicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final List<Usuario> usuarios;
    private Integer id = 0;

    public UsuarioController(List<Usuario> usuarios) {
        this.usuarios = new ArrayList<>();
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Usuario usuario) {
        if (emailExiste(usuario.getEmail()) || idExiste(usuario.getId()) || !validarEmail(usuario.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        id++;
        usuario.setId(id);
        usuarios.add(usuario);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResposta>> listar() {
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(usuarios.stream().map(usuario -> UsuarioMapper.mapearUsuario(usuario)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResposta> buscarPorId(@PathVariable Integer id) {
        Usuario usuarioId = null;

        for (Usuario usuarioAtual : usuarios) {
            if (usuarioAtual.getId().equals(id)) {
                usuarioId = usuarioAtual;
                return ResponseEntity.status(200).body(UsuarioMapper.mapearUsuario(usuarioId));

            }
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResposta> atualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        if (emailExiste(usuario.getEmail()) || !validarEmail(usuario.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                usuario.setId(id);
                usuarios.set(i, usuario);
                return ResponseEntity.status(200).body(UsuarioMapper.mapearUsuario(usuario));
            }
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                usuarios.remove(i);
                return ResponseEntity.status(204).build();
            }

        }
        return ResponseEntity.status(404).build();
    }

    public Boolean emailExiste(String email) {
        for (Usuario usuarioAtual : usuarios) {
            if (usuarioAtual.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Boolean idExiste(Integer id) {
        for (Usuario usuarioAtual : usuarios) {
            if (usuarioAtual.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }

    public Boolean validarEmail(String email) {
        return email.length() >= 10 && email.contains("@");
    }

}
