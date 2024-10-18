package com.example.sisapsoo;

import com.example.sisapsoo.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.Gerente;
import com.example.sisapsoo.model.Usuario;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
import com.example.sisapsoo.model.dao.UsuarioDAO;

public class HelloApplication extends Application {

    private Funcionario[] funcionarios;
    private Gerente[] gerentes;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Salgado Smart");

//        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/images/coxinha.png")));
//        stage.getIcons().add(icon);

        UsuarioDAO uDAO = new UsuarioDAO();
        Usuario u = new Usuario("1", "123");
        uDAO.save(u);

        FuncionarioDAO fDao = new FuncionarioDAO();
        Funcionario f = new Funcionario("1", "joao", "1234", null, 25.5, u);
        fDao.save(f);

        LoginController controller = fxmlLoader.getController();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
