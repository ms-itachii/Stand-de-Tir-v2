package database;

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public abstract class DataBaseUtils {

	protected static Connection conn;

	protected DataBaseUtils() {
		super();
	}
	
	protected static Connection connect() {
		try {
			String host = "localhost";
			String user = "user";
			String password = "";
			String database = "";
			
			String url = "jdbc:mysql://" + host + "/" + database + "?useSSL=false&serverTimezone=UTC";
			
			conn = DriverManager.getConnection(url,user, password);
			return conn;
		}catch (SQLException e) {
		        System.err.println("Erreur de connexion : " + e.getMessage());
		        return null;
		    }
	}
	
    protected static void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connexion fermée.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture : " + e.getMessage());
        }
    }
    
    protected static ResultSet select(String sql, Object... params) {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) stmt.setObject(i + 1, params[i]);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Erreur select : " + e.getMessage());
            return null;
        }
    }

    protected int insert(String sql, Object... params) {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
            	stmt.setObject(i + 1, params[i]);
            }
            	
            int affectedRows = stmt.executeUpdate(); //return le nombre de lignes affectées
            
            if(affectedRows == 0) {
            	System.err.println("Aucune ligne insérée."); //voir comment mieux gérer les print
            	return -1;
            }
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); //l'ID généré
            } else {
                System.err.println("ID non généré."); //voir comment mieux gérer les print
                return -1;
            }
            	
        } catch (SQLException e) {
            System.err.println("Erreur insert : " + e.getMessage());
            return -1;
        }
    }
            
    protected static String passwordHash(String password) {
    	return BCrypt.hashpw(password, BCrypt.gensalt());
    }
	
    protected static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    
    protected static boolean checkUserExiste(String name,String email) {
    	connect();
    	try {
    		ResultSet rs = select("SELECT * FROM users WHERE email = ?", email);
    		if (rs != null && rs.next()) {
    		    System.out.println("Nom : " + rs.getString("name"));
    		    return true;
    		}
    	} catch (SQLException e) {
                System.err.println("Erreur vérification user : " + e.getMessage());
                return false;
    	} finally {
    		close();
    	}
		return false;
    	
    }
    
    
}
