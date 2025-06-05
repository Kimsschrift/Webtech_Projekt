package htw_berlin.webtech.controller;

import htw_berlin.webtech.dto.LoginRequest;
import htw_berlin.webtech.dto.LoginResponse;
import htw_berlin.webtech.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = appUserService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
