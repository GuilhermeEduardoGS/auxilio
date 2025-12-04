package github.britooo.treino_prova.empresa.service;

import static org.junit.jupiter.api.Assertions.*;

import github.britooo.treino_prova.empresa.dto.EmpresaMapper;
import github.britooo.treino_prova.empresa.entity.Empresa;
import github.britooo.treino_prova.empresa.repository.EmpresaRepository;
import github.britooo.treino_prova.exception.EntidadeNaoEncontradaException;
import github.britooo.treino_prova.jogo.entity.Jogo;
import github.britooo.treino_prova.jogo.repository.JogoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class EmpresaServiceTest {

    @InjectMocks
    private EmpresaService eService;

    @Mock
    private EmpresaRepository eRepository;

    @Mock
    private EmpresaMapper eMapper;

    @Mock
    private JogoRepository jRepository;


    @Test
    @DisplayName("Deve trazer a lista cheia")
    void deveListarTudoTest(){

        List<Empresa> listaMockada = List.of(new Empresa());
        Mockito.when(eRepository.findAll()).thenReturn(listaMockada);

        List<Empresa> resultado = eService.listar();

        assertEquals(1, resultado.size());

    }

    @Test
    @DisplayName("Deve retornar a lista de empresas vazia")
    void deveRetornarListaVaziaTest(){

        Mockito.when(eRepository.findAll()).thenReturn(List.of());

        List<Empresa> resultado = eService.listar();

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Deve buscar por Id quando acionado com Id valido deve retornar empresa")
    void deveBuscarPoridQuandoAcionadoComIdValidoDeveRetornarEmpresaTest(){

        Empresa teste = new Empresa();
        teste.setId(1L);
        teste.setNome("Vibe codding");
        teste.setNome("Lero Lero");

        Mockito.when(eRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(teste));

        Empresa resultado = eService.buscarPorId(1L);

        assertEquals(teste.getNome(), resultado.getNome());
    }

    @Test
    @DisplayName("Deve buscar por Id quando acionado com Id invalido deve lançar exceção")
    void deveLancarExcecaoQuandoIdForInvalido(){

        Mockito.when(eRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(
            EntidadeNaoEncontradaException.class,
                ()-> eService.buscarPorId(200L),
                "Devera lançar exceção se o id não existir"
        );

        assertEquals("Empresa de id %d não encontrada".formatted(200L), exception.getMessage());
    }

    @Test
    @DisplayName("Deve deletar a empresa pelo Id")
    void deveDeletarEmpresaPorId(){

        Mockito.when(eRepository.existsById(Mockito.anyLong())).thenReturn(true);

        Mockito.doNothing().when(jRepository).removerJogosPorEmpresaID(Mockito.anyLong());
        Mockito.doNothing().when(eRepository).deleteById(Mockito.anyLong());

        eService.deletarPorId(1L);

        Mockito.verify(eRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verify(jRepository, Mockito.times(1)).removerJogosPorEmpresaID(Mockito.anyLong());
        Mockito.verify(eRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    @DisplayName("Deve lançar excecao se tentar excluir e nao existir id")
    void deveLancarExcecaoEntidadeNaoEncontradaExceptionSeNaoEncontrarTest(){

        Mockito.when(eRepository.existsById(Mockito.anyLong())).thenReturn(false);

        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                ()-> eService.deletarPorId(200L),
                "Devera lançar exceção se o id não existir"
        );

        assertEquals("Empresa não encontrada", exception.getMessage());

    }

    @Test
    @DisplayName("Deve cadastrar uma empresa")
    void cadastrarEmpresa(){

        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setNome("From Software");
        empresa.setCnpj("42.591.651/0001-43");

        Mockito.when(eRepository.save(empresa)).thenReturn(empresa);

        Empresa resultado = eService.cadastrar(empresa);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("From Software", empresa.getNome());
        assertEquals("42.591.651/0001-43", empresa.getCnpj());

    }

}