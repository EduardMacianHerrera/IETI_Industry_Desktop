import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import com.password4j.Password;

public class Database {

    public static String basePath = System.getProperty("user.dir") + "/";
    public static String filePath = basePath + "database.db";
    public static String pathSalt = basePath + "salt.db";
    public static String pathPepper = basePath + "pepper.db";

    

    static HashMap<String, String> getData() {
        Connection conn = UtilsSQLite.connect(filePath);
        HashMap<String, String> data = new HashMap<String, String>();
        ResultSet rs = UtilsSQLite.querySelect(conn, "SELECT username, password FROM users");

        try {
            while (rs.next()) {
                data.put(rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return data;

    }

    static void initDatabase() {
        // Connectar (crea la BBDD si no existeix)
        Connection conn = UtilsSQLite.connect(filePath);
        Connection connSalt = UtilsSQLite.connect(pathSalt);
        Connection connPepper = UtilsSQLite.connect(pathPepper);

        // Esborrar la taula (per si existeix)
        UtilsSQLite.queryUpdate(conn, "DROP TABLE IF EXISTS users;");

        // Crear una nova taula
        UtilsSQLite.queryUpdate(conn, "CREATE TABLE IF NOT EXISTS users ("
                + "	id integer PRIMARY KEY AUTOINCREMENT,"
                + "	username text NOT NULL,"
                + " password text NOT NULL);");
        
        UtilsSQLite.queryUpdate(conn, "CREATE TABLE if not exists \"snapshots\" (\r\n"
        		+ "	\"id\"	INTEGER NOT NULL,\r\n"
        		+ "	\"name\"	TEXT NOT NULL,\r\n"
        		+ "	\"date\"	TEXT NOT NULL,\r\n"
        		+ "	\"controls\"	TEXT NOT NULL,\r\n"
        		+ "	PRIMARY KEY(\"id\" AUTOINCREMENT)\r\n"
        		+ ");");
        
        UtilsSQLite.queryUpdate(connSalt, "DROP TABLE IF EXISTS salt;");
        UtilsSQLite.queryUpdate(connSalt, "CREATE TABLE IF NOT EXISTS salt("
        		+ "id integer PRIMARY KEY AUTOINCREMENT,"
        		+ "idUser integer NOT NULL,"
        		+ "salt text NOT NULL);");
        
        UtilsSQLite.queryUpdate(connPepper, "DROP TABLE IF EXISTS pepper;");
        UtilsSQLite.queryUpdate(connPepper, "CREATE TABLE IF NOT EXISTS pepper("
        		+ "id integer PRIMARY KEY AUTOINCREMENT,"
        		+ "idUser integer NOT NULL,"
        		+ "pepper text NOT NULL);");

        // Afegir elements a una taula
        
        
        
        
        
        
        String pwd = "test";
        String pwdSalt = "hola123";
        String pwdPepper = "pimienta";
        
       
        String hash = Password.hash(pwd).addSalt(pwdSalt).addPepper(pwdPepper).withArgon2().getResult();
        
        UtilsSQLite.queryUpdate(conn, "INSERT INTO users (username,password) VALUES (\"test\",'"+hash+"');");

        ResultSet rs = UtilsSQLite.querySelect(conn, "select id from users where username = 'test';");
        int id = 0;
        try {
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        UtilsSQLite.queryUpdate(connSalt, "insert into salt (idUser,salt) values ("+id+",'"+pwdSalt+"');");
        UtilsSQLite.queryUpdate(connPepper, "insert into pepper (idUser,pepper) values ("+id+",'"+pwdPepper+"');");
        
        
        // Desconnectar
        UtilsSQLite.disconnect(conn);
    }
    
    public static String checkLogin(String username, String password) {
    	Connection conn = UtilsSQLite.connect(filePath);
        Connection connSalt = UtilsSQLite.connect(pathSalt);
        Connection connPepper = UtilsSQLite.connect(pathPepper);
        
        ResultSet rs = UtilsSQLite.querySelect(conn, "select id,password from users where username = '"+username+"';");
        int idUser = 0;
        String pwdHash = "";
        try {
			rs.next();
			idUser = rs.getInt(1);
			pwdHash = rs.getString(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ResultSet rsSalt = UtilsSQLite.querySelect(connSalt, "select salt from salt where idUser = "+idUser+";");
        String pwdSalt = "";
        try {
			rsSalt.next();
			pwdSalt = rsSalt.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ResultSet rsPepper = UtilsSQLite.querySelect(connPepper, "select pepper from pepper where idUser = "+idUser+";");
        String pwdPepper = "";
        try {
			rsPepper.next();
			pwdPepper = rsPepper.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        boolean check;
        
        if (pwdSalt != null && pwdSalt != null && pwdPepper != null) {
	        check = Password.check(password, pwdHash).addSalt(pwdSalt).addPepper(pwdPepper).withArgon2();
		} else {
			check = false;
		}
        
        System.out.println("Login: "+check);
        if (check) {
			return "true";
		} else {
			return "false";
		}
    }
    
    public static void saveSnapshot(String name, String controls) {
    	Connection conn = UtilsSQLite.connect(filePath);
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
    	
    	UtilsSQLite.queryUpdate(conn, "insert into snapshots(name,date,controls) values ('"+name+"','"+dtf.format(now)+"','"+controls+"');");
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static HashMap<Integer, String> listSnapshots() {
    	Connection conn = UtilsSQLite.connect(filePath);
    	HashMap<Integer, String> snapshotsMap = new HashMap<Integer, String>();
    	
    	ResultSet rs = UtilsSQLite.querySelect(conn, "select id, (name || ' - ' || date) from snapshots;");
    	try {
			while (rs.next()) {
				snapshotsMap.put(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return snapshotsMap;
		
    }
    
    public static boolean loadSnapshot(String id) {
    	Connection conn = UtilsSQLite.connect(filePath);
    	
    	ResultSet rs = UtilsSQLite.querySelect(conn, "select controls from snapshots where id = "+id+";");
    	
    	String modelo = "";
    	try {
			while (rs.next()) {
				modelo = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	if (!modelo.equals("")) {
    		Modelo.blocks.clear();
    		Modelo.loadModel(modelo);
    		return true;
		} else {
			System.out.println("El modelo no se ha podido cargar correctamente");
			return false;
		}
    }
    
    
}
