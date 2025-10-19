package school.sptech.exemplo_strategy.mapper;

import school.sptech.exemplo_strategy.dto.FreteRequestDto;
import school.sptech.exemplo_strategy.dto.FreteResponseDto;
import school.sptech.exemplo_strategy.dto.FreteResponseGetDto;
import school.sptech.exemplo_strategy.entity.Frete;
import school.sptech.exemplo_strategy.entity.TipoFreteEnum;

import java.util.List;

public class FreteMapper {

    public static Frete toEntity(FreteRequestDto dto) {
        if (dto == null) return null;

        Frete entity = new Frete();
        entity.setTipo(TipoFreteEnum.valueOf(dto.getTipo()));
        entity.setPesoEmKg(dto.getPesoEmKg());

        return entity;
    }

    public static FreteResponseDto toResponseDtoComDesc(Frete entity) {
        if (entity == null) return null;

        FreteResponseDto dto = new FreteResponseDto();
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo().name());
        dto.setValorFrete(entity.getValorFrete());
        dto.setDescricao(entity.getTipo().getDescricao());
        return dto;
    }

    public static FreteResponseGetDto toResponseGetDto(Frete entity) {
        if (entity == null) return null;

        FreteResponseGetDto dto = new FreteResponseGetDto();
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo().name());
        dto.setValorFrete(entity.getValorFrete());
        return dto;
    }

    public static List<FreteResponseGetDto> toResponseGetDto(List<Frete> entities) {
        return entities.stream()
                .map(FreteMapper::toResponseGetDto)
                .toList();
    }
}