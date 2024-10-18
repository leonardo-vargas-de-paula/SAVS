package teste;

import jakarta.persistence.Persistence;

public class TesteConexao {
    public static void main(String[] args) {
        try {
            Persistence.createEntityManagerFactory("sistemaApsoo");
            System.out.println("Conexão bem-sucedida!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}