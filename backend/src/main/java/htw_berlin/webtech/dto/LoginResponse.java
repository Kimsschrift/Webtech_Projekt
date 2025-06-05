package htw_berlin.webtech.dto;

import htw_berlin.webtech.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private Long userId;
    private UserRole role;
    private Long companyId;
    private Long applicantId;
}