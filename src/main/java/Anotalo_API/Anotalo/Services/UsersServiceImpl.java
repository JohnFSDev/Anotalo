package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.User;
import Anotalo_API.Anotalo.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository userRepository;
    
    @Autowired
    private EmailService emailService; 

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        
        User savedUser = userRepository.save(user);
        
        //sendRegistrationEmail(savedUser);
        
        return savedUser;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
   // Método privado para enviar el correo de registro
    private void sendRegistrationEmail(User user) {
        String userEmail = user.getEmail();
        String subject = "Registro exitoso en Anotalo";
        String message = String.format("""
                                       Hola %s,
                                       
                                       Te has registrado exitosamente en Anotalo.
                                       Tu correo electr\u00f3nico: %s
                                       
                                       \u00a1Gracias por unirte a nosotros!""", user.getName(), user.getEmail());

        // Llama al método de instancia sendEmail de EmailService
        emailService.sendEmail(userEmail, subject, message);
    }
}
