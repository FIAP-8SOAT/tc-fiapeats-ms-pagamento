package br.com.fiap.fiapeats.domain.entities;

public class Categoria {

    private final Long id;
    private final String descricao;

    public Categoria(Long id, String descricao) {
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
