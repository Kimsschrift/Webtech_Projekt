package htw_berlin.webtech.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "company")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String legalForm;

    private String website;

    @Email
    private String email;

    private String phoneNumber;

    private Integer foundedYear;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String logoUrl;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<JobPosting> jobPostings = new ArrayList<>();

}
