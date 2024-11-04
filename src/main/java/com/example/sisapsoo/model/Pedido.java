package com.example.sisapsoo.model;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;


@Entity
 public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoSalgado> pedidoSalgados= new ArrayList<>();;

    private Double preco;

    private String status;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Endereco loc;

    @ManyToOne
    @JoinColumn(name = "cliente_fk", referencedColumnName = "id")
    private Cliente cliente;


     public Integer getIdPedido() {

         return idPedido;
     }

     public void setIdPedido(Integer idPedido) {

         this.idPedido = idPedido;
     }

     public Double getPreco() {
         return preco;
     }

     public void setPreco(Double preco) {
         this.preco = preco;
     }

     public String getStatus() {
         return status;
     }

    public void setStatus(String status) {
         this.status = status;
     }

     public Cliente getCliente() {
         return cliente;
     }

     public void setCliente(Cliente cliente) {
         this.cliente = cliente;
     }

    public Double calcularPrecoTotal() {
        return pedidoSalgados.stream()
                .mapToDouble(PedidoSalgado::calcularSubtotal)
                .sum();
    }

    public Endereco getLoc() {
        return loc;
    }

    public void setLoc(Endereco loc) {
        this.loc = loc;
    }

    public List<PedidoSalgado> getPedidoSalgados() {
        return pedidoSalgados;
    }

    public void setPedidoSalgados(List<PedidoSalgado> pedidoSalgados) {
        this.pedidoSalgados = pedidoSalgados;
    }
}

