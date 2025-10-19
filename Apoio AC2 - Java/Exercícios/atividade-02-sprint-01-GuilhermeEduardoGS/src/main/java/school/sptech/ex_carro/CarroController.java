package school.sptech.ex_carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carros")
public class CarroController {

@Autowired
private final CarroRepository repository;

public CarroController(CarroRepository repository){
    this.repository = repository;
}

    // Cadastrar carro
    @PostMapping
    public ResponseEntity<Carro> cadastrar(@RequestBody Carro carro){
        if (repository.existsByPlaca(carro.getPlaca())){
            return ResponseEntity.status(409).build();
        }
        carro.setId(null);
        Carro carroRegistrado = repository.save(carro);
        return ResponseEntity.status(201).body(carroRegistrado);
    }

    // Listar carro
    @GetMapping
    public ResponseEntity <List<Carro>> listar(){
        List<Carro> all = repository.findAll();
        if (all.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(all);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarPorId(@PathVariable Integer id){
        Optional<Carro> carroOpt = repository.findById(id);
        if(carroOpt.isPresent()){
            Carro carro = carroOpt.get();
            return ResponseEntity.status(200).body(carro);
        }
        return ResponseEntity.status(404).build();
    }

    // Atualizar Carro
    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizar(@PathVariable Integer id, @RequestBody Carro carro) {
        carro.setId(id);
        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        Carro placaDoCarro = repository.findByPlaca(carro.getPlaca());
        if (placaDoCarro != null && !placaDoCarro.getId().equals(id)) {
            return ResponseEntity.status(409).build();
        }

        Carro atualizado = repository.save(carro);
        return ResponseEntity.ok(atualizado);
    }
    
    // Deletar Carro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    // Buscar por marca
    @GetMapping("/marca")
    public ResponseEntity<List<Carro>> buscarPorMarca(@RequestParam String marca) {
        List<Carro> carrosEncontrados = repository.findByMarcaContainingIgnoreCase(marca);

        if (carrosEncontrados.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(carrosEncontrados);
    }

    // Buscar por marca que realmente retorna a quantidade
//    public ResponseEntity<Integer> buscarPorMarca(@RequestParam String marca) {
//        List<Carro> carrosEncontrados = repository.findByMarcaContainingIgnoreCase(marca);
//
//        if (carrosEncontrados.isEmpty()) {
//            return ResponseEntity.status(204).build();
//        }
//        return ResponseEntity.status(200).body(carrosEncontrados.size());
//    }


}
