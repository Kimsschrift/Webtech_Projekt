package htw_berlin.webtech;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Job title cannot be blank")
    private String title;

    @ElementCollection
    @NotEmpty(message = "At least one skill is required")
    private List<String> skills;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Education level must be provided")
    private DegreeLevel education;

    @ElementCollection
    private List<String> languages;

    @Min(value = 0, message = "Expected salary must not be negative")
    private int expectedSalary;

    @NotBlank(message = "Location must be provided")
    private String location;

    private boolean homeoffice;
    private Branche branche;
    private boolean jobExperience;
    private Anstellungsart anstellungsart;
    private Arbeitszeit arbeitszeit;


}
