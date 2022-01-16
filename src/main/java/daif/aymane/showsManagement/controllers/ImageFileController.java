package daif.aymane.showsManagement.controllers;

import daif.aymane.showsManagement.models.ImageFile;
import daif.aymane.showsManagement.services.ImageFileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RequestMapping(path = "api/v1/users/{username}/shows/images")
@RestController
public class ImageFileController {
    private final ImageFileService imageFileService;

    public ImageFileController(ImageFileService imageFileService) {
        this.imageFileService = imageFileService;
    }

    @PostMapping
    public ImageFile uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            ImageFile imageFile = imageFileService.save(file);
		//ResponseEntity.status(HttpStatus.OK)
                  //  .body(String.format(imageFile.getId()))
            return imageFile;
        } catch (Exception e) {
           // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
             //       .body(String.format("Could not upload the file"));
		return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        if (!imageFileService.getFile(id).isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        ImageFile imageFile = imageFileService.getFile(id).get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageFile.getName() + "\"")
                .contentType(MediaType.valueOf(imageFile.getContentType()))
                .body(imageFile.getData());
    }
}
