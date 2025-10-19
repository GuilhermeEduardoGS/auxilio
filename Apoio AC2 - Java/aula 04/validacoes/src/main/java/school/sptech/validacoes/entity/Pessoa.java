package school.sptech.validacoes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank // nao tem relacao a banco de dados (nao tem relacao com o JPA)
    @Size(min = 2) // Size para Varchar e Lenght funciona pro resto // Tem Max tbm :D
    private String nome; // nao quero: null, "", "   "

    @CPF
    @NotBlank
    private String cpf;

    @NotNull
    @Past
//    @Past(message = "Insira a mensagem aqui :D") se quiser traduzir vai ter q ser na mão
//    @PastOrPresent
//    @Future
//    @FutureOrPresent
    private LocalDate dataNascimento;

    @Positive
    @Min(value = 1518)
    private Double salario;

    @NotNull
    @Email
    private String email;

    @CNPJ
    private String cnpj;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
