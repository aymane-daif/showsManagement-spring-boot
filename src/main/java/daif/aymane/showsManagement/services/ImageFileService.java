package daif.aymane.showsManagement.services;

import daif.aymane.showsManagement.models.ImageFile;
import daif.aymane.showsManagement.repositories.ImageFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageFileService {
    private final ImageFileRepository imageFileRepository;

    public ImageFileService(ImageFileRepository imageFileRepository) {
        this.imageFileRepository = imageFileRepository;
    }

    public void save(MultipartFile file) throws IOException {
        ImageFile imageFile = new ImageFile();
        imageFile.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        imageFile.setContentType(file.getContentType());
        imageFile.setData(file.getBytes());
        imageFile.setSize(file.getSize());

        imageFileRepository.save(imageFile);
    }

    public Optional<ImageFile> getFile(String id) {
        return imageFileRepository.findById(id);
    }

}
