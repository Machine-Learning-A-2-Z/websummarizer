// File: backend/src/main/java/ai/cimba/scalalibrary/SummarizerLibraryWrapper.java
package ai.cimba.scalalibrary;

import ai.cimba.scalaSummarizer.SummarizerLibrary;

public class SummarizerLibraryWrapper {
    private final SummarizerLibrary summarizer;

    public SummarizerLibraryWrapper(String dbUrl, String dbUser, String dbPassword, String pythonServiceUrl) {
        // Create the Scala library instance with custom params
        this.summarizer = new SummarizerLibrary(dbUrl, dbUser, dbPassword, pythonServiceUrl);
    }
    
    public String summarize(String url) {
        return summarizer.summarize(url);
    }
}