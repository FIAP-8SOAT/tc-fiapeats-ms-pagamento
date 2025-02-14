package br.com.fiap.fiapeats.external.integration.feign.response;

public class CategoriaResponse {
    private final Long id;
    private final String descricao;

    public CategoriaResponse(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
