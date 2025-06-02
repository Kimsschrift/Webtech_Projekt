package htw_berlin.webtech.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "applicant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    private String phoneNumber;

    private LocalDate birthDate;

    private String cvLink;

    @Column(columnDefinition = "TEXT")
    private String resumeText;

    private String profileImageUrl;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
}
