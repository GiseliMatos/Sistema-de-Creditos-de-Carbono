package model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="credito_carbono")

public class CreditoCarbono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private User user;

    private double totalCreditos;
    private LocalDate ultimaAtualizacao;

    public CreditoCarbono() {}

    public double getTotalCreditos() {
        return totalCreditos;
    }

    public void setTotalCreditos(double totalCreditos) {
        this.totalCreditos = totalCreditos;
    }

    public LocalDate getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDate ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CreditoCarbono{" +
                "Id:  " + id +
                ", User: " + (user != null ? user.getNome() : "null") +
                ", Total creditos: " + totalCreditos +
                ", Ultima atualizacao: " + ultimaAtualizacao +
                '}';
    }
}
