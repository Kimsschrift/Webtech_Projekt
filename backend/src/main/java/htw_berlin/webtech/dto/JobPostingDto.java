package htw_berlin.webtech.dto;

import htw_berlin.webtech.domain.JobPosting;
import htw_berlin.webtech.domain.enums.DegreeLevel;
import htw_berlin.webtech.domain.enums.EmploymentType;
import htw_berlin.webtech.domain.enums.Industry;
import htw_berlin.webtech.domain.enums.WorkTime;
import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JobPostingDto {
    private Long id;
    private String title;
    private List<String> skills;
    private DegreeLevel minDegree;
    private List<String> languages;
    private BigDecimal expectedSalary;
    private String location;
    private boolean remoteAllowed;
    private boolean requiresExperience;
    private EmploymentType employmentType;
    private WorkTime workTime;
    private Industry industry;
    private CompanyDto company;

    // für DetailJobpostings
    private String description;
    private String taskArea;
    private String userProfile;
    private String benefits;

    public JobPostingDto(JobPosting posting) {
        this.id = posting.getId();
        this.title = posting.getTitle();
        this.skills = new ArrayList<>(posting.getSkills());
        this.languages = new ArrayList<>(posting.getLanguages());
        this.minDegree = posting.getMinDegree();
        this.expectedSalary = posting.getExpectedSalary();
        this.location = posting.getLocation();
        this.remoteAllowed = posting.isRemoteAllowed();
        this.requiresExperience = posting.isRequiresExperience();
        this.employmentType = posting.getEmploymentType();
        this.workTime = posting.getWorkTime();
        this.industry = posting.getIndustry();
        this.company = new CompanyDto(posting.getCompany());

        // für DetailJobpostings
        this.description = posting.getDescription();
        this.taskArea = posting.getTaskArea();
        this.userProfile = posting.getUserProfile();
        this.benefits = posting.getBenefits();
    }

}
