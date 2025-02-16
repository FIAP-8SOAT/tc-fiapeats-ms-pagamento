package br.com.fiap.fiapeats.external.integration.feign.response;

public class PagamentoResponse {

    private String statusPagamento;
    private Long idPagamento;
    private String qrCode;

    public PagamentoResponse(String statusPagamento, Long idPagamento, String qrCode) {
        this.statusPagamento = statusPagamento;
        this.idPagamento = idPagamento;
        this.qrCode = qrCode;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public String getQrCode() {
        return qrCode;
    }
}
