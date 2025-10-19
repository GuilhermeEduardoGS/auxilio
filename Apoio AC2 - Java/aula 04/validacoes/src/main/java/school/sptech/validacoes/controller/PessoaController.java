package school.sptech.validacoes.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.validacoes.entity.Pessoa;
import school.sptech.validacoes.repository.PessoaRepository;
import school.sptech.validacoes.service.PessoaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@Valid @RequestBody Pessoa pessoa){
        Pessoa PessoaRegistrada = service.cadastrar(pessoa);
        return ResponseEntity.status(201).body(PessoaRegistrada);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listar(){
        List<Pessoa> todos = service.listar();

        if (todos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id){

        Pessoa pessoaEncontrada = service.buscarPorId(id);
        return ResponseEntity.status(200).body(pessoaEncontrada);
    }

}
