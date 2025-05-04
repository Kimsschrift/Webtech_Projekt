package htw_berlin.webtech.service;

import htw_berlin.webtech.domain.JobPosting;
import htw_berlin.webtech.domain.enums.DegreeLevel;
import htw_berlin.webtech.domain.enums.EmploymentType;
import htw_berlin.webtech.domain.enums.Industry;
import htw_berlin.webtech.domain.enums.WorkTime;
import htw_berlin.webtech.dto.JobPostingDto;
import htw_berlin.webtech.repository.JobPostingRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    public JobPostingService(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }

    @Transactional
    public List<JobPostingDto> getAllJobPostings() {
        List<JobPosting> postings = jobPostingRepository.findAll();
        return postings.stream()
                .map(posting -> new JobPostingDto(posting))
                .toList();
    }
}
