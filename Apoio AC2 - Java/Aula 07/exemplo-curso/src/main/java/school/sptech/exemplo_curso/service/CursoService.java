package school.sptech.exemplo_curso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.exemplo_curso.entity.Curso;
import school.sptech.exemplo_curso.exception.EntidadeNaoEncontradaException;
import school.sptech.exemplo_curso.repository.CursoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    public Curso cadastrar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso buscarPorId(Integer id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Curso de id %d não encontrado".formatted(id)));
    }

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public Curso atualizar(Curso curso) {
        if (cursoRepository.existsById(curso.getId())) {
            curso.setId(curso.getId());
            return cursoRepository.save(curso);
        } else {
            throw new EntidadeNaoEncontradaException("Curso de id %d não encontrado".formatted(curso.getId()));
        }
    }

    public void removerPorId(Integer id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
        } else {
            throw new EntidadeNaoEncontradaException("Curso de id %d não encontrado".formatted(id));
        }
    }

    public Double soma (){
        long qtdCursos = cursoRepository.count();
        Double preco = 0.0;

        for (int i = 1; i < qtdCursos; i++) {
            Optional<Curso> curso = cursoRepository.findById(i);
            preco += curso.get().getPreco();
        }
        return preco;
    }

    public void removerPorPrecoMenorQue(Double preco){
        cursoRepository.removerPorPrecoMenorQue(preco);
    }


}
