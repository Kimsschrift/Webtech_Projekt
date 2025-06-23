package htw_berlin.webtech.service;

import htw_berlin.webtech.domain.AppUser;
import htw_berlin.webtech.domain.Applicant;
import htw_berlin.webtech.domain.Company;
import htw_berlin.webtech.domain.Admin;
import htw_berlin.webtech.domain.enums.UserRole;
import htw_berlin.webtech.dto.AppUserDto;
import htw_berlin.webtech.dto.RegistrationRequest;
import htw_berlin.webtech.dto.LoginRequest;
import htw_berlin.webtech.dto.AdminRegistrationRequest;
import htw_berlin.webtech.dto.AdminDto;
import htw_berlin.webtech.dto.LoginResponse;
import htw_berlin.webtech.repository.AppUserRepository;
import htw_berlin.webtech.repository.ApplicantRepository;
import htw_berlin.webtech.repository.CompanyRepository;
import htw_berlin.webtech.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final CompanyRepository companyRepository;
    private final ApplicantRepository applicantRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegistrationRequest request) {
        if (appUserRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Benutzeremail bereits vergeben.");
        }

        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
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
        } else if (request.getRole() == UserRole.ADMIN) {
            Admin admin = Admin.builder()
                    .user(savedUser)
                    .build();
            adminRepository.save(admin);
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

    public boolean deleteById(Long id, Long requestingUserId) {
        if (!id.equals(requestingUserId)) {
            return false;
        }
        if (!appUserRepository.existsById(id)) {
            return false;
        }

        appUserRepository.deleteById(id);
        return true;
    }

    public LoginResponse login(LoginRequest request) {
        AppUser user = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Falsche Zugangsdaten"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Falsche Zugangsdaten");
        }

        Long companyId = companyRepository.findByUserId(user.getId())
                .map(Company::getId).orElse(null);
        Long applicantId = applicantRepository.findByUserId(user.getId())
                .map(Applicant::getId).orElse(null);
        Long adminId = adminRepository.findByUserId(user.getId())
                .map(Admin::getId).orElse(null);

        return new LoginResponse(user.getId(), user.getRole(), companyId, applicantId, adminId);
    }
    public AdminDto registerAdmin(AdminRegistrationRequest request) {
        if (appUserRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Benutzeremail bereits vergeben.");
        }
        if (adminRepository.findByAdminCode(request.getAdminCode()).isPresent()) {
            throw new RuntimeException("Admin-Code bereits vergeben.");
        }

        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.ADMIN)
                .enabled(true)
                .build();

        AppUser savedUser = appUserRepository.save(user);

        Admin admin = Admin.builder()
                .adminCode(request.getAdminCode())
                .user(savedUser)
                .build();

        Admin saved = adminRepository.save(admin);
        return new AdminDto(saved);
    }

    public LoginResponse loginByAdminCode(String adminCode) {
        Admin admin = adminRepository.findByAdminCode(adminCode)
                .orElseThrow(() -> new RuntimeException("Ungültiger Admin-Code"));

        AppUser user = admin.getUser();

        Long companyId = companyRepository.findByUserId(user.getId())
                .map(Company::getId).orElse(null);
        Long applicantId = applicantRepository.findByUserId(user.getId())
                .map(Applicant::getId).orElse(null);

        return new LoginResponse(user.getId(), user.getRole(), companyId, applicantId, admin.getId());
    }
}
