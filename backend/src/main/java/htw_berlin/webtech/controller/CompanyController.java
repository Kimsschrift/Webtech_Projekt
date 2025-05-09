package htw_berlin.webtech.controller;


import htw_berlin.webtech.domain.Company;
import htw_berlin.webtech.domain.CompanyManipulationRequest;
import htw_berlin.webtech.domain.JobPostingManipulationRequest;
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
    public ResponseEntity<CompanyDto> registerCompany(@RequestBody CompanyManipulationRequest request) {
        var company = companyService.createCompany(request);
        return ResponseEntity.status(201).body(company);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CompanyDto> fetchCompanyById(@PathVariable Long id) {
        var company = companyService.findById(id);
        return company != null? ResponseEntity.ok(company) : ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long id, @RequestBody CompanyManipulationRequest request) {
        var company = companyService.update(id, request);
        return company != null? ResponseEntity.ok(company) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<CompanyDto> deleteCompany(@PathVariable Long id) {
        boolean successful = companyService.deleteById(id);
        return successful ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}
