package htw_berlin.webtech.dto;

import htw_berlin.webtech.domain.Applicant;
import htw_berlin.webtech.domain.Company;
import htw_berlin.webtech.domain.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUserDto {

    private Long id;
    private UserRole role;
}

