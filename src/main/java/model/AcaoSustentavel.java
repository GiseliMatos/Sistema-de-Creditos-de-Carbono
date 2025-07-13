package model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "acao_sustentavel")

public class AcaoSustentavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private String descricao;
    private LocalDate data;
    private double quantidade;
    private double creditosGerados;
    private boolean validado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;

    public AcaoSustentavel() {}

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setCreditosGerados(double creditosGerados) {
        this.creditosGerados = creditosGerados;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "AcaoSustentavel{" +
                "id = " + id +
                ", tipo = '" + tipo + '\'' +
                ", descricao = '" + descricao + '\'' +
                ", data = " + data +
                ", quantidade = " + quantidade +
                ", creditosGerados = " + creditosGerados +
                ", validado = " + validado +
                ", user = " + (user != null ? user.getNome() : "null") +
                '}';
    }
}
