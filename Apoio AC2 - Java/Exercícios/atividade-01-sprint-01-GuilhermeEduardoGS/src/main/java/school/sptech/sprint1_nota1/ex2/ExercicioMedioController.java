package school.sptech.sprint1_nota1.ex2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExercicioMedioController {

    @GetMapping("/ex-02/{numero}")
    public Boolean exercicioMedio(@PathVariable int numero) {

        if (numero < 2){
            return false;
        }

        for (int i = 2; i < numero; i++) {
            if (numero % i == 0){
                return false;
            }
        }

        return true;
    }
}
