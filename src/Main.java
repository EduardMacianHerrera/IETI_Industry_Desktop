import java.io.IOException;

public class Main {
    static Modelo modelo;

    public static void main(String[] args) {
        try {
            WsServidor.connecta();
        } catch (InterruptedException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        modelo = new Modelo();
        interfazMenuDesktop interfaz = new interfazMenuDesktop(modelo);
        interfaz.setVisible(true);
        
    }
}
