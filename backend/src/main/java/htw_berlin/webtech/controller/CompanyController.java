package htw_berlin.webtech.controller;


import htw_berlin.webtech.domain.Company;
import htw_berlin.webtech.domain.CompanyCreateRequest;
import htw_berlin.webtech.dto.CompanyDto;
import htw_berlin.webtech.dto.JobPostingDto;
import htw_berlin.webtech.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domain/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Company> registerCompany(@RequestBody CompanyCreateRequest request) {
        Company company = companyService.createCompany(request);
        return ResponseEntity.status(201).body(company);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CompanyDto> fetchCompanyById(@PathVariable Long id) {
        var company = companyService.findById(id);
        return company != null? ResponseEntity.ok(company) : ResponseEntity.notFound().build();
    }

}
