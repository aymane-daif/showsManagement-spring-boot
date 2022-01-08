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
    private final EpisodeRepository episodeRepository;

    public UpComingEpisodeService(UpComingEpisodeRepository upComingEpisodeRepository, EpisodeRepository episodeRepository) {
        this.upComingEpisodeRepository = upComingEpisodeRepository;
        this.episodeRepository = episodeRepository;
    }

    public List<UpComingEpisode> allUpComingEpisodes(){
        return upComingEpisodeRepository.findAll();
    }

    public UpComingEpisode createUpComingEpisode(UpComingEpisodeDto upComingEpisodeDto){
        UpComingEpisode upComingEpisode = new UpComingEpisode();
        upComingEpisode.setReleaseDate(upComingEpisodeDto.getReleaseDate());

        Episode episode = episodeRepository.findById(upComingEpisodeDto.getEpisodeId()).get();
        upComingEpisode.setEpisode(episode);
        return upComingEpisodeRepository.save(upComingEpisode);
    }
}
