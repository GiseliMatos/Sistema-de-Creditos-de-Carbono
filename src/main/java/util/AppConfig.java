package util;

import java.util.Map;

public class AppConfig {
    public Sistema sistema;
    public Map<String, Double> taxasConversao;
    public Map<String, Integer> limitesMensais;
    public Map<String, Integer> valoresMinimos;

    public static class Sistema {
        public String nome;
        public String versao;
        public String desenvolvedor;
    }
}
