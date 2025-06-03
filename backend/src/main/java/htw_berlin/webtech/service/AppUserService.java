package htw_berlin.webtech.service;

import htw_berlin.webtech.config.SecurityConfig;
import htw_berlin.webtech.domain.AppUser;
import htw_berlin.webtech.domain.Applicant;
import htw_berlin.webtech.domain.Company;
import htw_berlin.webtech.domain.enums.UserRole;
import htw_berlin.webtech.dto.AppUserDto;
import htw_berlin.webtech.dto.CompanyDto;
import htw_berlin.webtech.dto.RegistrationRequest;
import htw_berlin.webtech.repository.AppUserRepository;
import htw_berlin.webtech.repository.ApplicantRepository;
import htw_berlin.webtech.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final CompanyRepository companyRepository;
    private final ApplicantRepository applicantRepository;
    private final SecurityConfig passwordEncoder;

    public void registerUser(RegistrationRequest request) {
        if (appUserRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Benutzeremail bereits vergeben.");
        }

        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.passwordEncoder().encode(request.getPassword()))
                .role(request.getRole())
                .enabled(true)
                .build();

        AppUser savedUser = appUserRepository.save(user);

        if (request.getRole() == UserRole.COMPANY) {
            Company company = Company.builder()
                    .name(request.getCompanyName())
                    .email(request.getEmail())
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
                    .email(request.getEmail())
                    .phoneNumber(request.getApplicantPhoneNumber())
                    .birthDate(request.getBirthDate())
                    .cvLink(request.getCvLink())
                    .resumeText(request.getResumeText())
                    .profileImageUrl(request.getProfileImageUrl())
                    .user(savedUser)
                    .build();
            applicantRepository.save(applicant);
        } else {
            throw new RuntimeException("Ungültige Rolle: " + request.getRole());
        }
    }

    @Transactional(readOnly = true)
    public AppUserDto findById(Long id) {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Der Benutzer nicht gefunden: ID " + id));
        return new AppUserDto(user.getId(), user.getRole());
    }

    public boolean deleteById(Long id) {
        if (!appUserRepository.existsById(id)) {
            return false;
        }
        appUserRepository.deleteById(id);
        return true;
    }
}
