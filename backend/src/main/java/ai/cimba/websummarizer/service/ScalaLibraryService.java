// File: backend/src/main/java/ai/cimba/websummarizer/service/ScalaLibraryService.java
package ai.cimba.websummarizer.service;

import ai.cimba.scalalibrary.SummarizerLibraryWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ScalaLibraryService {
    
    private final SummarizerLibraryWrapper summarizerLibrary;
    
    public ScalaLibraryService(
            @Value("${spring.datasource.url}") String dbUrl,
            @Value("${spring.datasource.username}") String dbUser,
            @Value("${spring.datasource.password}") String dbPassword,
            @Value("${python.service.url}") String pythonServiceUrl) {
        this.summarizerLibrary = new SummarizerLibraryWrapper(dbUrl, dbUser, dbPassword, pythonServiceUrl);
    }
    
    public String summarizeWebsite(String url) {
        return summarizerLibrary.summarize(url);
    }
}