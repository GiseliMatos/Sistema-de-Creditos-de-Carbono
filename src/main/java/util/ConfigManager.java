package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import enumpac.TipoAcao;

import java.io.InputStream;

public class ConfigManager {

    private static AppConfig config;
    private static boolean loaded = false;

    public static void loadConfig() {
        if (!loaded) {
            try {
                InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("config.json");
                if (is == null) {
                    throw new RuntimeException("Arquivo config.json não encontrado");
                }
                ObjectMapper mapper = new ObjectMapper();
                config = mapper.readValue(is, AppConfig.class);

                loaded = true;
                System.out.println("Configurações carregadas com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao carregar configurações: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static String getSistemaNome() {
        loadConfig();
        return config.sistema.nome;
    }

    private static String mapTipoParaChave(String tipo) {
        if (tipo == null) {
            return "";
        }
        String tipoNormalizado = tipo.toLowerCase();
        if (tipoNormalizado.equals("transportesustentavel")) {
            return "transporteSustentavel";
        }
        if (tipoNormalizado.equals("energiasolar")) {
            return "energiaSolar";
        }
        return tipoNormalizado;
    }

    private static String mapTipoParaChave(TipoAcao tipoAcao) {
        if (tipoAcao == null) {
            return "";
        }
        return tipoAcao.getTipo();
    }

    public static double getTaxa(String tipo) {
        loadConfig();
        String chave = mapTipoParaChave(tipo);
        double valor = config.taxasConversao.getOrDefault(chave, 0.0);
        return valor;
    }

    public static double getTaxa(TipoAcao tipoAcao) {
        loadConfig();
        String chave = mapTipoParaChave(tipoAcao);
        double valor = config.taxasConversao.getOrDefault(chave, 0.0);
        return valor;
    }

    public static int getLimiteMensal(String tipo) {
        loadConfig();
        String chave = mapTipoParaChave(tipo);
        int valor = config.limitesMensais.getOrDefault(chave, 0);
        return valor;
    }

    public static int getLimiteMensal(TipoAcao tipoAcao) {
        loadConfig();
        String chave = mapTipoParaChave(tipoAcao);
        int valor = config.limitesMensais.getOrDefault(chave, 0);
        return valor;
    }

    public static int getValorMinimo(String tipo) {
        loadConfig();
        String chave = mapTipoParaChave(tipo);
        return config.valoresMinimos.getOrDefault(chave, 0);
    }

    public static int getValorMinimo(TipoAcao tipoAcao) {
        loadConfig();
        String chave = mapTipoParaChave(tipoAcao);
        return config.valoresMinimos.getOrDefault(chave, 0);
    }
}
