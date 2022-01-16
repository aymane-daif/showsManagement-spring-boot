package daif.aymane.showsManagement.services;

import daif.aymane.showsManagement.dto.UpComingEpisodes.UpComingEpisodeDto;
import daif.aymane.showsManagement.models.Episode;
import daif.aymane.showsManagement.models.UpComingEpisode;
import daif.aymane.showsManagement.repositories.EpisodeRepository;
import daif.aymane.showsManagement.repositories.UpComingEpisodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpComingEpisodeService {
    private final UpComingEpisodeRepository upComingEpisodeRepository;
    private final EpisodeService episodeService;

    public UpComingEpisodeService(UpComingEpisodeRepository upComingEpisodeRepository, EpisodeService episodeService) {
        this.upComingEpisodeRepository = upComingEpisodeRepository;
        this.episodeService = episodeService;
    }

    public List<UpComingEpisode> allUpComingEpisodes(){
        return upComingEpisodeRepository.findAll();
    }

    public UpComingEpisode createUpComingEpisode(UpComingEpisodeDto upComingEpisodeDto){
        UpComingEpisode upComingEpisode = new UpComingEpisode();
        upComingEpisode.setReleaseDate(upComingEpisodeDto.getReleaseDate());
        upComingEpisode.setEpisode(episodeService.createEpisode(new Episode(null,upComingEpisodeDto.getUpComingEpisode(),upComingEpisodeDto.getUpComingSeason())));

        return upComingEpisodeRepository.save(upComingEpisode);
    }
}
