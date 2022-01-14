package daif.aymane.showsManagement.controllers;

import daif.aymane.showsManagement.dto.UpComingEpisodes.UpComingEpisodeDto;
import daif.aymane.showsManagement.models.UpComingEpisode;
import daif.aymane.showsManagement.services.UpComingEpisodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/v1/users/{username}/episodes/upcoming")
@RestController
public class UpComingEpisodeController {
    private final UpComingEpisodeService upComingEpisodeService;

    public UpComingEpisodeController(UpComingEpisodeService upComingEpisodeService) {
        this.upComingEpisodeService = upComingEpisodeService;
    }

    @GetMapping
    public List<UpComingEpisode> getAllUpComingEpisodes() {
        return upComingEpisodeService.allUpComingEpisodes();
    }

    @PostMapping
    public UpComingEpisode createUpComingEpisode(@RequestBody UpComingEpisodeDto upComingEpisodeDto) {
        return upComingEpisodeService.createUpComingEpisode(upComingEpisodeDto);
    }
}
