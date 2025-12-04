package github.britooo.mocks.service;

import github.britooo.mocks.entity.Orcamento;
import github.britooo.mocks.repository.OrcamentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrcamentoServiceTest {

    /*
    * Existem vários tipos de dublês:
    *
    * - Mock;
    * - Dummy;
    * - Spy
    *
    * */

    @Mock // Dublê
    private OrcamentoRepository repository;

    @InjectMocks
    private OrcamentoService service;

    @Test
    @DisplayName("Quando tabela estiver vazia deve retornar lista vazia")
    void deveRetornarListaVaziaTest(){

        // arrange
        List<Orcamento> resultado = new ArrayList<>();

        Mockito.when(repository.findAll())
                .thenReturn(resultado);

        // act
        List<Orcamento> recebido = service.listarOrcamentos();

        // assert
        Assertions.assertTrue(recebido.isEmpty());

    }

    @Test
    @DisplayName("Quando a tabela tiver itens deve retornar a lista")
    void deveRetornarListaComOrcamentos(){

        Orcamento orcamento = new Orcamento();
        orcamento.setQuantidade(10);
        orcamento.setPrecoUnitario(10.0);
        orcamento.setCodigo("ORC-23132131");

        List<Orcamento> resultado = new ArrayList<>();

        resultado.add(orcamento);

        Mockito.when(repository.findAll())
                .thenReturn(resultado);

        List<Orcamento> recebido = service.listarOrcamentos();

        Assertions.assertFalse(recebido.isEmpty());

    }

}