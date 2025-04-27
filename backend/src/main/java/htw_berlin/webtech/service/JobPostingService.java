package htw_berlin.webtech.service;

import htw_berlin.webtech.domain.JobPosting;
import htw_berlin.webtech.domain.enums.DegreeLevel;
import htw_berlin.webtech.domain.enums.EmploymentType;
import htw_berlin.webtech.domain.enums.Industry;
import htw_berlin.webtech.domain.enums.WorkTime;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class JobPostingService {

    // M1-Stufe: liefert einfach ein einzelnes Beispiel-Objekt zurück.
    // bei späteren Stufen ersetze ich das durch DB-Zugriff

    public JobPosting getJobPosting() {
        return JobPosting.builder()
                .id(1L)
                .title("Java Backend Developer")
                .skills(List.of("Java", "SQL"))
                .minDegree(DegreeLevel.BACHELOR)
                .languages(List.of("German", "English"))
                .expectedSalary(new BigDecimal("50000"))
                .location("Berlin")
                .remoteAllowed(false)
                .requiresExperience(true)
                .employmentType(EmploymentType.FULL_TIME)
                .workTime(WorkTime.FULL_TIME)
                .industry(Industry.IT)
                .company(null)  // noch kein Company-Objekt
                .build();
    }

    // später real implementieren
    public List<JobPosting> listAll() {
        return Collections.singletonList(getJobPosting());
    }
}
