package service;

import dao.AcaoSustentavelDao;
import jakarta.persistence.EntityManager;
import model.AcaoSustentavel;
import util.ConfigManager;
import enumpac.TipoAcao;

public class AcaoSustentavelService extends GenericService<AcaoSustentavel> {
    private AcaoSustentavelDao dao;

    public AcaoSustentavelService(EntityManager em) {
        super(em, AcaoSustentavel.class);
        this.dao = new AcaoSustentavelDao(em);
    }

    private TipoAcao mapStringToTipoAcao(String tipo) {
        if (tipo == null) {
            return TipoAcao.OUTRO;
        }
        for (TipoAcao ta : TipoAcao.values()) {
            if (ta.getTipo().equalsIgnoreCase(tipo)) {
                return ta;
            }
        }
        return TipoAcao.OUTRO;
    }

    public boolean validarQuantidade(AcaoSustentavel acao) {
        TipoAcao tipoAcao = mapStringToTipoAcao(acao.getTipo());

        int minimo = ConfigManager.getValorMinimo(tipoAcao);

        if (minimo == 0) {
            return acao.getQuantidade() > 0;
        }

        if (acao.getQuantidade() < minimo) {
            return false;
        }

        int limite = ConfigManager.getLimiteMensal(tipoAcao);

        return acao.getQuantidade() <= limite;
    }

    public double calcularCreditos(AcaoSustentavel acao) {
        TipoAcao tipoAcao = mapStringToTipoAcao(acao.getTipo());
        double taxa = ConfigManager.getTaxa(tipoAcao);

        return acao.getQuantidade() * taxa;
    }
}