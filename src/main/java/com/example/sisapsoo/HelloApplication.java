package com.example.sisapsoo;

import com.example.sisapsoo.controller.LoginController;
import com.example.sisapsoo.model.Estoque;
import com.example.sisapsoo.model.Relatorio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.example.sisapsoo.builder.FuncionarioBuilder;
import com.example.sisapsoo.builder.GerenteBuilder;
import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.Gerente;

public class HelloApplication extends Application {

    private Funcionario[] funcionarios;
    private Gerente[] gerentes;

    @Override
    public void start(Stage stage) throws Exception {
        initializeUsers();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Salgado Smart");

//        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/images/coxinha.png")));
//        stage.getIcons().add(icon);

        LoginController controller = fxmlLoader.getController();
        controller.setFuncionarios(funcionarios);
        controller.setGerentes(gerentes);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void initializeUsers() {
        Relatorio[] relatorios = new Relatorio[0];
        Estoque estoque = new Estoque();

        gerentes = new Gerente[] {
                new GerenteBuilder()
                        .setId("gerente1")
                        .setSenha("senhaGerente1")
                        .setNome("Gerente 1")
                        .setCpf("123.456.789-00")
                        .setTelefone("11987654321")
                        .setSalario(5000)
                        .setRelatorios(relatorios)
                        .setEstoque(estoque)
                        .build()
        };

        funcionarios = new Funcionario[] {
                new FuncionarioBuilder()
                        .setId("func1")
                        .setSenha("senhaFunc1")
                        .setNome("Funcionario 1")
                        .setCpf("987.654.321-00")
                        .setTelefone("11912345678")
                        .setSalario(3000)
                        .build(),
                new FuncionarioBuilder()
                        .setId("func2")
                        .setSenha("senhaFunc2")
                        .setNome("Funcionario 2")
                        .setCpf("987.654.321-11")
                        .setTelefone("11923456789")
                        .setSalario(3200)
                        .build()
        };
    }

    public static void main(String[] args) {
        launch();
    }
}
