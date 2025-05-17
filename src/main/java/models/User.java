package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String userName;
    private String salt;
    private String saltedHash;

    public User(int id, String userName, String salt, String saltedHash) {
        this.id = id;
        this.userName = userName;
        this.salt = salt;
        this.saltedHash = saltedHash;
    }

    public static User getInstance(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String userName = rs.getString("username");
        String salt = rs.getString("salt");
        String saltedHash = rs.getString("salted_hash");
        return new User(id, userName, salt, saltedHash);
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getSalt() {
        return salt;
    }

    public String getSaltedHash() {
        return saltedHash;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setSaltedHash(String slatedHash) {
        this.saltedHash = saltedHash;
    }

}
