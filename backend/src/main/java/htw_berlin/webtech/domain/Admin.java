package htw_berlin.webtech.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_code", unique = true, nullable = false)
    private String adminCode;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
}
