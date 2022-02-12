package daif.aymane.showsManagement.services;

import daif.aymane.showsManagement.dto.TVShows.TVShowDto;
import daif.aymane.showsManagement.dto.UpComingEpisodes.UpComingEpisodeDto;
import daif.aymane.showsManagement.models.*;
import daif.aymane.showsManagement.repositories.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Transactional
@Service
public class TvShowService {
    private final TvShowRepository tvShowRepository;
    private final AppUserRepository appUserRepository;
    private final EpisodeService episodeService;
    private final UpComingEpisodeService upComingEpisodeService;
    private final ImageFileRepository imageFileRepository;

    public TvShowService(TvShowRepository tvShowRepository, AppUserRepository appUserRepository, EpisodeService episodeService, UpComingEpisodeService upComingEpisodeService, ImageFileRepository imageFileRepository) {
        this.tvShowRepository = tvShowRepository;
        this.appUserRepository = appUserRepository;
        this.episodeService = episodeService;
        this.upComingEpisodeService = upComingEpisodeService;
        this.imageFileRepository = imageFileRepository;
    }

    public TVShow singleShow(String username, Long showId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
           if(username.equals(currentUserName)){
               boolean isExists = appUserRepository.existsByUsername(currentUserName);
               if(isExists){
			//get show of specific id AND user
                   //AppUser appUser = appUserRepository.findByUsername(currentUserName);
                   return tvShowRepository.findByShowId(showId);
               }else {
                   throw new IllegalStateException("user not found");
               }
           }
        }
        throw new IllegalStateException("unauthorized");
    }

    public void deleteShow(String username, Long showId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
           if(username.equals(currentUserName)){
               boolean isExists = appUserRepository.existsByUsername(currentUserName);
               if(isExists){
                    tvShowRepository.delete(tvShowRepository.findByShowId(showId));
                    return;
               }else {
                   throw new IllegalStateException("user not found");
               }
           }
        }
        throw new IllegalStateException("unauthorized");
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

    public TVShow addShow(String username, TVShowDto tvShowDto) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            if(username.equals(currentUserName)){
                TVShow createdTVShow = new TVShow();
                createdTVShow.setShowState(tvShowDto.getShowState());
                createdTVShow.setCompleted(tvShowDto.isCompleted());
                createdTVShow.setName(tvShowDto.getName());

                if(tvShowDto.getShowState().equals(ShowState.ONGOING)){
                    UpComingEpisodeDto upComingEpisodeDto = new UpComingEpisodeDto();
                    upComingEpisodeDto.setUpComingEpisode(tvShowDto.getUpComingEpisode());
                    upComingEpisodeDto.setUpComingSeason(tvShowDto.getUpComingSeason());
                    upComingEpisodeDto.setReleaseDate(tvShowDto.getReleaseDate());
                    createdTVShow.setUpComingEpisode(upComingEpisodeService.createUpComingEpisode(
                            upComingEpisodeDto
                    ));

                }

                createdTVShow.setLastSeenEpisode(episodeService.createEpisode(
                        new Episode(null,tvShowDto.getLastSeenEpisode(),tvShowDto.getLastSeenSeason())
                ));

                AppUser appUser = appUserRepository.findByUsername(currentUserName);
                createdTVShow.setUser(appUser);

                createdTVShow.setPostImage(
                        imageFileRepository.findById(tvShowDto.getPosterId()).get()
                );

		createdTVShow.setShowEmoji(tvShowDto.getShowEmoji());

                return tvShowRepository.save(createdTVShow);
            }

        }

        throw new IllegalStateException("unauthorized");
    }

    public TVShow updateShow(String username, TVShowDto tvShowDto) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            if(username.equals(currentUserName)){
                TVShow createdTVShow = new TVShow();
                if(tvShowDto.getShowState() != null){
                    createdTVShow.setShowState(tvShowDto.getShowState());
                }
                if(tvShowDto.isCompleted()) {
                    createdTVShow.setCompleted(tvShowDto.isCompleted());
                }
                if(tvShowDto.getName() != null){
                    createdTVShow.setName(tvShowDto.getName());
                }
                if(tvShowDto.getShowState() != null){
                    if(tvShowDto.getShowState().equals(ShowState.ONGOING)){
                        UpComingEpisodeDto upComingEpisodeDto = new UpComingEpisodeDto();
                        upComingEpisodeDto.setUpComingEpisode(tvShowDto.getUpComingEpisode());
                        upComingEpisodeDto.setUpComingSeason(tvShowDto.getUpComingSeason());
                        upComingEpisodeDto.setReleaseDate(tvShowDto.getReleaseDate());
                        createdTVShow.setUpComingEpisode(upComingEpisodeService.createUpComingEpisode(
                                upComingEpisodeDto
                        ));
                    }
                }
                if(tvShowDto.getLastSeenEpisode() != null && tvShowDto.getLastSeenSeason() != null){
                    createdTVShow.setLastSeenEpisode(episodeService.createEpisode(
                            new Episode(null,tvShowDto.getLastSeenEpisode(),tvShowDto.getLastSeenSeason())
                    ));
                }
                AppUser appUser = appUserRepository.findByUsername(currentUserName);
                createdTVShow.setUser(appUser);

                if(tvShowDto.getPosterId() != null){
                    createdTVShow.setPostImage(
                            imageFileRepository.findById(tvShowDto.getPosterId()).get()
                    );
                }

                if(tvShowDto.getShowEmoji() != null){
                    createdTVShow.setShowEmoji(tvShowDto.getShowEmoji());
                }

                return tvShowRepository.save(createdTVShow);
            }

        }

        throw new IllegalStateException("unauthorized");
    }
}
