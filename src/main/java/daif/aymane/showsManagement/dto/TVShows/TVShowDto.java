package daif.aymane.showsManagement.dto.TVShows;


import daif.aymane.showsManagement.models.ShowState;
import org.springframework.web.multipart.MultipartFile;


public class TVShowDto {

    private Long showId;
    private String name;
    private boolean completed;
    private ShowState showState;
    private String showEmoji;


    private String lastSeenEpisode;
    private String lastSeenSeason;

    private String upComingEpisode;
    private String upComingSeason;


    private String posterId;

    public TVShowDto(){}

    public TVShowDto(Long showId, String name, boolean completed, ShowState showState, String showEmoji, String lastSeenEpisode, String lastSeenSeason, String upComingEpisode, String upComingSeason, String posterId) {
        this.showId = showId;
        this.name = name;
        this.completed = completed;
        this.showState = showState;
	this.showEmoji = showEmoji;
        this.lastSeenEpisode = lastSeenEpisode;
        this.lastSeenSeason = lastSeenSeason;
        this.upComingEpisode = upComingEpisode;
        this.upComingSeason = upComingSeason;
        this.posterId = posterId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public ShowState getShowState() {
        return showState;
    }

    public void setShowState(ShowState showState) {
        this.showState = showState;
    }

    public String getLastSeenEpisode() {
        return lastSeenEpisode;
    }

    public void setLastSeenEpisode(String lastSeenEpisode) {
        this.lastSeenEpisode = lastSeenEpisode;
    }

    public String getLastSeenSeason() {
        return lastSeenSeason;
    }

    public void setLastSeenSeason(String lastSeenSeason) {
        this.lastSeenSeason = lastSeenSeason;
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

    public String getPosterId() {
        return posterId;
    }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    public String getShowEmoji() {
	return showEmoji;
    }

    public void setShowEmoji(String showEmoji) {
   	this.showEmoji = showEmoji;
    }
}
