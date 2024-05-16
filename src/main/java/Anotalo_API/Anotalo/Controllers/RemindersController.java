package Anotalo_API.Anotalo.Controllers;

import Anotalo_API.Anotalo.Models.Reminder;
import Anotalo_API.Anotalo.Services.RemindersService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reminders")
public class RemindersController {

    @Autowired
    private RemindersService remindersService;

    @GetMapping("/")
    public ResponseEntity<List<Reminder>> getAllReminders() {
        List<Reminder> reminders = remindersService.getAllReminders();
        return ResponseEntity.ok(reminders); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reminder> getReminderById(@PathVariable Long id) {
        Optional<Reminder> reminderOptional = remindersService.getReminderById(id);
        if (reminderOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        } else {
            return ResponseEntity.ok(reminderOptional.get()); // 200 OK (with reminder)
        }
    }

    @PostMapping("/")
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) {
        if (reminder == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        Reminder createdReminder = remindersService.saveReminder(reminder);
        return ResponseEntity.ok(createdReminder); // 200 OK (with created reminder)
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable Long id, @RequestBody Reminder reminder) {
        if (reminder == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        reminder.setId(id); // Explicitly set ID for update (assuming service requires it)
        Reminder updatedReminder = remindersService.saveReminder(reminder);
        return ResponseEntity.ok(updatedReminder); // 200 OK (with updated reminder)
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        remindersService.deleteReminder(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
