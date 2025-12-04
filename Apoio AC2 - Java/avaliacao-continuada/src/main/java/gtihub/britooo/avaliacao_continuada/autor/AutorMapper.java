package gtihub.britooo.avaliacao_continuada.autor;

import java.util.List;

public class AutorMapper {
    public static Autor toEntity(AutorRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return new Autor(null, dto.getNome());
    }

    public static Autor toEntity(AutorRequestDto dto, int id) {
        if (dto == null) {
            return null;
        }
        Autor entity = toEntity(dto);
        entity.setId(id);
        return entity;
    }

    public static AutorResponseDto toResponseDto(Autor entity) {
        if (entity == null) {
            return null;
        }

//        return new AutorResponseDto(entity.getId(), entity.getNome());
        return null;
    }

    public static List<AutorResponseDto> toResponseDto(List<Autor> entities) {
        return entities.stream()
                .map(AutorMapper::toResponseDto)
                .toList();
    }
}
