package school.sptech.exemplo_lombok;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest dto){

        if (dto == null){
            return null;
        }

       Usuario entity = Usuario.builder()
                .email(dto.email())
                .nome(dto.nome())
                .build();

        return entity;
    }
}
