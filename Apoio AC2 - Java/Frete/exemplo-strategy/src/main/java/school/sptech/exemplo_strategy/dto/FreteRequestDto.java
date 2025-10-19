package school.sptech.exemplo_strategy.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FreteRequestDto {

    @NotBlank(message = "O tipo de frete é obrigatório!")
    private String tipo;

    @NotNull(message = "O valor da encomenda é obrigatório!")
    @DecimalMin(value = "10.0", inclusive = false,  message = "O valor da encomenda deve ser maior que R$10,00")
    private Double valorEncomenda;

    @NotNull(message = "O valor do peso é obrigatório!")
    @DecimalMin(value = "1.0", inclusive = false, message = "O valor do peso deve ser maior que 1Kg")
    private Double pesoEmKg;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValorEncomenda() {
        return valorEncomenda;
    }

    public void setValorEncomenda(Double valorEncomenda) {
        this.valorEncomenda = valorEncomenda;
    }

    public Double getPesoEmKg() {
        return pesoEmKg;
    }

    public void setPesoEmKg(Double pesoEmKg) {
        this.pesoEmKg = pesoEmKg;
    }
}
