package daif.aymane.showsManagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class TVShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;

    private String name;

    private boolean isCompleted;

    @Enumerated(value = EnumType.STRING)
    private ShowState showState;

    @OneToOne
    private Episode lastSeenEpisode;

    @OneToOne
    private UpComingEpisode upComingEpisode;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;

    public TVShow(){}

    public TVShow(Long showId, String name, boolean isCompleted, ShowState showState, Episode lastSeenEpisode, UpComingEpisode upComingEpisode, AppUser user) {
        this.showId = showId;
        this.name = name;
        this.isCompleted = isCompleted;
        this.showState = showState;
        this.lastSeenEpisode = lastSeenEpisode;
        this.upComingEpisode = upComingEpisode;
        this.user = user;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
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

    public Episode getLastSeenEpisode() {
        return lastSeenEpisode;
    }

    public void setLastSeenEpisode(Episode lastSeenEpisode) {
        this.lastSeenEpisode = lastSeenEpisode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public UpComingEpisode getUpComingEpisode() {
        return upComingEpisode;
    }

    public void setUpComingEpisode(UpComingEpisode upComingEpisode) {
        this.upComingEpisode = upComingEpisode;
    }
}
