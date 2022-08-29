package ru.job4j.accident.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.Optional;

@Controller
public class RegController {

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    public RegController(PasswordEncoder encoder, UserRepository users, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        Optional<User> userDb = users.findUserByUsername(user.getUsername());
        if (userDb.isPresent()) {
            return "redirect:/reg?error=true";
        }
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage(Model model,
                          @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "User with the given name already exists!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "registration";
    }
}