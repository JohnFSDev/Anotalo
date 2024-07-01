package Anotalo_API.Anotalo.Services;

public interface EmailService {
    
    void sendEmail(String to, String subject, String body);
    
}