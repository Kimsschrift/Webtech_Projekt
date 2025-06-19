package htw_berlin.webtech.controller;

import htw_berlin.webtech.domain.Applicant;
import htw_berlin.webtech.domain.ApplicantManipulationRequest;
import htw_berlin.webtech.domain.CompanyManipulationRequest;
import htw_berlin.webtech.dto.ApplicantDto;
import htw_berlin.webtech.dto.CompanyDto;
import htw_berlin.webtech.repository.ApplicantRepository;
import htw_berlin.webtech.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantRepository applicantRepository;
    private final ApplicantService applicantService;

    @GetMapping("/{id}")
    public ResponseEntity<ApplicantDto> getApplicant(@PathVariable Long id) {
        var applicant = applicantService.findById(id);
        return applicant != null ? ResponseEntity.ok(applicant) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Long id) {
        if (applicantRepository.existsById(id)) {
            applicantRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApplicantDto> updateApplicant(@PathVariable Long id, @RequestBody ApplicantManipulationRequest request) {
        var applicant = applicantService.update(id, request);
        return applicant != null? ResponseEntity.ok(applicant) : ResponseEntity.notFound().build();
    }

}