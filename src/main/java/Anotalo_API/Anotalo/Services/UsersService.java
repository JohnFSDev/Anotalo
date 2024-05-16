package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.User;
import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
}
