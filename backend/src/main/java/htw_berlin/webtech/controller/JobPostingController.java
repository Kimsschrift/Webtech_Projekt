package htw_berlin.webtech.controller;


import htw_berlin.webtech.domain.JobPosting;
import htw_berlin.webtech.domain.JobPostingCreateRequest;
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
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;



@RestController
@RequestMapping("/domain/jobpostings")
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<JobPostingDto> fetchJobPostingById(@PathVariable Long id) {
        var posting = jobPostingService.findById(id);
        return posting != null? ResponseEntity.ok(posting) : ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<Void> createJobPosting(@RequestBody JobPostingCreateRequest request) throws URISyntaxException {
        JobPostingDto created = jobPostingService.create(request);
        URI uri = new URI("/jobpostings/" + created.getId());
        return ResponseEntity.created(uri).build();
    }

}
