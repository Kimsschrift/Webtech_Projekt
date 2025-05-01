package htw_berlin.webtech.service;

import htw_berlin.webtech.domain.JobPosting;
import htw_berlin.webtech.domain.enums.DegreeLevel;
import htw_berlin.webtech.domain.enums.EmploymentType;
import htw_berlin.webtech.domain.enums.Industry;
import htw_berlin.webtech.domain.enums.WorkTime;
import htw_berlin.webtech.repository.JobPostingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    public JobPostingService(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }


    // M1-Stufe: liefert einfach ein einzelnes Beispiel-Objekt zurück.
    // bei späteren Stufen ersetze ich das durch DB-Zugriff

    public JobPosting getJobPosting() {
        return jobPostingRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("JobPosting mit ID 1 nicht gefunden"));
    }
    public List<JobPosting> listAll() {
        return jobPostingRepository.findAll();
    }
}
