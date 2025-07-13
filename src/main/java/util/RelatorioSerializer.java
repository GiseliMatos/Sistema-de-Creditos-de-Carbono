package util;

import vo.RelatorioImpactoVo;
import vo.RelatorioAuditoriaVo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioSerializer {
    private static final String RELATORIOS_DIR = "relatorios/";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    static {
        File dir = new File(RELATORIOS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static String serializarRelatorioImpacto(List<RelatorioImpactoVo> relatorio) {
        if (relatorio == null || relatorio.isEmpty()) {
            System.out.println("Nenhum dado para serializar no relatório de impacto.");
            return null;
        }
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String filename = RELATORIOS_DIR + "relatorio_impacto_" + timestamp + ".ser";
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(relatorio);
            System.out.println("Relatório de Impacto serializado em: " + filename);
            return filename;
        } catch (IOException e) {
            System.err.println("Erro ao serializar relatório de impacto: " + e.getMessage());
            return null;
        }
    }

    public static List<RelatorioImpactoVo> deserializarRelatorioImpacto(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            List<RelatorioImpactoVo> relatorio = (List<RelatorioImpactoVo>) ois.readObject();
            System.out.println("Relatório de Impacto deserializado de: " + filename);
            return relatorio;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao deserializar relatório de impacto: " + e.getMessage());
            return null;
        }
    }

    public static String serializarRelatorioAuditoria(List<RelatorioAuditoriaVo> relatorio) {
        if (relatorio == null || relatorio.isEmpty()) {
            System.out.println("Nenhum dado para serializar no relatório de auditoria.");
            return null;
        }
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String filename = RELATORIOS_DIR + "relatorio_auditoria_" + timestamp + ".ser";
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(relatorio);
            System.out.println("Relatório de Auditoria serializado em: " + filename);
            return filename;
        } catch (IOException e) {
            System.err.println("Erro ao serializar relatório de auditoria: " + e.getMessage());
            return null;
        }
    }

    public static List<RelatorioAuditoriaVo> deserializarRelatorioAuditoria(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            List<RelatorioAuditoriaVo> relatorio = (List<RelatorioAuditoriaVo>) ois.readObject();
            System.out.println("Relatório de Auditoria deserializado de: " + filename);
            return relatorio;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao deserializar relatório de auditoria: " + e.getMessage());
            return null;
        }
    }

    public static String serializarRelatorioAuditoriaIndividual(RelatorioAuditoriaVo relatorio) {
        if (relatorio == null) {
            System.out.println("Nenhum dado para serializar no relatório de auditoria individual.");
            return null;
        }
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String filename = RELATORIOS_DIR + "relatorio_auditoria_individual_" + timestamp + ".ser";
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(relatorio);
            System.out.println("Relatório de Auditoria Individual serializado em: " + filename);
            return filename;
        } catch (IOException e) {
            System.err.println("Erro ao serializar relatório de auditoria individual: " + e.getMessage());
            return null;
        }
    }

    public static RelatorioAuditoriaVo deserializarRelatorioAuditoriaIndividual(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            RelatorioAuditoriaVo relatorio = (RelatorioAuditoriaVo) ois.readObject();
            System.out.println("Relatório de Auditoria Individual deserializado de: " + filename);
            return relatorio;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao deserializar relatório de auditoria individual: " + e.getMessage());
            return null;
        }
    }

    public static void listarRelatoriosSalvos() {
        File dir = new File(RELATORIOS_DIR);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".ser"));
        
        if (files == null || files.length == 0) {
            System.out.println("Nenhum relatório salvo encontrado.");
            return;
        }

        System.out.println("\n=== Relatórios Salvos ===");
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName());
        }
    }

    public static File[] obterArquivosRelatorios() {
        File dir = new File(RELATORIOS_DIR);
        return dir.listFiles((d, name) -> name.endsWith(".ser"));
    }

    public static boolean arquivoExiste(String filename) {
        return new File(filename).exists();
    }
}
