package htw_berlin.webtech.dto;

import htw_berlin.webtech.domain.enums.UserRole;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
public class RegistrationRequest {
    public String username;
    public String password;
    private UserRole role; // Company or applicant



    // company
    private String companyName;
    private String companyEmail;
    private String website;
    private String industry;
    private String address;
    private String phoneNumber;
    private Integer foundedYear;
    private String companyDescription;
    private String logoUrl;


    // applicant
    private String applicantName;
    private String applicantEmail;
    private String applicantPhoneNumber;
    private LocalDate birthDate;
    private String cvLink;
    private String resumeText;
    private String profileImageUrl;


}
