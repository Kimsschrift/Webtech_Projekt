package htw_berlin.webtech.service;

import htw_berlin.webtech.domain.Company;
import htw_berlin.webtech.domain.CompanyCreateRequest;
import htw_berlin.webtech.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(CompanyCreateRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        company.setAddress(request.getAddress());
        company.setLegalForm(request.getLegalForm());
        company.setWebsite(request.getWebsite());
        company.setContactEmail(request.getContactEmail());


        return companyRepository.save(company);
    }
}
