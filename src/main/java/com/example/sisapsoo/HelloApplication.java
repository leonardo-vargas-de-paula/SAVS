package com.example.sisapsoo;

import com.example.sisapsoo.controller.CadastroFuncController;
import com.example.sisapsoo.controller.GerenciamentoFuncs;
import com.example.sisapsoo.controller.LoginController;
// import com.example.sisapsoo.model.Gerente;
// import com.example.sisapsoo.model.dao.GerenteDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Salgado Smart");

        LoginController controller = fxmlLoader.getController();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
