package htw_berlin.webtech.controller;

import htw_berlin.webtech.dto.AdminRegistrationRequest;
import htw_berlin.webtech.dto.AdminDto;
import htw_berlin.webtech.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminRegistrationRequest request) {
        AdminDto admin = appUserService.registerAdmin(request);
        return ResponseEntity.status(201).body(admin);
    }
}