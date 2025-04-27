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

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class JobPosting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String title;

    @ElementCollection @NotEmpty
    private List<@NotBlank String> skills;

    @Enumerated(EnumType.STRING) @NotNull
    private DegreeLevel minDegree;

    @ElementCollection @NotEmpty
    private List<@NotBlank String> languages;

    @NotNull private BigDecimal expectedSalary;

    @NotBlank private String location;
    private boolean remoteAllowed;
    private boolean requiresExperience;

    @Enumerated(EnumType.STRING) @NotNull
    private EmploymentType employmentType;

    @Enumerated(EnumType.STRING) @NotNull
    private WorkTime workTime;

    @Enumerated(EnumType.STRING) @NotNull
    private Industry industry;

    @ManyToOne @NotNull
    private Company company;

}
