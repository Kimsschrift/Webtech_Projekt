package htw_berlin.webtech.service;

import htw_berlin.webtech.domain.Company;
import htw_berlin.webtech.domain.JobPosting;
import htw_berlin.webtech.domain.JobPostingCreateRequest;
import htw_berlin.webtech.domain.enums.DegreeLevel;
import htw_berlin.webtech.domain.enums.EmploymentType;
import htw_berlin.webtech.domain.enums.Industry;
import htw_berlin.webtech.domain.enums.WorkTime;
import htw_berlin.webtech.dto.JobPostingDto;
import htw_berlin.webtech.repository.CompanyRepository;
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
    private final CompanyRepository companyRepository;

    public JobPostingService(JobPostingRepository jobPostingRepository, CompanyRepository companyRepository) {
        this.jobPostingRepository = jobPostingRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public List<JobPostingDto> getAllJobPostings() {
        List<JobPosting> postings = jobPostingRepository.findAll();
        return postings.stream()
                .map(posting -> new JobPostingDto(posting))
                .toList();
    }

    @Transactional
    public JobPostingDto findById(Long id) {
        return jobPostingRepository.findById(id)
                .map(JobPostingDto::new)
                .orElse(null);
    }


    public JobPostingDto create(JobPostingCreateRequest request) {
        JobPosting jobPostingEntity = new JobPosting();
        jobPostingEntity.setTitle(request.getTitle());
        jobPostingEntity.setSkills(request.getSkills());
        jobPostingEntity.setLanguages(request.getLanguages());
        jobPostingEntity.setMinDegree(request.getMinDegree());
        jobPostingEntity.setExpectedSalary(request.getExpectedSalary());
        jobPostingEntity.setLocation(request.getLocation());
        jobPostingEntity.setRemoteAllowed(request.isRemoteAllowed());
        jobPostingEntity.setRequiresExperience(request.isRequiresExperience());
        jobPostingEntity.setEmploymentType(request.getEmploymentType());
        jobPostingEntity.setWorkTime(request.getWorkTime());
        jobPostingEntity.setIndustry(request.getIndustry());

        Long companyId = request.getCompany().getId();
        Company company = companyRepository.findById(companyId)
                        .orElseThrow(() -> new IllegalArgumentException("CompanyId not found: " + companyId));


        jobPostingEntity.setCompany(company);

        JobPosting saved = jobPostingRepository.save(jobPostingEntity);
        return new JobPostingDto(saved);
    }
}
