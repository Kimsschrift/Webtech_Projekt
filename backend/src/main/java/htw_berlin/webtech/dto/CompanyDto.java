package htw_berlin.webtech.dto;

import htw_berlin.webtech.domain.Company;
import lombok.*;

@Getter
@Setter
public class CompanyDto {
    private Long id;
    private String name;
    private String address;
    private String legalForm;
    private String website;
    private String contactEmail;

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
        this.legalForm = company.getLegalForm();
        this.website = company.getWebsite();
        this.contactEmail = company.getContactEmail();
    }
}
