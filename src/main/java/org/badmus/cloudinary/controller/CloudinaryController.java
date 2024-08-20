package org.badmus.cloudinary.controller;

import lombok.RequiredArgsConstructor;
import org.badmus.cloudinary.files.service.impl.FileParserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/cloudinary")
@RequiredArgsConstructor
public class CloudinaryController {
    
    private final FileParserService fileParserService;
    
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> uploadToCloudinary(@ModelAttribute MultipartFile multipartFile){
        String fileExtension = getFileExtension(multipartFile);
        fileParserService.parse(multipartFile, fileExtension);
        return ResponseEntity.ok("upload successful");
    }


    public static String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        }
        return "";
    }
    
}