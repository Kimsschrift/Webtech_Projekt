package htw_berlin.webtech.domain;

import htw_berlin.webtech.domain.enums.UserRole;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "appUser")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Company company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Applicant applicants;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean enabled;

}
