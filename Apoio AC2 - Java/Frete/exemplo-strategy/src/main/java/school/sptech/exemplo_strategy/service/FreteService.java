package school.sptech.exemplo_strategy.service;

import org.springframework.stereotype.Service;
import school.sptech.exemplo_strategy.entity.Frete;
import school.sptech.exemplo_strategy.entity.TipoFreteEnum;
import school.sptech.exemplo_strategy.exception.FreteInvalidoException;
import school.sptech.exemplo_strategy.repository.FreteRepository;

import java.util.List;

@Service
public class FreteService {

    private final FreteRepository freteRepository;

    public FreteService(FreteRepository freteRepository) {
        this.freteRepository = freteRepository;
    }

    public Frete registrarCotacao(Frete frete) {

        if (frete.getTipo() == null){
            throw new FreteInvalidoException("Tipo de frete não pode ser nulo");
        }

        Double valorFrete = 0.0;

        switch (frete.getTipo()){

            case NORMAL:
                valorFrete = 10.0 + (0.10 * frete.getPesoEmKg());
                break;
            case EXPRESSO:
                valorFrete = 30.0 + (0.50 * frete.getPesoEmKg());
                break;
            case TRANSPORTADORA:
                valorFrete = 20.0 + (0.20 * frete.getPesoEmKg());
                break;
            default:
                throw new FreteInvalidoException("Tipo de frete inválido");

        }

        frete.setValorFrete(valorFrete);

        return freteRepository.save(frete);

    }

    public List<Frete> listarCotacoes() {
        return freteRepository.findAll();
    }
}
