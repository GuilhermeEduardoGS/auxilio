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

    public UsuarioController() {
        this.usuarios = new ArrayList<>();
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
        if (usuario.getEmail() == null || !validarEmail(usuario.getEmail()) || emailExiste(usuario.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        id++;
        usuario.setId(id);
        usuarios.add(usuario);

        return ResponseEntity.status(201).body(resposta(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<Usuario> resposta = new ArrayList<>();
        for (Usuario u : usuarios) {
            resposta.add(resposta(u));
        }

        return ResponseEntity.status(200).body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id)) {
                return ResponseEntity.status(200).body(resposta(u));
            }
        }

        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        if (usuario.getEmail() == null || !validarEmail(usuario.getEmail()) || emailDuplicado(usuario.getEmail(), id)) {
            return ResponseEntity.status(409).build();
        }

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                usuario.setId(id);
                usuarios.set(i, usuario);
                return ResponseEntity.status(200).body(resposta(usuario));
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

    private Boolean emailExiste(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    private Boolean emailDuplicado(String email, Integer idAtual) {
        for (Usuario u : usuarios) {
            if (!u.getId().equals(idAtual) && u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    private Boolean validarEmail(String email) {
        return email.length() >= 10 && email.contains("@");

    }

    private Usuario resposta(Usuario usuario) {
        Usuario copia = new Usuario();
        copia.setId(usuario.getId());
        copia.setNome(usuario.getNome());
        copia.setEmail(usuario.getEmail());
        copia.setDataNascimento(usuario.getDataNascimento());
        return copia;
    }

}