package model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")

public class User {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String cpfCnpj;
    private String tipo; // PF, organização, PJ
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AcaoSustentavel> acoes = new ArrayList<>();

    public User(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return id + " - " + nome + " - " + email + " - " + cpfCnpj + " - " + tipo;
    }
}
