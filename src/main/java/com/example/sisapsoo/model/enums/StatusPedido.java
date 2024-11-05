package com.example.sisapsoo.model.enums;

public enum StatusPedido {
    EM_ESPERA("Em espera"),
    AGUARDANDO_PAGAMENTO("Aguardando pagamento"),
    FINALIZADO("Finalizado");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para converter de String para StatusPedido
    public static StatusPedido fromString(String status) {
        for (StatusPedido sp : StatusPedido.values()) {
            if (sp.getDescricao().equals(status)) {
                return sp;
            }
        }
        return null; // ou lance uma exceção, se preferir
    }
}