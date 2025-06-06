package htw_berlin.webtech.service;

import htw_berlin.webtech.domain.Company;
import htw_berlin.webtech.domain.JobPosting;
import htw_berlin.webtech.domain.JobPostingManipulationRequest;
import htw_berlin.webtech.dto.JobPostingDto;
import htw_berlin.webtech.repository.CompanyRepository;
import htw_berlin.webtech.repository.JobPostingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

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


    public JobPostingDto create(JobPostingManipulationRequest request) {
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

        jobPostingEntity.setDescription(request.getDescription());
        jobPostingEntity.setTaskArea(request.getTaskArea());
        jobPostingEntity.setUserProfile(request.getUserProfile());
        jobPostingEntity.setBenefits(request.getBenefits());

        Long companyId = request.getCompany().getId();
        Company company = companyRepository.findById(companyId)
                        .orElseThrow(() -> new IllegalArgumentException("CompanyId not found: " + companyId));


        jobPostingEntity.setCompany(company);

        JobPosting saved = jobPostingRepository.save(jobPostingEntity);
        return new JobPostingDto(saved);
    }

    public JobPostingDto update(Long id, JobPostingManipulationRequest request) {
        var jobPostingEntityOptional = jobPostingRepository.findById(id);
        if (jobPostingEntityOptional.isEmpty()) {
            return null;
        }
        var jobPostingEntity = jobPostingEntityOptional.get();
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

        jobPostingEntity.setDescription(request.getDescription());
        jobPostingEntity.setTaskArea(request.getTaskArea());
        jobPostingEntity.setUserProfile(request.getUserProfile());
        jobPostingEntity.setBenefits(request.getBenefits());

        Long companyId = request.getCompany().getId();
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("CompanyId not found: " + companyId));


        jobPostingEntity.setCompany(company);

        JobPosting saved = jobPostingRepository.save(jobPostingEntity);
        return new JobPostingDto(jobPostingEntity);
    }

    public boolean deleteById(Long id, Long companyId) {
        return jobPostingRepository.findById(id)
                .filter(post -> post.getCompany().getId().equals(companyId))
                .map(post -> {
                    jobPostingRepository.delete(post);
                    return true;
                })
                .orElse(false);

    }
}
