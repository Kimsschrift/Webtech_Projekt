package htw_berlin.webtech.dto;

import htw_berlin.webtech.domain.enums.UserRole;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class RegistrationRequest {
    private String email;
    private String password;
    private UserRole role;

    // Company data
    private String companyName;
    private String website;
    private String industry;
    private String address;
    private String phoneNumber;
    private Integer foundedYear;
    private String companyDescription;
    private String logoUrl;

    // Applicant data
    private String applicantName;
    private String applicantPhoneNumber;
    private LocalDate birthDate;
    private String cvLink;
    private String resumeText;
    private String profileImageUrl;

    // === MultipartFile fields (received from form) ===
    // company
    private MultipartFile companyDescriptionFile;
    private MultipartFile logoFile;

    // applicant
    private MultipartFile resumeTextFile;
    private MultipartFile profileImageFile;
    private MultipartFile cvFile;

}
