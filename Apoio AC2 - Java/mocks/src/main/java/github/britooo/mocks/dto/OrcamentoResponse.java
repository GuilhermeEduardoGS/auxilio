package github.britooo.mocks.dto;

public class OrcamentoResponse {

    private String codigo;
    private Double valorTotal;

    public OrcamentoResponse(String codigo, Double valorTotal) {
        this.codigo = codigo;
        this.valorTotal = valorTotal;
    }

    public String getCodigo() {
        return codigo;
    }

    public Double getValorTotal() {
        return valorTotal;
    }
}