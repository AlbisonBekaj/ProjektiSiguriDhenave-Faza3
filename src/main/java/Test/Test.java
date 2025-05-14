package Test;

import repository.UserRepository;
import models.User;
import models.dto.CreateUserDto;
import models.dto.UpdateUserDto;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        CreateUserDto user = new CreateUserDto("ion", "Albisoni123");

        UserRepository userRepository = new UserRepository();

//        userRepository.create(user);

        ArrayList<User> array =  userRepository.getAll();

        for (User u : array) {
            System.out.println(u.getUserName());
            System.out.println(u.getSalt());
            System.out.println(u.getSaltedHash());
        }
        UpdateUserDto updateUser = new UpdateUserDto(1,"albi", "Albisoni123");
//        userRepository.update(updateUser);

        if(userRepository.delete(1)){
            System.out.println("User deleted successfully");
        }
        User userById = userRepository.getById(1);
        System.out.println(userById.getUserName());
        System.out.println(userById.getSalt());
        System.out.println(userById.getSaltedHash());

    }
}
