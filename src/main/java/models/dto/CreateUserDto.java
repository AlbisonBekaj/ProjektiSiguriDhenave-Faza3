package models.dto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class CreateUserDto {
    private String userName;
    private String password;
    private String salt;
    private String saltedHash;

    public CreateUserDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.salt = generateSalt();
        this.saltedHash = generateSaltedHash(password, salt);
    }

    private String generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] saltBytes = new byte[16];
        sr.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    private String generateSaltedHash(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(Base64.getDecoder().decode(salt));
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserName() { return userName; }
    public String getPassword () { return password; }
    public String getSalt() { return salt; }
    public String getSaltedHash() { return saltedHash; }

    public void setUserName(String userName) { this.userName = userName; }

    public void setPassword(String password) {
        this.password = password;
        this.salt = generateSalt();
        this.saltedHash = generateSaltedHash(password, salt);
    }
}
