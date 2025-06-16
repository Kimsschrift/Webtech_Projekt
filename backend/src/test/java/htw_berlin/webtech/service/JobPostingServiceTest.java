package htw_berlin.webtech.service;

import htw_berlin.webtech.domain.JobPosting;
import htw_berlin.webtech.domain.enums.DegreeLevel;
import htw_berlin.webtech.domain.enums.EmploymentType;
import htw_berlin.webtech.domain.enums.Industry;
import htw_berlin.webtech.domain.enums.WorkTime;
import htw_berlin.webtech.dto.JobPostingDto;
import htw_berlin.webtech.repository.CompanyRepository;
import htw_berlin.webtech.repository.JobPostingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobPostingServiceTest {

    @Mock
    JobPostingRepository jobPostingRepository;
    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    JobPostingService jobPostingService;

    @Test
    void getAllJobPostings_returnsDtos() {
        JobPosting post = new JobPosting();
        post.setId(1L);
        post.setTitle("Dev");
        post.setSkills(List.of("Java"));
        post.setLanguages(List.of("EN"));
        post.setMinDegree(DegreeLevel.BACHELOR);
        post.setExpectedSalary(BigDecimal.ONE);
        post.setLocation("Berlin");
        post.setRemoteAllowed(true);
        post.setRequiresExperience(false);
        post.setEmploymentType(EmploymentType.FULL_TIME);
        post.setWorkTime(WorkTime.FULL_TIME);
        post.setIndustry(Industry.IT);
        var company = new htw_berlin.webtech.domain.Company();
        company.setId(1L);
        company.setName("TestCorp");
        post.setCompany(company);

        when(jobPostingRepository.findAll()).thenReturn(List.of(post));

        List<JobPostingDto> result = jobPostingService.getAllJobPostings();

        assertEquals(1, result.size());
        assertEquals("Dev", result.get(0).getTitle());
    }
}
