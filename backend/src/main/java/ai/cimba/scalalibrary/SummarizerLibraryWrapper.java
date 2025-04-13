// File: backend/src/main/java/ai/cimba/scalalibrary/SummarizerLibraryWrapper.java
package ai.cimba.scalalibrary;

import ai.cimba.scalaSummarizer.SummarizerLibrary;

public class SummarizerLibraryWrapper {

    private SummarizerLibrary summarizer = new SummarizerLibrary(
            "jdbc:postgresql://localhost:5432/websummarizer",
            "postgres",
            "Isroraj@2022",
            "http://localhost:8000/summarize"
    );

    public SummarizerLibraryWrapper() {
        // Create the Scala library instance with custom params
        this.summarizer = new SummarizerLibrary(
                "jdbc:postgresql://localhost:5432/websummarizer",
                "postgres",
                "Isroraj@2022",
                "http://localhost:8000/summarize"
        );
    }
    
    public String summarize(String url) {
        return summarizer.summarize(url);
    }
}