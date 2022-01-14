package daif.aymane.showsManagement.controllers;

import daif.aymane.showsManagement.models.Episode;
import daif.aymane.showsManagement.services.EpisodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/v1/users/{username}/episodes")
@RestController
public class EpisodeController {
    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping
    public List<Episode> getEpisodes(){
        return episodeService.allEpisodes();
    }

    @PostMapping
    public Episode createEpisode(@RequestBody Episode episode){
        return episodeService.createEpisode(episode);
    }
}
