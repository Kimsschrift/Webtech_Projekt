package htw_berlin.webtech.service;

import htw_berlin.webtech.domain.Admin;
import htw_berlin.webtech.domain.AdminManipulationRequest;
import htw_berlin.webtech.dto.AdminDto;
import htw_berlin.webtech.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional(readOnly = true)
    public List<AdminDto> findAll() {
        return adminRepository.findAll().stream()
                .map(AdminDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public AdminDto findById(Long id) {
        return adminRepository.findById(id)
                .map(AdminDto::new)
                .orElse(null);
    }

    public AdminDto update(Long id, AdminManipulationRequest request) {
        return adminRepository.findById(id)
                .map(admin -> {
                    if (request.getAdminCode() != null && !request.getAdminCode().isBlank()) {
                        admin.setAdminCode(request.getAdminCode());
                    }
                    Admin saved = adminRepository.save(admin);
                    return new AdminDto(saved);
                })
                .orElse(null);
    }

    public boolean deleteById(Long id) {
        if (!adminRepository.existsById(id)) {
            return false;
        }
        adminRepository.deleteById(id);
        return true;
    }
}