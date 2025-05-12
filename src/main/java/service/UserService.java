package service;
import Repository.UserRepository;
import models.User;
import models.dto.CreateUserDto;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User signUp(String username, String password) {
        CreateUserDto dto = new CreateUserDto(username, password);
        return userRepository.create(dto);
    }
    public boolean signIn(String username, String password) {
        for (User user : userRepository.getAll()) {
            if (user.getUserName().equals(username)) {
                String salt = user.getSalt();
                String hashedInput = hashPassword(password, salt);
                return user.getSaltedHash().equals(hashedInput);
            }
        }
        return false;
    }

    private String hashPassword(String password, String salt) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            md.update(java.util.Base64.getDecoder().decode(salt));
            byte[] hashedPassword = md.digest(password.getBytes());
            return java.util.Base64.getEncoder().encodeToString(hashedPassword);
        } catch (Exception e) {
            throw new RuntimeException("Hashing error", e);
        }
    }

}
