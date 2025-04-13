// ScalaLibraryService.java
package ai.cimba.websummarizer.service;

import ai.cimba.scalalibrary.SummarizerLibraryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ScalaLibraryService {

    private final SummarizerLibraryWrapper summarizerLibrary;

    @Autowired
    public ScalaLibraryService(
            @Value("${spring.datasource.url}") String dbUrl,
            @Value("${spring.datasource.username}") String dbUser,
            @Value("${spring.datasource.password}") String dbPassword,
            @Value("${python.service.url}") String pythonServiceUrl) {

        try {
            System.out.println("Creating SummarizerLibraryWrapper with:");
            System.out.println("DB URL: " + dbUrl);
            System.out.println("User: " + dbUser);
            System.out.println("Python Service URL: " + pythonServiceUrl);

            if (dbUrl == null || dbUser == null || dbPassword == null || pythonServiceUrl == null) {
                throw new IllegalArgumentException("Some parameters are null!");
            }

            System.out.println("About to create the wrapper class...");
            this.summarizerLibrary = new SummarizerLibraryWrapper();
            System.out.println("Wrapper created successfully!");
        } catch (Exception e) {
            System.err.println("Failed to create SummarizerLibraryWrapper:");
            e.printStackTrace();
            throw new RuntimeException("Failed to create SummarizerLibraryWrapper", e);
        }
    }

    public String summarizeWebsite(String url) {
        try {
            return summarizerLibrary.summarize(url);
        } catch (Exception e) {
            System.err.println("Error during website summarization:");
            e.printStackTrace();  // This will print the root cause
            return "Error during summarization"; // or any other appropriate error message
        }
    }
}
