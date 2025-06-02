package htw_berlin.webtech.service;

import htw_berlin.webtech.config.SecurityConfig;
import htw_berlin.webtech.domain.AppUser;
import htw_berlin.webtech.domain.Applicant;
import htw_berlin.webtech.domain.Company;
import htw_berlin.webtech.domain.enums.UserRole;
import htw_berlin.webtech.dto.RegistrationRequest;
import htw_berlin.webtech.repository.AppUserRepository;
import htw_berlin.webtech.repository.ApplicantRepository;
import htw_berlin.webtech.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final CompanyRepository companyRepository;
    private final ApplicantRepository applicantRepository;
    private final SecurityConfig passwordEncoder;

    public void registerUser(RegistrationRequest request) {
        if (appUserRepository.existsByEmail(request.getUsername())) {
            throw new RuntimeException("Benutzername bereits vergeben.");
        }

        AppUser user = AppUser.builder()
                .email(request.getUsername())
                .password(passwordEncoder.passwordEncoder().encode(request.getPassword()))
                .role(request.getRole())
                .enabled(true)
                .build();

        AppUser savedUser = appUserRepository.save(user);

        if (request.getRole() == UserRole.COMPANY) {
            Company company = Company.builder()
                    .name(request.getCompanyName())
                    .contactEmail(request.getCompanyEmail())
                    .website(request.getWebsite())
                    .legalForm(request.getIndustry())
                    .address(request.getAddress())
                    .phoneNumber(request.getPhoneNumber())
                    .foundedYear(request.getFoundedYear())
                    .description(request.getCompanyDescription())
                    .logoUrl(request.getLogoUrl())
                    .user(savedUser)
                    .build();
            companyRepository.save(company);
        } else if (request.getRole() == UserRole.APPLICANT) {
            Applicant applicant = Applicant.builder()
                    .name(request.getApplicantName())
                    .email(request.getApplicantEmail())
                    .phoneNumber(request.getApplicantPhoneNumber())
                    .birthDate(request.getBirthDate())
                    .cvLink(request.getCvLink())
                    .resumeText(request.getResumeText())
                    .profileImageUrl(request.getProfileImageUrl())
                    .user(savedUser)
                    .build();
            applicantRepository.save(applicant);
        }
    }
}
