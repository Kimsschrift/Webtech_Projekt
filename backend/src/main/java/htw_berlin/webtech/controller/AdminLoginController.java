package htw_berlin.webtech.controller;

import htw_berlin.webtech.dto.AdminLoginRequest;
import htw_berlin.webtech.dto.LoginResponse;
import htw_berlin.webtech.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminLoginController {
    private final AppUserService appUserService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AdminLoginRequest request) {
        try {
            LoginResponse response = appUserService.loginByAdminCode(request.getCode());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
