package com.example.gates.Controllers;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class ImageController {
    @GetMapping("/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable String name) throws IOException {
        File imageFile = new File("/home/team3/photos/"+name);
//        File imageFile = new File(imageName);
        if (!imageFile.exists()) {
            return ResponseEntity.notFound().build();
        }
        byte[] imageBytes = FileUtils.readFileToByteArray(imageFile);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
