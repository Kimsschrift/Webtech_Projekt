package htw_berlin.webtech.dto;

import htw_berlin.webtech.domain.Applicant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApplicantDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String profileImageUrl;

    public ApplicantDto(Applicant applicant){
        this.id = applicant.getId();
        this.name = applicant.getName();
        this.email = applicant.getEmail();
        this.phoneNumber = applicant.getPhoneNumber();
        this.profileImageUrl = applicant.getProfileImageUrl();
    }
}
