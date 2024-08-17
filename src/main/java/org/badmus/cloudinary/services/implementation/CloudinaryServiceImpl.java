package org.badmus.cloudinary.services.implementation;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.badmus.cloudinary.services.CloudinaryService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.badmus.cloudinary.utils.GeneralUtils.generateString;


@Slf4j
@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final static int CHUNK_SIZE = 100 * 1024 * 1024;

    private final Cloudinary cloudinary;

    @Override
    @Async(value = "uploadToCloudinaryExecutorThread")
    public Map<String, String> asyncUploadCourse(String folderName, MultipartFile file) {
        Map<Object, Object> mapObjectObject = new HashMap<>();
        Map<String, String> mapStringString = new HashMap<>();

        try {
            String public_id = generateString();
            mapObjectObject.put("folder", folderName);
            mapObjectObject.put("public_id", public_id);
            mapObjectObject.put("resource_type", "auto");
            mapObjectObject.put("chunk_size", CHUNK_SIZE);

            Map map = cloudinary.uploader().uploadLarge(file.getBytes(), mapObjectObject);

            mapStringString.put("fileId", public_id);
            mapStringString.put("url", map.get("url").toString());
            mapStringString.put("public_id", map.get("public_id").toString());
            mapStringString.put("secure_url", map.get("secure_url").toString());

            log.info("uploaded successfully");
        } catch (IOException e) {
            //  send failure message
            log.error(e.getMessage());
            throw new RuntimeException("could not upload file");
        }

        return mapStringString;
    }

    @Override
    public void deleteFolder(String folderName) {
        try {
            cloudinary.api().deleteFolder(folderName, new HashMap<>());
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }

    @Override
    public void deleteSingleAsset(String public_id) {
        try {
            cloudinary.uploader().destroy(public_id, new HashMap<>());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}