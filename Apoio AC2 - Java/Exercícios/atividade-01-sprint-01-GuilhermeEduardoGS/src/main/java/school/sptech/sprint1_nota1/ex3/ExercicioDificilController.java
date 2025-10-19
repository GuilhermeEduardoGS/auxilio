package school.sptech.sprint1_nota1.ex3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExercicioDificilController {

    @GetMapping("/ex-03/{n}")
    public ExercicioDificilResponse exercicioDificil(@PathVariable int n) {

        if(n <= 0){
            return new ExercicioDificilResponse(0, 0);
        }

        Integer enesimoTermo = 1;
        Integer soma = 1;
        Integer termo1 = 1;
        Integer termo2 = 1;

        if(n == 1){
            return new ExercicioDificilResponse(enesimoTermo, soma);
        }

        soma = termo1 + termo2;
        enesimoTermo = termo2;

        for (int i = 3; i <= n; i++) {
            Integer prox = termo1 + termo2;
            enesimoTermo = prox;
            soma += prox;
            termo1 = termo2;
            termo2 = prox;
        }

        return new ExercicioDificilResponse(enesimoTermo, soma);
    }
}
