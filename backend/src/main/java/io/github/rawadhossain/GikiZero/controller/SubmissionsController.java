package io.github.rawadhossain.GikiZero.controller;

import io.github.rawadhossain.GikiZero.model.Submission;
import io.github.rawadhossain.GikiZero.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable String id) {
        return submissionService.getSubmissionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Submission> getSubmissionsByUser(@PathVariable String userId) {
        return submissionService.getSubmissionsByUser(userId);
    }

    @GetMapping("/user/{userId}/since")
    public List<Submission> getSubmissionsSince(
            @PathVariable String userId,
            @RequestParam("date") String date
    ) {
        LocalDateTime sinceDate = LocalDateTime.parse(date);
        return submissionService.getSubmissionsSince(userId, sinceDate);
    }

    @PostMapping
    public Submission createSubmission(@RequestBody Submission submission) {
        return submissionService.createSubmission(submission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable String id) {
        submissionService.deleteSubmission(id);
        return ResponseEntity.noContent().build();
    }
}
