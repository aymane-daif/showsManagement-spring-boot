package daif.aymane.showsManagement.controllers;

import daif.aymane.showsManagement.dto.TVShows.TVShowDto;
import daif.aymane.showsManagement.models.TVShow;
import daif.aymane.showsManagement.services.TvShowService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@CrossOrigin("*")
@RequestMapping(path = "api/v1/users/{username}/shows")
@RestController
public class TvShowController {
    private final TvShowService tvShowService;

    public TvShowController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @GetMapping
    public List<TVShow> getUserShows(@PathVariable String username){
        return tvShowService.allShows(username);
    }

    @GetMapping(path = "/{showId}")
    public TVShow getUserShow(@PathVariable String username, @PathVariable Long showId){
        return tvShowService.singleShow(username, showId);
    }

    @PostMapping
    public TVShow createUserShow(@PathVariable String username, @RequestBody TVShowDto tvShowDto) throws IOException {
        return tvShowService.addShow(username, tvShowDto);
    }
}
