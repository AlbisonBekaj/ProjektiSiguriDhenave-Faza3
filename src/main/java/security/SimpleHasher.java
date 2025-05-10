package security;


import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class SimpleHasher {


    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) throws Exception {
        // Combine password and salt
        String passwordWithSalt = password + salt;

        // Hash using SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(passwordWithSalt.getBytes());

        // Return the hash as a Base64 encoded string
        return Base64.getEncoder().encodeToString(hash);
    }

    public static boolean verifyPassword(String user , String enteredPassword, String storedSaltedHash) throws Exception {
        // Split the stored salt and hash
        String[] parts = storedSaltedHash.split(":");
        String storedSalt = parts[0];  // Extract the salt
        String storedHash = parts[1];  // Extract the stored hash

        // Hash the entered password with the stored salt
        String enteredHash = hashPassword(enteredPassword, storedSalt);

        // Compare the hashes
        return enteredHash.equals(storedHash);
    }
    public static void main(String[] args) {
        try {
            String password = "mySecretPassword";

            // Generate salt and hash the password
            String salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);
            String storedSaltedHash = salt + ":" + hashedPassword;

            System.out.println("Stored Salted Hash: " + storedSaltedHash);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
