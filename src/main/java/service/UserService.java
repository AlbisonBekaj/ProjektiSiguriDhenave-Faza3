package service;
import managers.SessionManager;
import repository.UserRepository;
import models.User;
import models.dto.CreateUserDto;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public int signIn(String username, String password) throws Exception{
        if (username == null || password == null) {
            throw new Exception("EMPTY !! username or password ");
        }
        User user = userRepository.getUserByUsername(username);
        System.out.println(user.getUserName());
        if (user == null) {
            throw new Exception("WRONG !! username or password ");
        }
            String salt = user.getSalt();
            String hashedInput = hashPassword(password, salt);
            if(user.getSaltedHash().equals(hashedInput)) {
                SessionManager.setCurrentUser(user);
                return user.getId();
            }
            return 0;
    }
    public User signUp(String username, String password) throws Exception{
        if (username == null || password == null) {
            throw new Exception("EMPTY !! username or password ");
        }
        User user = userRepository.getUserByUsername(username);
        if (user != null) {
            throw new ExceptionAlreadyExists("It already exits!! ");
        }
        CreateUserDto createUserDto= new CreateUserDto(username,password);
        User newUser= userRepository.create(createUserDto);
        return newUser;
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
    public class ExceptionAlreadyExists extends Exception{
        public ExceptionAlreadyExists(String message) {
            super(message);
        }
    }

    public boolean changePassword(int userId, String newPassword) {
        try {
            byte[] saltBytes = new byte[16];
            new java.security.SecureRandom().nextBytes(saltBytes);
            String salt = java.util.Base64.getEncoder().encodeToString(saltBytes);

            String saltedHash = hashPassword(newPassword, salt);

            return userRepository.updatePassword(userId, salt, saltedHash);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
