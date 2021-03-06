package tn.esprit.project.Config;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.CalendarScopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Auto-configuration for {@link GoogleCredential}, {@link NetHttpTransport}, and {@link
 * JacksonFactory}.
 */
@Configuration
public class GoogleApiClientConfig {
    /**
     * Provides a unmodifiable {@link Set<String>} of Google OAuth 2.0 scopes to be used.
     *
     * @return An unmodifiable {@link Set<String>}
     */
    private Set<String> googleOAuth2Scopes() {

        Set<String> googleOAuth2Scopes = new HashSet<>();
        googleOAuth2Scopes.add(CalendarScopes.CALENDAR_READONLY);
        googleOAuth2Scopes.add(CalendarScopes.CALENDAR);
        return Collections.unmodifiableSet(googleOAuth2Scopes);
    }

    /**
     * Provides a global {@link GoogleCredential} instance for use for all Google API calls.
     *
     * @return {@link GoogleCredential}
     */
    @Bean
    @Deprecated
    public GoogleCredential googleCredential() throws IOException {
        File serviceAccount = new ClassPathResource("well-being-2022-e5c98296e8d7.json").getFile();
        return GoogleCredential.fromStream(new FileInputStream(serviceAccount))
                .createScoped(googleOAuth2Scopes());
    }

    /**
     * A preconfigured HTTP client for calling out to Google APIs.
     *
     * @return {@link NetHttpTransport}
     */
    @Bean
    public NetHttpTransport netHttpTransport() throws GeneralSecurityException, IOException {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    /**
     * Abstract low-level JSON factory.
     *
     * @return {@link JacksonFactory}
     */
    @Bean
    public JacksonFactory jacksonFactory() {
        return JacksonFactory.getDefaultInstance();
    }
}
