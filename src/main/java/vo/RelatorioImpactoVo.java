package vo;

import java.io.Serializable;
import java.time.LocalDate;

public class RelatorioImpactoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nomeUser;
    private Long totalAcoes;
    private double totalCreditos;
    private LocalDate dataUltimaAtualizacao;

    public RelatorioImpactoVo(String nomeUsuario, Long totalAcoes, double totalCreditos, LocalDate dataAtualizacao) {
        this.nomeUser = nomeUsuario;
        this.totalAcoes = totalAcoes;
        this.totalCreditos = totalCreditos;
        this.dataUltimaAtualizacao = dataAtualizacao;
    }

    @Override
    public String toString(){
        return "========== Relatório de Impacto ==========\n"
                + "Nome do usuário: " + nomeUser + "\n"
                + "Total ações: " + totalAcoes + "\n"
                + "Total créditos: " + totalCreditos + "\n"
                + "Data de atualização: " + dataUltimaAtualizacao;
    }
}
