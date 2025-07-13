package model;

import jakarta.persistence.*;

@Entity
@Table(name = "auditor")

public class Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String registroProfissional;

    public Auditor() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }

    public Auditor(String nome, String email, String registroProfissional) {
        this.nome = nome;
        this.email = email;
        this.registroProfissional = registroProfissional;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Auditor{" +
                "Id: " + id +
                ", Nome: '" + nome +
                ", Email: '" + email +
                ", Registro profissional: " + registroProfissional +
                '}';
    }
}
