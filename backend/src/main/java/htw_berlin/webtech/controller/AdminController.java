package htw_berlin.webtech.controller;

import htw_berlin.webtech.dto.AdminRegistrationRequest;
import htw_berlin.webtech.dto.AdminDto;
import htw_berlin.webtech.service.AppUserService;
import htw_berlin.webtech.service.AdminService;
import htw_berlin.webtech.domain.AdminManipulationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AppUserService appUserService;
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminRegistrationRequest request) {
        AdminDto admin = appUserService.registerAdmin(request);
        return ResponseEntity.status(201).body(admin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable Long id,
                                                @RequestBody AdminManipulationRequest request) {
        AdminDto admin = adminService.update(id, request);
        return admin != null ? ResponseEntity.ok(admin) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        boolean success = adminService.deleteById(id);
        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}