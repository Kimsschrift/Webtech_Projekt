package htw_berlin.webtech.service;

import htw_berlin.webtech.domain.Company;
import htw_berlin.webtech.domain.CompanyManipulationRequest;
import htw_berlin.webtech.dto.CompanyDto;
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


    public CompanyDto createCompany(CompanyManipulationRequest request) {
        Company companyEntity = new Company();
        companyEntity.setName(request.getName());
        companyEntity.setAddress(request.getAddress());
        companyEntity.setLegalForm(request.getLegalForm());
        companyEntity.setWebsite(request.getWebsite());
        companyEntity.setEmail(request.getContactEmail());

        Company saved = companyRepository.save(companyEntity);
        return new CompanyDto(saved);
    }

    public CompanyDto update(Long id, CompanyManipulationRequest request) {
        var companyEntityOptional = companyRepository.findById(id);
        if (companyEntityOptional.isEmpty()) {
            return null;
        }
        var companyEntity = companyEntityOptional.get();
        companyEntity.setName(request.getName());
        companyEntity.setAddress(request.getAddress());
        companyEntity.setLegalForm(request.getLegalForm());
        companyEntity.setWebsite(request.getWebsite());
        companyEntity.setEmail(request.getContactEmail());

        Company saved = companyRepository.save(companyEntity);
        return new CompanyDto(saved);
    }

    public boolean deleteById(long id) {
        if (!companyRepository.existsById(id)) {
            return false;
        }
        companyRepository.deleteById(id);
        return true;
    }
}
