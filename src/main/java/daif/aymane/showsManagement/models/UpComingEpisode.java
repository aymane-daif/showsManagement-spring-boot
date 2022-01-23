package daif.aymane.showsManagement.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class UpComingEpisode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long upComingEpisodeId;

    @OneToOne
    private Episode episode;

    @DateTimeFormat
    private LocalDateTime releaseDate;

    public UpComingEpisode(){}

    public UpComingEpisode(Long upComingEpisodeId, Episode episode) {
        this.upComingEpisodeId = upComingEpisodeId;
        this.episode = episode;
    }

    public Long getUpComingEpisodeId() {
        return upComingEpisodeId;
    }

    public void setUpComingEpisodeId(Long upComingEpisodeId) {
        this.upComingEpisodeId = upComingEpisodeId;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime newDate = LocalDateTime.parse(releaseDate, formatter);
        this.releaseDate = newDate;
    }
}
