package htw_berlin.webtech.controller;

import htw_berlin.webtech.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain/appUser")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        boolean successful = appUserService.deleteById(id);
        return successful ? ResponseEntity.ok(id) : ResponseEntity.notFound().build();

    }
}