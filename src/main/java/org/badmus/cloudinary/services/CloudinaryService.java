package org.badmus.cloudinary.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudinaryService {

    Map<String, String> asyncUploadCourse(String folderName, MultipartFile file);

    void deleteFolder(String folderName);

    void deleteSingleAsset(String public_id);

}