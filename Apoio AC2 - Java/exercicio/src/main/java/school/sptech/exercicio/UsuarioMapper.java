package school.sptech.exercicio;

public class UsuarioMapper {
    public static UsuarioResposta mapearUsuario(Usuario usuario){
        return new UsuarioResposta(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getDataNascimento());
    }
}
