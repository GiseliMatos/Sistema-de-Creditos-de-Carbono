package enumpac;

public enum TipoAcao {
    PAPEL("papel"),
    PLASTICO("plastico"),
    VIDRO("vidro"),
    METAL("metal"),
    COMPOSTAGEM("compostagem"),
    ENERGIA_SOLAR("energiaSolar"),
    TRANSPORTE_SUSTENTAVEL("transporteSustentavel"),
    OUTRO("outro");

    private final String tipo;

    private TipoAcao(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }

    public static TipoAcao fromInt(int opcao) {
        switch (opcao) {
            case 1: return PAPEL;
            case 2: return PLASTICO;
            case 3: return VIDRO;
            case 4: return METAL;
            case 5: return COMPOSTAGEM;
            case 6: return ENERGIA_SOLAR;
            case 7: return TRANSPORTE_SUSTENTAVEL;
            case 8: return OUTRO;
            default: throw new IllegalArgumentException("Opção inválida para TipoAcao: " + opcao);
        }
    }
}
