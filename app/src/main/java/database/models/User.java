package database.models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DataBaseUtils;

public class User extends DataBaseUtils {
	private String name;
	private String email;
	private String oldEmail;
	private String passwordHash;
	private String bio; 
	private String avatar_url;
	private final String role = "user";
	private int score;

	/**
	 * @param name
	 * @param email
	 * @param password
	 * @param bio
	 * @param tavatar_url
	 */
	public User(String name, String email, String password, String bio, String avatar_url) {
		super();			
		this.name = name;
		this.email = this.oldEmail = email;
		this.passwordHash = passwordHash(password);
		this.bio = bio;
		this.avatar_url = avatar_url;

	}
	
	public boolean register(String name, String email, String passwordHash, String bio, String avatar_url){
		if(!checkUserExiste(name,email)) {
			connect();
			
			String sql = "INSERT INTO users (name, email, password,role) VALUES (?, ?, ?, ?)";
			int idGenerated = insert(sql,name,email, passwordHash,this.role);
			if(idGenerated == -1) {
				System.err.println("ID non généré.");
			}
			sql = "INSERT INTO profiles (user_id, bio, avatar_url) VALUES (?, ?, ?)";
			insert(sql,idGenerated,bio,avatar_url);
			
			close();
			return true;
		}else {
			System.err.println("User déjà créé.");
			return false;
		}
	}
	
    public boolean deleteSelf() {
        try {
            String sql = "DELETE FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.email);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            System.err.println("Erreur suppression user : " + e.getMessage());
            return false;
        } finally {
            close();
        }
    }
	
	public boolean updateSelf() {
	    String sql = "UPDATE users SET name = ?, email = ?, password_hash = ?, bio = ?, avatar_url = ? WHERE email = ?";
	    connect();
	    try {
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, name);
	        stmt.setString(2, email);
	        stmt.setString(3, passwordHash); // déjà hashé
	        stmt.setString(4, bio);
	        stmt.setString(5, avatar_url);
	        stmt.setString(6, oldEmail);

	        int affected = stmt.executeUpdate();
	        return affected > 0;
	    } catch (SQLException e) {
	        System.err.println("Erreur mise à jour utilisateur : " + e.getMessage());
	        return false;
	    } finally {
	        close();
	    }
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String password) {
		this.passwordHash = passwordHash(password);
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getOldEmail() {
		return oldEmail;
	}

	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}
	
	
	
	
	
	
	
	
	
	
}
