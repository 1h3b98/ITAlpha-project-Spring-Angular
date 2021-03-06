package tn.esprit.project.Config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Auto-configuration for {@link Calendar}. */
@Configuration
public class GoogleCalendarConfig {
    @Deprecated
    private GoogleCredential googleCredential;
    private NetHttpTransport netHttpTransport;
    private JacksonFactory jacksonFactory;

    public GoogleCalendarConfig  (
            @Deprecated
            GoogleCredential googleCredential,
            NetHttpTransport netHttpTransport,
            JacksonFactory jacksonFactory) {
        this.googleCredential = googleCredential;
        this.netHttpTransport = netHttpTransport;
        this.jacksonFactory = jacksonFactory;
    }

    /**
     * Provides a Google Calendar client.
     *
     * @return {@link Calendar}
     */
    @Bean
    public Calendar googleCalendar() {
        return new Calendar(netHttpTransport, jacksonFactory, googleCredential);
    }
}
