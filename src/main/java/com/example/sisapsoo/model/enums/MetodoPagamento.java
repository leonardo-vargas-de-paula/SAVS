package com.example.sisapsoo.model.enums;

public enum MetodoPagamento {

    PIX("Pix"),
    CARTAO_DEBITO("Cartão de débito"),
    CARTAO_CREDITO("Cartão de crédito"),
    DINHEIRO("Dinheiro");

    private final String metodo;

    MetodoPagamento(String metodo) {
        this.metodo = metodo;
    }

    public String getMetodo() {
        return metodo;
    }

    //Metodo para converter de String para MetodoPagamento
    public static MetodoPagamento fromString(String metodo) {
        for (MetodoPagamento mp : MetodoPagamento.values()) {
            if (mp.getMetodo().equals(metodo)) {
                return mp;
            }
        }
        return null; // ou lance uma exceção, se preferir
    }
}
