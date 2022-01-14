package daif.aymane.showsManagement.dto.TVShows;


import daif.aymane.showsManagement.models.ShowState;


public class TVShowDto {

    private Long showId;
    private String name;
    private boolean isCompleted;
    private ShowState showState;

    private Long lastSeenEpisodeId;

    private Long upComingEpisodeId;

    private Long userId;

    private String imageFileId;

    public TVShowDto(){}

    public TVShowDto(Long showId, String name, boolean isCompleted, ShowState showState, Long lastSeenEpisodeId, Long upComingEpisodeId, Long userId, String imageFileId) {
        this.showId = showId;
        this.name = name;
        this.isCompleted = isCompleted;
        this.showState = showState;
        this.lastSeenEpisodeId = lastSeenEpisodeId;
        this.upComingEpisodeId = upComingEpisodeId;
        this.userId = userId;
        this.imageFileId = imageFileId;
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
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public ShowState getShowState() {
        return showState;
    }

    public void setShowState(ShowState showState) {
        this.showState = showState;
    }

    public Long getLastSeenEpisodeId() {
        return lastSeenEpisodeId;
    }

    public void setLastSeenEpisodeId(Long lastSeenEpisodeId) {
        this.lastSeenEpisodeId = lastSeenEpisodeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUpComingEpisodeId() {
        return upComingEpisodeId;
    }

    public void setUpComingEpisodeId(Long upComingEpisodeId) {
        this.upComingEpisodeId = upComingEpisodeId;
    }

    public String getImageFileId() {
        return imageFileId;
    }

    public void setImageFileId(String imageFileId) {
        this.imageFileId = imageFileId;
    }
}
