package com.example.sisapsoo;

import com.example.sisapsoo.controller.CadastroFuncController;
import com.example.sisapsoo.controller.GerenciamentoFuncs;
import com.example.sisapsoo.controller.LoginController;
// import com.example.sisapsoo.model.Gerente;
// import com.example.sisapsoo.model.dao.GerenteDAO;
import com.example.sisapsoo.model.*;
import com.example.sisapsoo.model.dao.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HelloApplication extends Application {
     @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Salgado Smart");

//        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/images/coxinha.png")));
//        stage.getIcons().add(icon);

        // adicionando um funcionario gerente
        Gerente g = new Gerente();
        GerenteDAO gDAO = new GerenteDAO();

        g.setSenha(hashPassword("1234"));
        g.setNome("jose");
        g.setCpf("11");
        g.setTelefone("2333");
        g.setSalario(98.0);
        gDAO.save(g);

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
