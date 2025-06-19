package htw_berlin.webtech.service;

import htw_berlin.webtech.domain.Applicant;
import htw_berlin.webtech.domain.ApplicantManipulationRequest;
import htw_berlin.webtech.dto.ApplicantDto;
import htw_berlin.webtech.repository.ApplicantRepository;
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

        if (request.getBirthDate() != null) {
            applicantEntity.setBirthDate(request.getBirthDate());
        }

        if (request.getCvLink() != null && !request.getCvLink().isBlank()) {
            applicantEntity.setCvLink(request.getCvLink());
        }

        if (request.getResumeText() != null && !request.getResumeText().isBlank()) {
            applicantEntity.setResumeText(request.getResumeText());
        }

        if (request.getProfileImageUrl() != null && !request.getProfileImageUrl().isBlank()) {
            applicantEntity.setProfileImageUrl(request.getProfileImageUrl());
        }


        Applicant saved = applicantRepository.save(applicantEntity);
        return new ApplicantDto(saved);

    }


}
