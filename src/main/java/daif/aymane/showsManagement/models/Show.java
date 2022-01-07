package daif.aymane.showsManagement.models;

import javax.persistence.*;

@Entity
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isCompleted;

    @Enumerated(value = EnumType.STRING)
    private ShowState showState;

    @OneToOne
    private Episode last_seen_episode;

    @ManyToOne
    private AppUser user;

    public Show(){}

    public Show(Long id, boolean isCompleted, ShowState showState, Episode last_seen_episode, AppUser user) {
        this.id = id;
        this.isCompleted = isCompleted;
        this.showState = showState;
        this.last_seen_episode = last_seen_episode;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Episode getLast_seen_episode() {
        return last_seen_episode;
    }

    public void setLast_seen_episode(Episode last_seen_episode) {
        this.last_seen_episode = last_seen_episode;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
