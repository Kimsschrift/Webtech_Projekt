package htw_berlin.webtech.controller;

import htw_berlin.webtech.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appUsers")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id,
                                           @RequestHeader("X-User-Id") Long userId) {
        boolean successful = appUserService.deleteById(id, userId);
        return successful ? ResponseEntity.ok(id) : ResponseEntity.status(403).build();
    }
}