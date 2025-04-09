package htw_berlin.webtech;

import jakarta.persistence.*;
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
    private String title;

    @ElementCollection
    private List<String> skills;

    @Enumerated(EnumType.STRING)
    private DegreeLevel education;

    @ElementCollection
    private List<String> languages;
    private int expectedSalary;
    private String location;





}
