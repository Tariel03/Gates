package com.example.gates.Photos;

import com.example.gates.Exceptions.MainException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Data
public class PhotoConfig {
//    @Value(value = "${home_dir}")
//    private String uploadDir = "/home/iskender/Tariel/selim_team3";
    private String uploadDir = "/home/team3/photos";
    

    private Path path = Paths.get(uploadDir);

    public void savePhoto(MultipartFile file){
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ignored) {

            }
        }

        try {
            byte[] bytes = file.getBytes();
            Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
            Files.write(filePath, bytes);
        } catch (IOException e) {
            throw new MainException("Can not upload a photo!");
        }

    }


}
