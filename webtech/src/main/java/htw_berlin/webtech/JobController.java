package htw_berlin.webtech;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @GetMapping("/example")
    public ResponseEntity<Job> getJob() {
        Job job = new Job(
                null,
                "Java Backend Developer",
                List.of("Java", "Oracle SQL", "R", "JavaScript", "CSS", "HTML"),
                DegreeLevel.BACHELOR,
                List.of("German", "English"),
                50000,
                "Berlin"

        );
        return ResponseEntity.ok(job);

    }
}
