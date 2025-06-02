package htw_berlin.webtech.domain;

import htw_berlin.webtech.domain.enums.DegreeLevel;
import htw_berlin.webtech.domain.enums.EmploymentType;
import htw_berlin.webtech.domain.enums.Industry;
import htw_berlin.webtech.domain.enums.WorkTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "job_posting")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @ElementCollection
    @CollectionTable(
            name = "job_posting_skills",
            joinColumns = @JoinColumn(name = "job_posting_id", referencedColumnName = "id"))
    @Column(name = "skills", nullable = false)
    @NotEmpty
    private List<@NotBlank String> skills;

    @Enumerated(EnumType.STRING)
    @NotNull
    private DegreeLevel minDegree;

    @ElementCollection
    @CollectionTable(
            name = "job_posting_languages",
            joinColumns = @JoinColumn(name = "job_posting_id",  referencedColumnName = "id"))
    @Column(name = "languages", nullable = false)
    @NotEmpty
    private List<@NotBlank String> languages;

    @NotNull
    private BigDecimal expectedSalary;

    @NotBlank
    private String location;

    private boolean remoteAllowed;

    private boolean requiresExperience;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EmploymentType employmentType;

    @Enumerated(EnumType.STRING)
    @NotNull
    private WorkTime workTime;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Industry industry;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false, referencedColumnName = "id")
    @NotNull
    private Company company;


    // für DetailJobpostings
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String taskArea;

    @Column(columnDefinition = "TEXT")
    private String userProfile;

    @Column(columnDefinition = "TEXT")
    private String benefits;
}
