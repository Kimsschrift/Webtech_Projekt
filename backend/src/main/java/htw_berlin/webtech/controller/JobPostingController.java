package htw_berlin.webtech.controller;


import htw_berlin.webtech.domain.JobPosting;
import htw_berlin.webtech.domain.enums.EmploymentType;
import htw_berlin.webtech.domain.enums.WorkTime;
import htw_berlin.webtech.domain.enums.Industry;
import htw_berlin.webtech.domain.enums.DegreeLevel;
import htw_berlin.webtech.dto.CompanyDto;
import htw_berlin.webtech.dto.JobPostingDto;
import htw_berlin.webtech.repository.JobPostingRepository;
import htw_berlin.webtech.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobpostings")
public class JobPostingController {

    private final JobPostingService jobPostingService;

    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }


    @GetMapping
    public ResponseEntity<List<JobPostingDto>> fetchJobPosting() {
        List<JobPosting> postings = jobPostingService.findAll();
        List<JobPostingDto> dtos = postings.stream()
                .map(posting -> JobPostingDto.builder()
                        .id(posting.getId())
                        .title(posting.getTitle())
                        .skills(posting.getSkills())
                        .minDegree(posting.getMinDegree())
                        .languages(posting.getLanguages())
                        .expectedSalary(posting.getExpectedSalary())
                        .location(posting.getLocation())
                        .remoteAllowed(posting.isRemoteAllowed())
                        .requiresExperience(posting.isRequiresExperience())
                        .employmentType(posting.getEmploymentType())
                        .workTime(posting.getWorkTime())
                        .industry(posting.getIndustry())
                        .company(
                                CompanyDto.builder()
                                        .id(posting.getCompany().getId())
                                        .name(posting.getCompany().getName())
                                        .address(posting.getCompany().getAddress())
                                        .legalForm(posting.getCompany().getLegalForm())
                                        .website(posting.getCompany().getWebsite())
                                        .contactEmail(posting.getCompany().getContactEmail())
                                        .build()
                        )
                        .build()
                )
                .toList();

        return ResponseEntity.ok(dtos);
    }
}
