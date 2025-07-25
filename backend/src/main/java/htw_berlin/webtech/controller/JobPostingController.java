package htw_berlin.webtech.controller;


import htw_berlin.webtech.domain.JobPostingManipulationRequest;
import htw_berlin.webtech.dto.JobPostingDto;
import htw_berlin.webtech.service.JobPostingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@CrossOrigin(origins = "*")
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<JobPostingDto> fetchJobPostingById(@PathVariable Long id) {
        var posting = jobPostingService.findById(id);
        return posting != null? ResponseEntity.ok(posting) : ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<Void> createJobPosting(@RequestBody JobPostingManipulationRequest request) throws URISyntaxException {
        JobPostingDto created = jobPostingService.create(request);
        URI uri = new URI("/api/jobpostings/" + created.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<JobPostingDto> updateJobPosting(@PathVariable Long id, @RequestBody JobPostingManipulationRequest request) {
        var posting = jobPostingService.update(id, request);
        return posting != null? ResponseEntity.ok(posting) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable Long id,
                                                 @RequestHeader(value = "X-Company-Id", required = false) Long companyId,
                                                 @RequestHeader(value = "X-User-Role", required = false) String role) {
        boolean isAdmin = "ADMIN".equalsIgnoreCase(role);
        boolean successful = jobPostingService.deleteById(id, companyId, isAdmin);
        return successful ? ResponseEntity.ok().build() : ResponseEntity.status(403).build();
    }


}




