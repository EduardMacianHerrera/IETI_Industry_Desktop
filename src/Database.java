import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    public static void main(String[] args) throws SQLException {
        String basePath = System.getProperty("user.dir") + "/";
        String filePath = basePath + "database.db";


        // Si no hi ha l'arxiu creat, el crea i li posa dades
        File fDatabase = new File(filePath);
        if (!fDatabase.exists()) {
            initDatabase(filePath);
        }

    }

    static HashMap<String, String> getData(Connection conn) throws SQLException {
        HashMap data = new HashMap<String, String>();
        ResultSet rs = UtilsSQLite.querySelect(conn, "SELECT username, password FROM users");

        while (rs.next()) {
            data.put(rs.getString("username"), rs.getString("password"));
        }

        return data;

    }

    static void initDatabase(String filePath) {
        // Connectar (crea la BBDD si no existeix)
        Connection conn = UtilsSQLite.connect(filePath);

        // Esborrar la taula (per si existeix)
        UtilsSQLite.queryUpdate(conn, "DROP TABLE IF EXISTS users;");

        // Crear una nova taula
        UtilsSQLite.queryUpdate(conn, "CREATE TABLE IF NOT EXISTS users ("
                + "	id integer PRIMARY KEY AUTOINCREMENT,"
                + "	username text NOT NULL,"
                + " password text NOT NULL);");

        // Desconnectar
        UtilsSQLite.disconnect(conn);
    }
}
