package htw_berlin.webtech.domain;

import htw_berlin.webtech.domain.enums.DegreeLevel;
import htw_berlin.webtech.domain.enums.EmploymentType;
import htw_berlin.webtech.domain.enums.Industry;
import htw_berlin.webtech.domain.enums.WorkTime;
import htw_berlin.webtech.dto.CompanyDto;
import htw_berlin.webtech.dto.JobPostingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Diese Klasse dient dazu, bei Stellenausschreibungen
 * auf der Website die von der Firma angebotenen Leistungen
 * und die Anforderungen an Bewerber zu erfassen und in der Datenbank zu speichern.
 * **/



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobPostingCreateRequest {
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

    public JobPostingCreateRequest(JobPosting posting) {
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
    }
}
