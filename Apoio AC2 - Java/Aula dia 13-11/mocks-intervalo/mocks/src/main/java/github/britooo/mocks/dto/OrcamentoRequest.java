package github.britooo.mocks.dto;

public class OrcamentoRequest {

    private Integer quantidade;
    private Double precoUnitario;

    public OrcamentoRequest() {}

    public OrcamentoRequest(Integer quantidade, Double precoUnitario) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}

