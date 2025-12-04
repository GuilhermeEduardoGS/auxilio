package github.britooo.treino_prova.jogo.dto;

import github.britooo.treino_prova.empresa.entity.Empresa;
import github.britooo.treino_prova.jogo.entity.Jogo;

import java.util.ArrayList;
import java.util.List;

public class JogoMapper {

    public static Jogo toEntity(JogoRequestDto dto) {
        if (dto==null) {
            return null;
        }

        Jogo jogo = new Jogo();

        jogo.setNome(dto.getNome());
        jogo.setCategoria(dto.getCategoria());
        jogo.setDataLancamento(dto.getDataLancamento());
        jogo.setNota(dto.getNota());

        return jogo;
    }

    public static Jogo toEntity(JogoRequestDto dto, long idJogo) {
        if (dto==null) {
            return null;
        }
        Jogo jogo = toEntity(dto);
        jogo.setId(idJogo);
        return jogo;
    }

    public static JogoResponseDto toResponse(Jogo entidadeJogo) {
        if (entidadeJogo==null) {
            return null;
        }

        JogoResponseDto jogoDto = new JogoResponseDto();

        jogoDto.setId(entidadeJogo.getId());
        jogoDto.setNome(entidadeJogo.getNome());
        jogoDto.setCategoria(entidadeJogo.getCategoria());
        jogoDto.setDataLancamento(entidadeJogo.getDataLancamento());
        jogoDto.setNota(entidadeJogo.getNota());

        Empresa empresaEntidade = entidadeJogo.getEmpresa();

        EmpresaInfoDto empresaDto = new EmpresaInfoDto();
        empresaDto.setId(empresaEntidade.getId());
        empresaDto.setNome(empresaEntidade.getNome());

        jogoDto.setEmpresa(empresaDto);

        return jogoDto;
    }

    public static List<JogoResponseDto> toResponse(List<Jogo> entidades) {
//        return entidades.stream()
//                .map(JogoMapper::toResponse)
//                .toList();

        List<JogoResponseDto> dtos = new ArrayList<>();

        for (Jogo jogo : entidades) {
            JogoResponseDto response = toResponse(jogo);
            dtos.add(response);
        }

        return dtos;
    }
}
