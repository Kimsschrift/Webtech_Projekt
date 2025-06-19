package htw_berlin.webtech.service;

import htw_berlin.webtech.domain.Applicant;
import htw_berlin.webtech.domain.ApplicantManipulationRequest;
import htw_berlin.webtech.domain.Company;
import htw_berlin.webtech.domain.CompanyManipulationRequest;
import htw_berlin.webtech.dto.ApplicantDto;
import htw_berlin.webtech.dto.CompanyDto;
import htw_berlin.webtech.repository.AppUserRepository;
import htw_berlin.webtech.repository.ApplicantRepository;
import htw_berlin.webtech.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantService {


    private final ApplicantRepository applicantRepository;

    public ApplicantDto findById(Long id) {
        return applicantRepository.findById(id)
                .map(ApplicantDto::new)
                .orElse(null);
    }

    public ApplicantDto update(Long id, ApplicantManipulationRequest request) {
        var applicant = applicantRepository.findById(id);
        if (applicant.isEmpty()) {
            return null;
        }

        var applicantEntity = applicant.get();

        if (request.getName() != null && !request.getName().isBlank()) {
            applicantEntity.setName(request.getName());
        }

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            applicantEntity.setEmail(request.getEmail());
        }

        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank()) {
            applicantEntity.setPhoneNumber(request.getPhoneNumber());
        }

        Applicant saved = applicantRepository.save(applicantEntity);
        return new ApplicantDto(saved);

    }


}
