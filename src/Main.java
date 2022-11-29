import java.io.File;
import java.io.IOException;

public class Main {
    static Modelo modelo;

    public static void main(String[] args) {
        try {
            File fDatabase = new File(Database.filePath);
        if (!fDatabase.exists()) {
            Database.initDatabase();
        }
            WsServidor.connecta();
        } catch (InterruptedException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        modelo = new Modelo();
        interfazIndustry_2 interfaz = new interfazIndustry_2(modelo);
        interfaz.setVisible(true);
        
    }
}
