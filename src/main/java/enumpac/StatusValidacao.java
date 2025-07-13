package enumpac;

public enum StatusValidacao {
    APROVADO("Aprovado"),
    REJEITADO("Rejeitado"),
    PENDENTE("Pendente");

    private final String status;

    StatusValidacao(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
