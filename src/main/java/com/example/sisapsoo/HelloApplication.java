package com.example.sisapsoo;

import com.example.sisapsoo.controller.LoginController;
import com.example.sisapsoo.model.*;
import com.example.sisapsoo.model.dao.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class HelloApplication extends Application {
     @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Salgado Smart");

         Image icon = new Image(getClass().getResourceAsStream("/assets/images/coxinha.png"));
         stage.getIcons().add(icon);

        // adicionando um funcionario gerente
        Gerente g = new Gerente();
        GerenteDAO gDAO = new GerenteDAO();

        g.setSenha(hashPassword("1234"));
        g.setNome("jose");
        g.setCpf("11");
        g.setTelefone("2333");
        g.setSalario(98.0);
        gDAO.save(g);

        //adicionando um salgado
        Salgado s = new Salgado();
        SalgadoDAO sDAO = new SalgadoDAO();

        s.setNome("Esfiha");
        s.setPreco(21);
        sDAO.save(s);

        Salgado s2 = new Salgado();
        SalgadoDAO sDAO2 = new SalgadoDAO();

        s2.setNome("Kibe");
        s2.setPreco(7);
        sDAO2.save(s2);

        //adicionando um cliente
        Cliente c = new Cliente();
        ClienteDAO cDAO = new ClienteDAO();

        c.setNome("Gustavo Pereira Uzumaki");
        c.setTelefone("67993253538");
        cDAO.save(c);

        //criando um pedido relacionado a um cliente (c)
        Pedido p = new Pedido();
        PedidoDAO pDAO = new PedidoDAO();

        p.setCliente(c);
        p.setStatus("teste");
        p.setLoc("av afonso pena 888");
        pDAO.save(p);

        //criando um pedido salgado para o pedido
        PedidoSalgado ps = new PedidoSalgado();
        PedidoSalgadoDAO psDAO = new PedidoSalgadoDAO();

        ps.setSalgado(s);//esfiha
        ps.setPedido(p);
        ps.setQuantidade(3);
        psDAO.save(ps);

        PedidoSalgado ps2 = new PedidoSalgado();
        PedidoSalgadoDAO psDAO2 = new PedidoSalgadoDAO();

        ps2.setSalgado(s2);//kibe
        ps2.setPedido(p);
        ps2.setQuantidade(2);
        psDAO2.save(ps2);

        //adicionando item ao pedido
        p.getPedidoSalgados().add(ps);
        p.getPedidoSalgados().add(ps2);

        //adicionando preco
        p.setPreco(p.calcularPrecoTotal());

        pDAO.save(p);

        LoginController controller = fxmlLoader.getController();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static String hashPassword(String password) {
        try {
            // Cria uma instância do MessageDigest para SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Executa o hash da senha
            byte[] hashBytes = digest.digest(password.getBytes());

            // Converte o hash em uma string hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
