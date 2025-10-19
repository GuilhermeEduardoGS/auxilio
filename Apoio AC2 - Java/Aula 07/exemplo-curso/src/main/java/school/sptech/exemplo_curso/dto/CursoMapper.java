package school.sptech.exemplo_curso.dto;

import school.sptech.exemplo_curso.entity.Curso;

import java.util.List;

public class CursoMapper {

    public static CursoListagemDto toListagemDto(Curso entity) {

        if (entity == null) {
            return null;
        }

        return new CursoListagemDto(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getCategoria()
        );
    }

    public static List<CursoListagemDto> toListagemDtos(List<Curso> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(CursoMapper::toListagemDto)
                .toList();
    }

    public static Curso toEntity(CursoCadastroDto dto) {
        if (dto == null) {
            return null;
        }

        return new Curso(
                null,
                dto.getNome(),
                dto.getDescricao(),
                dto.getPreco(),
                dto.getCategoria()
        );
    }

    public static Curso toEntity(CursoAtualizacaoDto dto, Integer id) {
        if (dto == null) {
            return null;
        }

        return new Curso(
                id,
                dto.getNome(),
                dto.getDescricao(),
                dto.getPreco(),
                dto.getCategoria()
        );
    }
}
