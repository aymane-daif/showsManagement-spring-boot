package daif.aymane.showsManagement.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UpComingEpisode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Episode episode;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    public UpComingEpisode(){}

    public UpComingEpisode(Long id, Episode episode) {
        this.id = id;
        this.episode = episode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
}
