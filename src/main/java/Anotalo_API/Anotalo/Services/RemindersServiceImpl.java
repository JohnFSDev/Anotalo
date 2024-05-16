package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.Reminder;
import Anotalo_API.Anotalo.Repository.RemindersRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemindersServiceImpl implements RemindersService{

    @Autowired
    private RemindersRepository userRepository;

    @Override
    public List<Reminder> getAllReminders() {
        return userRepository.findAll();
    }

    @Override
    public Reminder saveReminder(Reminder reminder) {
        return userRepository.save(reminder);
    }

    @Override
    public Optional<Reminder> getReminderById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteReminder(Long id) {
        userRepository.deleteById(id);
    }

}