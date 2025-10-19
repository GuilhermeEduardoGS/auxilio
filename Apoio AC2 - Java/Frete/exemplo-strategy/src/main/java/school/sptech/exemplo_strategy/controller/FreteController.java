package school.sptech.exemplo_strategy.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.exemplo_strategy.mapper.FreteMapper;
import school.sptech.exemplo_strategy.dto.FreteRequestDto;
import school.sptech.exemplo_strategy.dto.FreteResponseDto;
import school.sptech.exemplo_strategy.dto.FreteResponseGetDto;
import school.sptech.exemplo_strategy.entity.Frete;
import school.sptech.exemplo_strategy.service.FreteService;

import java.util.List;

@RestController
@RequestMapping("/fretes")
public class FreteController {

    private final FreteService freteService;

    public FreteController(FreteService freteService) {
        this.freteService = freteService;
    }

    @PostMapping
    public ResponseEntity<FreteResponseDto> calcularFrete(@Valid @RequestBody FreteRequestDto request) {

        Frete paraCadastrar = FreteMapper.toEntity(request);

        Frete freteCadastrado = freteService.registrarCotacao(paraCadastrar);

        FreteResponseDto resposta = FreteMapper.toResponseDtoComDesc(freteCadastrado);

        return ResponseEntity.status(201).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<FreteResponseGetDto>> listarFretes() {
        List<Frete> fretes = freteService.listarCotacoes();

        if (fretes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<FreteResponseGetDto> dtos = FreteMapper.toResponseGetDto(fretes);
        return ResponseEntity.ok(dtos);
    }


}
