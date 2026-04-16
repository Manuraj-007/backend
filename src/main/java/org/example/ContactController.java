
package org.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
@CrossOrigin
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;


    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }


    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {

        Optional<Contact> optional = contactRepository.findById(id);

        if (optional.isPresent()) {
            Contact contact = optional.get();

            contact.setName(updatedContact.getName());
            contact.setPhoneNumber(updatedContact.getPhoneNumber());

            return contactRepository.save(contact);
        } else {
            throw new RuntimeException("Contact not found with id " + id);
        }
    }
}