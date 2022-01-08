package daif.aymane.showsManagement.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UpComingEpisode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long upComingEpisodeId;

    @OneToOne
    private Episode episode;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
