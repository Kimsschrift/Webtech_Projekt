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
        List<JobPostingDto> postings = jobPostingService.getAllJobPostings();

        if (postings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(postings);
    }
}
