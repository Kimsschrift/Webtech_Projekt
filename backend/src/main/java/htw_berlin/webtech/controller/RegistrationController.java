package htw_berlin.webtech.controller;


import htw_berlin.webtech.dto.RegistrationRequest;
import htw_berlin.webtech.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/domain/register")
public class RegistrationController {

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        try {
            appUserService.registerUser(request);
            return ResponseEntity.ok("Registrierung erfolgreich");
    } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Fehler: " + e.getMessage());
        }

    }
}
