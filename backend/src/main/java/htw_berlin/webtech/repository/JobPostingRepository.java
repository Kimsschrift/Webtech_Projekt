package htw_berlin.webtech.repository;

import htw_berlin.webtech.domain.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
}
