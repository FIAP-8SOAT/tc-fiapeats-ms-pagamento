package br.com.fiap.fiapeats.domain.entities;

public class PagamentoPedido {

    private String statusPagamento;
    private long idPagamento;
    private String qrCode;

    public PagamentoPedido(String statusPagamento, long idPagamento, String qrCode) {
        this.statusPagamento = statusPagamento;
        this.idPagamento = idPagamento;
        this.qrCode = qrCode;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public long getIdPagamento() {
        return idPagamento;
    }

    public String getQrCode() {
        return qrCode;
    }
}
