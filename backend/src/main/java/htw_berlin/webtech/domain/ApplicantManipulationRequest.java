package htw_berlin.webtech.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApplicantManipulationRequest {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}
