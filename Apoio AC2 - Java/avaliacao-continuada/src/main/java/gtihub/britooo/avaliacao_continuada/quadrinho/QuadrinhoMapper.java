package gtihub.britooo.avaliacao_continuada.quadrinho;

import gtihub.britooo.avaliacao_continuada.autor.Autor;

import java.util.List;

// COMPLEMENTAR
public class QuadrinhoMapper {

    public static Quadrinho toEntity(QuadrinhoRequestDto dto) {

        if (dto == null){
            return null;
        }

        Quadrinho quadrinho = new Quadrinho();

        quadrinho.setDataLancamento(dto.getDataLancamento());
        quadrinho.setNota(dto.getNota());
        quadrinho.setIsbn(dto.getIsbn());
        quadrinho.setTitulo(dto.getTitulo());

        return quadrinho;

    }

    public static Quadrinho toEntity(QuadrinhoRequestDto dto, Integer id) {

        if (dto == null){
            return null;
        }

        Quadrinho quadrinho = toEntity(dto);
        quadrinho.setId(id);
        return quadrinho;

    }

    public static QuadrinhoResponseDto toResponseDto(Quadrinho entity) {

        if (entity == null){
            return null;
        }

        QuadrinhoResponseDto quadrinhoResponseDto = new QuadrinhoResponseDto();

        quadrinhoResponseDto.setId(entity.getId());
        quadrinhoResponseDto.setTitulo(entity.getTitulo());
        quadrinhoResponseDto.setIsbn(entity.getIsbn());
        quadrinhoResponseDto.setNota(entity.getNota());
        quadrinhoResponseDto.setDataLancamento(entity.getDataLancamento());

        Autor autorEntidade = entity.getAutor();

        QuadrinhoResponseDto.AutorInfoDto autorInfoDto = new QuadrinhoResponseDto.AutorInfoDto();

        autorInfoDto.setId(autorEntidade.getId());
        autorInfoDto.setNome(autorEntidade.getNome());

        quadrinhoResponseDto.setAutor(autorInfoDto);

        return quadrinhoResponseDto;


    }

    public static List<QuadrinhoResponseDto> toResponseDto(List<Quadrinho> entities) {
        return entities.stream()
                .map(QuadrinhoMapper::toResponseDto)
                .toList();
    }
}
