package daif.aymane.showsManagement.controllers;

import daif.aymane.showsManagement.dto.TVShows.TVShowDto;
import daif.aymane.showsManagement.models.TVShow;
import daif.aymane.showsManagement.services.TvShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/v1/users/{userId}/shows")
@RestController
public class TvShowController {
    private final TvShowService tvShowService;

    public TvShowController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @GetMapping
    public List<TVShow> getUserShows(@PathVariable Long userId){
        return tvShowService.allShows(userId);
    }

    @PostMapping
    public TVShow createUserShow(@PathVariable Long userId, @RequestBody TVShowDto tvShowDto){
        return tvShowService.addShow(userId, tvShowDto);
    }
}
