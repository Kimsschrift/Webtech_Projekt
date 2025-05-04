package htw_berlin.webtech.dto;

import htw_berlin.webtech.domain.enums.DegreeLevel;
import htw_berlin.webtech.domain.enums.EmploymentType;
import htw_berlin.webtech.domain.enums.Industry;
import htw_berlin.webtech.domain.enums.WorkTime;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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



}
