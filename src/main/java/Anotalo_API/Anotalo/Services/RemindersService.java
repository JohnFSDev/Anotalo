package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.Reminder;
import java.util.List;
import java.util.Optional;


public interface RemindersService {
    List<Reminder> getAllReminders();
    Reminder saveReminder(Reminder reminder);
    Optional<Reminder> getReminderById(Long id);
    void deleteReminder(Long id);
}
