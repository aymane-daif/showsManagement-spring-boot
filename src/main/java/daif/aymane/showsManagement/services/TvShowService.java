package daif.aymane.showsManagement.services;

import daif.aymane.showsManagement.dto.TVShows.TVShowDto;
import daif.aymane.showsManagement.models.*;
import daif.aymane.showsManagement.repositories.AppUserRepository;
import daif.aymane.showsManagement.repositories.EpisodeRepository;
import daif.aymane.showsManagement.repositories.TvShowRepository;
import daif.aymane.showsManagement.repositories.UpComingEpisodeRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TvShowService {
    private final TvShowRepository tvShowRepository;
    private final AppUserRepository appUserRepository;
    private final EpisodeRepository episodeRepository;
    private final UpComingEpisodeRepository upComingEpisodeRepository;

    public TvShowService(TvShowRepository tvShowRepository, AppUserRepository appUserRepository, EpisodeRepository episodeRepository, UpComingEpisodeRepository upComingEpisodeRepository) {
        this.tvShowRepository = tvShowRepository;
        this.appUserRepository = appUserRepository;
        this.episodeRepository = episodeRepository;
        this.upComingEpisodeRepository = upComingEpisodeRepository;
    }

    public List<TVShow> allShows(String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
           if(username.equals(currentUserName)){
               boolean isExists = appUserRepository.existsByUsername(currentUserName);
               if(isExists){
                   AppUser appUser = appUserRepository.findByUsername(currentUserName);
                   return tvShowRepository.findAllByUser(appUser);
               }else {
                   throw new IllegalStateException("user not found");
               }
           }
        }
        throw new IllegalStateException("unauthorized");
    }

    public TVShow addShow(String username, TVShowDto tvShowDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            if(username.equals(currentUserName)){
                TVShow createdTVShow = new TVShow();
                createdTVShow.setShowState(tvShowDto.getShowState());
                createdTVShow.setCompleted(tvShowDto.isCompleted());
                createdTVShow.setName(tvShowDto.getName());

                if(tvShowDto.getShowState().equals(ShowState.ONGOING)){
                    UpComingEpisode upComingEpisode = upComingEpisodeRepository.findById(tvShowDto.getUpComingEpisodeId()).get();
                    createdTVShow.setUpComingEpisode(upComingEpisode);
                }
                Episode lastSeenEpisode =  episodeRepository.findById(tvShowDto.getLastSeenEpisodeId()).get();
                createdTVShow.setLastSeenEpisode(lastSeenEpisode);

                AppUser appUser = appUserRepository.findByUsername(currentUserName);
                createdTVShow.setUser(appUser);

                return tvShowRepository.save(createdTVShow);
            }

        }

        throw new IllegalStateException("unauthorized");
    }
}
