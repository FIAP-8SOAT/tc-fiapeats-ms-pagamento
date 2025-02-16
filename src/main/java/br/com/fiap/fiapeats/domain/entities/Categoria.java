package br.com.fiap.fiapeats.domain.entities;

public class Categoria {

    private Long id;
    private String descricao;

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
