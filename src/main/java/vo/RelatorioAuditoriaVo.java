package vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class RelatorioAuditoriaVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nomeAuditor;
    private String registroAuditor;
    private int totalAcoesValidadas;
    private int totalAcoesRejeitadas;
    private Map<String, Double> taxaAprovacaoPorTipo;
    private LocalDate dataRelatorio;

    public RelatorioAuditoriaVo(String nomeAuditor, String registroAuditor, 
            int totalAcoesValidadas, int totalAcoesRejeitadas, 
            Map<String, Double> taxaAprovacaoPorTipo, LocalDate dataRelatorio) {
        this.nomeAuditor = nomeAuditor;
        this.registroAuditor = registroAuditor;
        this.totalAcoesValidadas = totalAcoesValidadas;
        this.totalAcoesRejeitadas = totalAcoesRejeitadas;
        this.taxaAprovacaoPorTipo = taxaAprovacaoPorTipo;
        this.dataRelatorio = dataRelatorio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== Relatório de Auditoria ==========\n");
        sb.append("Auditor: ").append(nomeAuditor).append(" (Registro: ").append(registroAuditor).append(")\n");
        sb.append("Ações Validadas: ").append(totalAcoesValidadas).append("\n");
        sb.append("Ações Rejeitadas: ").append(totalAcoesRejeitadas).append("\n");
        sb.append("Taxa de Aprovação por Tipo de Ação:\n");
        
        taxaAprovacaoPorTipo.forEach((tipo, taxa) -> {
            sb.append("  - ").append(tipo).append(": ")
              .append(String.format("%.2f%%", taxa * 100)).append("\n");
        });
        
        sb.append("Data do Relatório: ").append(dataRelatorio);
        return sb.toString();
    }
}
