package com.example.sisapsoo;

import com.example.sisapsoo.controller.LoginController;
import com.example.sisapsoo.model.Gerente;
import com.example.sisapsoo.model.dao.GerenteDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.sisapsoo.model.Funcionario;
//import com.example.sisapsoo.model.Gerente;
import com.example.sisapsoo.model.Usuario;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
import com.example.sisapsoo.model.dao.UsuarioDAO;

public class HelloApplication extends Application {

    private Funcionario[] funcionarios;
    //private Gerente[] gerentes;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Salgado Smart");

//        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/images/coxinha.png")));
//        stage.getIcons().add(icon);

        FuncionarioDAO fDAO = new FuncionarioDAO();
        Funcionario f = new Funcionario();
        FuncionarioDAO fDAO2 = new FuncionarioDAO();
        Funcionario f2 = new Funcionario();
        Gerente g = new Gerente();
        GerenteDAO gDAO = new GerenteDAO();


        f.setId("2");
        f.setSenha("1234");
        f.setNome("naruto");
        f.setCpf("00000");
        f.setTelefone("11112222");
        f.setSalario(2.0);
        fDAO.save(f);

        f2.setId("3");
        f2.setSenha("12");
        f2.setNome("goku");
        f2.setCpf("14");
        f2.setTelefone("8999");
        f2.setSalario(2.0);
        fDAO2.save(f2);

        g.setId("4");
        g.setSenha("1234");
        g.setNome("jose");
        g.setCpf("11");
        g.setTelefone("2333");
        g.setSalario(98.0);
        gDAO.save(g);


//        LoginController controller = fxmlLoader.getController();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
