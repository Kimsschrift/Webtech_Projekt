package htw_berlin.webtech.controller;

import htw_berlin.webtech.dto.RegistrationRequest;
import htw_berlin.webtech.service.AppUserService;
import htw_berlin.webtech.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/domain/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final AppUserService appUserService;
    private final FileStorageService fileStorageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> register(@ModelAttribute RegistrationRequest request) {
        try {
            // ==== COMPANY Files ====
            if (request.getLogoFile() != null && !request.getLogoFile().isEmpty()) {
                String logoUrl = fileStorageService.storeFile(request.getLogoFile());
                request.setLogoUrl(logoUrl);
            }
            if (request.getCompanyDescriptionFile() != null && !request.getCompanyDescriptionFile().isEmpty()) {
                String companyDesc = fileStorageService.extractText(request.getCompanyDescriptionFile());
                request.setCompanyDescription(companyDesc);
            }

            // ==== APPLICANT Files ====
            if (request.getProfileImageFile() != null && !request.getProfileImageFile().isEmpty()) {
                String profileUrl = fileStorageService.storeFile(request.getProfileImageFile());
                request.setProfileImageUrl(profileUrl);
            }
            if (request.getResumeTextFile() != null && !request.getResumeTextFile().isEmpty()) {
                String resumeText = fileStorageService.extractText(request.getResumeTextFile());
                request.setResumeText(resumeText);
            }
            if (request.getCvFile() != null && !request.getCvFile().isEmpty()) {
                String cvUrl = fileStorageService.storeFile(request.getCvFile());
                request.setCvLink(cvUrl);
            }

            // ==== Delegate to Service ====
            appUserService.registerUser(request);

            return ResponseEntity.ok("Registrierung erfolgreich!");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Fehler bei der Registrierung: " + e.getMessage());
        }
    }
}
