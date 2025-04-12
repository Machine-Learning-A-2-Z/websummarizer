// File: backend/src/main/java/ai/cimba/websummarizer/controller/SummarizerController.java
package ai.cimba.websummarizer.controller;

import ai.cimba.websummarizer.model.SummarizationRequest;
import ai.cimba.websummarizer.repository.SummarizationRequestRepository;
import ai.cimba.websummarizer.service.ScalaLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SummarizerController {

    private final ScalaLibraryService scalaLibraryService;
    private final SummarizationRequestRepository requestRepository;

    @Autowired
    public SummarizerController(ScalaLibraryService scalaLibraryService, SummarizationRequestRepository requestRepository) {
        this.scalaLibraryService = scalaLibraryService;
        this.requestRepository = requestRepository;
    }

    @PostMapping("/summarize")
    public ResponseEntity<Map<String, String>> summarizeWebsite(@RequestBody Map<String, String> request) {
        String url = request.get("url");
        
        if (url == null || url.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "URL is required"));
        }
        
        try {
            String summary = scalaLibraryService.summarizeWebsite(url);
            
            // Save to database
            SummarizationRequest summRequest = new SummarizationRequest(url, summary);
            requestRepository.save(summRequest);
            
            Map<String, String> response = new HashMap<>();
            response.put("summary", summary);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Failed to summarize website: " + e.getMessage()));
        }
    }
    
    @GetMapping("/history")
    public ResponseEntity<List<SummarizationRequest>> getHistory() {
        List<SummarizationRequest> history = requestRepository.findAllByOrderByTimestampDesc();
        return ResponseEntity.ok(history);
    }
}