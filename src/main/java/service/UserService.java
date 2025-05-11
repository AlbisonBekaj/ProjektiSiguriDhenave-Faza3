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

}
