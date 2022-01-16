package daif.aymane.showsManagement.services;

import daif.aymane.showsManagement.models.Episode;
import daif.aymane.showsManagement.repositories.EpisodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    public List<Episode> allEpisodes(){
        return episodeRepository.findAll();
    }

    public Episode createEpisode(Episode episode){
        if(episodeRepository.existsByEpisodeNumberAndSeasonNumber(episode.getEpisodeNumber(),episode.getSeasonNumber())
        ){
            return episodeRepository.findByEpisodeNumberAndSeasonNumber(episode.getEpisodeNumber(), episode.getSeasonNumber());
        }
        return episodeRepository.save(episode);
    }
}
