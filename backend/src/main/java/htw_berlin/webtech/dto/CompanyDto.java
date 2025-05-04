package htw_berlin.webtech.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {
    private Long id;
    private String name;
    private String address;
    private String legalForm;
    private String website;
    private String contactEmail;
}
