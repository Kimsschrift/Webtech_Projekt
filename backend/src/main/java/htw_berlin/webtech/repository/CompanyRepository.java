package htw_berlin.webtech.repository;

import htw_berlin.webtech.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
