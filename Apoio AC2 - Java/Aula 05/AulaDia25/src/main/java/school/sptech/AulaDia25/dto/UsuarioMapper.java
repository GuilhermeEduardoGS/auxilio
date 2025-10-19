package school.sptech.AulaDia25.dto;

import school.sptech.AulaDia25.Entity.Usuario;

import java.util.List;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDto dto){

        if (dto == null){
            return null;
        }

        Usuario entity = new Usuario();
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());

        return entity;
    }

    public static UsuarioResponseDto toResponseDto(Usuario entity){

        if (entity == null){
            return null;
        }

        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());

        return dto;
    }

    public static List<UsuarioResponseDto> toResponseDto(List<Usuario> entity){
        return entity.stream()
                .map(UsuarioMapper::toResponseDto)
                .toList();
    }
}
