package htw_berlin.webtech.service;

import htw_berlin.webtech.domain.Company;
import htw_berlin.webtech.domain.CompanyCreateRequest;
import htw_berlin.webtech.domain.JobPosting;
import htw_berlin.webtech.dto.CompanyDto;
import htw_berlin.webtech.dto.JobPostingDto;
import htw_berlin.webtech.repository.CompanyRepository;
import htw_berlin.webtech.repository.JobPostingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final JobPostingRepository jobPostingRepository;

    public CompanyService(CompanyRepository companyRepository, JobPostingRepository jobPostingRepository) {
        this.companyRepository = companyRepository;
        this.jobPostingRepository = jobPostingRepository;
    }

    @Transactional
    public CompanyDto findById(long id) {
        return companyRepository.findById(id)
                .map(CompanyDto::new)
                .orElse(null);
    }

    private CompanyDto transformEntity(Company companyEntity) {
        return new CompanyDto(companyEntity);
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
