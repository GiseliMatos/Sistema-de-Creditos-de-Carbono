package model;

import enumpac.StatusValidacao;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "validacao")
public class Validacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_auditor")
    private Auditor auditor;

    @OneToOne
    @JoinColumn(name = "id_acao")
    private AcaoSustentavel acao;

    private LocalDate dataValidacao;
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusValidacao status;

    public Validacao() {
        this.status = StatusValidacao.PENDENTE;
    }

    public Validacao(Auditor auditor, AcaoSustentavel acao, LocalDate dataValidacao, String observacao) {
        this.auditor = auditor;
        this.acao = acao;
        this.dataValidacao = dataValidacao;
        this.observacao = observacao;
        this.status = StatusValidacao.PENDENTE;
    }

    public Long getId() {
        return id;
    }

    public Auditor getAuditor() {
        return auditor;
    }

    public void setAuditor(Auditor auditor) {
        this.auditor = auditor;
    }

    public AcaoSustentavel getAcao() {
        return acao;
    }

    public void setAcao(AcaoSustentavel acao) {
        this.acao = acao;
    }

    public LocalDate getDataValidacao() {
        return dataValidacao;
    }

    public void setDataValidacao(LocalDate dataValidacao) {
        this.dataValidacao = dataValidacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusValidacao getStatus() {
        return status;
    }

    public void setStatus(StatusValidacao status) {
        this.status = status;
    }

    public void aprovar() {
        this.status = StatusValidacao.APROVADO;
        this.dataValidacao = LocalDate.now();
    }

    public void rejeitar() {
        this.status = StatusValidacao.REJEITADO;
        this.dataValidacao = LocalDate.now();
    }

    public boolean isAprovada() {
        return this.status == StatusValidacao.APROVADO;
    }

    public boolean isRejeitada() {
        return this.status == StatusValidacao.REJEITADO;
    }

    public boolean isPendente() {
        return this.status == StatusValidacao.PENDENTE;
    }

    @Override
    public String toString() {
        return "Validacao {" +
                "Id: " + id +
                ", Auditor: " + (auditor != null ? auditor.getNome() : "null") +
                ", Acao: " + (acao != null ? acao.getTipo() : "null") +
                ", Data validacao: " + dataValidacao +
                ", Status: " + status +
                ", Observacao: " + observacao +
                '}';
    }
}
