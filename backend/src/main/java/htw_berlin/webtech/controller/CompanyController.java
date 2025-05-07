package htw_berlin.webtech.controller;


import htw_berlin.webtech.domain.Company;
import htw_berlin.webtech.domain.CompanyCreateRequest;
import htw_berlin.webtech.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companies")
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
}
