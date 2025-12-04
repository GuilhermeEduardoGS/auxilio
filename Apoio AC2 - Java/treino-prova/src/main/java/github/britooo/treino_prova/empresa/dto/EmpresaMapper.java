package github.britooo.treino_prova.empresa.dto;

import github.britooo.treino_prova.empresa.entity.Empresa;

import java.util.List;

public class EmpresaMapper {

    public static EmpresaResponseDto toResponse(Empresa empresa) {
        if (empresa==null) {
            return null;
        }
        EmpresaResponseDto dto = new EmpresaResponseDto();
        dto.setId(empresa.getId());
        dto.setNome(empresa.getNome());
        return dto;
    }

    public static List<EmpresaResponseDto> toResponse(List<Empresa> empresas) {
        return empresas.stream()
                .map(EmpresaMapper::toResponse)
                .toList();
    }

    public static Empresa toEntity(EmpresaRequestDto dto) {
        if (dto==null) {
            return null;
        }
        Empresa empresa = new Empresa();
        empresa.setNome(dto.getNome());
        empresa.setCnpj(dto.getCnpj());
        return empresa;
    }

    public static Empresa toEntity(EmpresaRequestDto dto, Long id) {
        Empresa empresa = toEntity(dto);
        empresa.setId(id);
        return empresa;
    }

}
