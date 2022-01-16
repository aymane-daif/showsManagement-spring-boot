package daif.aymane.showsManagement.dto.UpComingEpisodes;


import java.time.LocalDate;

public class UpComingEpisodeDto {
    private Long upComingEpisodeId;

    private String upComingEpisode;
    private String upComingSeason;

    private LocalDate releaseDate;

    public UpComingEpisodeDto(){}

    public UpComingEpisodeDto(Long upComingEpisodeId, String upComingEpisode, String upComingSeason, LocalDate releaseDate) {
        this.upComingEpisodeId = upComingEpisodeId;
        this.upComingEpisode = upComingEpisode;
        this.upComingSeason = upComingSeason;
        this.releaseDate = releaseDate;
    }

    public Long getUpComingEpisodeId() {
        return upComingEpisodeId;
    }

    public void setUpComingEpisodeId(Long upComingEpisodeId) {
        this.upComingEpisodeId = upComingEpisodeId;
    }

    public String getUpComingEpisode() {
        return upComingEpisode;
    }

    public void setUpComingEpisode(String upComingEpisode) {
        this.upComingEpisode = upComingEpisode;
    }

    public String getUpComingSeason() {
        return upComingSeason;
    }

    public void setUpComingSeason(String upComingSeason) {
        this.upComingSeason = upComingSeason;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
