package database.models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin extends User {
	
	private final String role = "admin";
			
	/**
	 * @param name
	 * @param email
	 * @param password
	 * @param bio
	 * @param avatar_url
	 */
	protected Admin(String name, String email, String password, String bio, String avatar_url) {
		super(name, email, password, bio, avatar_url);
	}

	@Override
	public boolean deleteSelf() {
	    System.err.println("Suppression d’un admin interdite !");
	    return false;
	}

	public boolean deleteUser(User targetUser) {
		try {
            String sql = "DELETE FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, targetUser.getEmail());
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            System.err.println("Erreur suppression user : " + e.getMessage());
            return false;
        } finally {
            close();
        }
	}
	
	public boolean updateUser(User targetUser) {
	    String sql = "UPDATE users SET name = ?, email = ?, password_hash = ?, bio = ?, avatar_url = ? WHERE email = ?";
	    connect();
	    try {
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, targetUser.getName());
	        stmt.setString(2, targetUser.getEmail());
	        stmt.setString(3, targetUser.getPasswordHash());
	        stmt.setString(4, targetUser.getBio());
	        stmt.setString(5, targetUser.getAvatar_url());
	        stmt.setString(6, targetUser.getOldEmail()); // ou par ID : WHERE id = ?

	        int affected = stmt.executeUpdate();
	        return affected > 0;
	    } catch (SQLException e) {
	        System.err.println("Erreur mise à jour par admin : " + e.getMessage());
	        return false;
	    } finally {
	        close();
	    }
	}
 

}
