package daif.aymane.showsManagement.dto.UpComingEpisodes;


import java.time.LocalDate;

public class UpComingEpisodeDto {
    private Long upComingEpisodeId;

    private Long episodeId;

    private LocalDate releaseDate;

    public Long getUpComingEpisodeId() {
        return upComingEpisodeId;
    }

    public void setUpComingEpisodeId(Long upComingEpisodeId) {
        this.upComingEpisodeId = upComingEpisodeId;
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
