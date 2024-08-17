package org.badmus.cloudinary.files.service.impl;

import lombok.RequiredArgsConstructor;
import org.badmus.cloudinary.files.service.FileParser;
import org.badmus.cloudinary.services.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static org.badmus.cloudinary.files.type.FileTypes.*;

@RequiredArgsConstructor
public class FileParserServiceImpl {

    private final CloudinaryService cloudinaryService;


    /**
     * CSV FILE PARSER
     */
    @Service(value = CSV)
    @RequiredArgsConstructor
    public class CSVSFileParserServiceImpl implements FileParser {
        
        @Override
        public void parse(MultipartFile multipartFile) {
            Map<String, String> objectMap = cloudinaryService.asyncUploadCourse(multipartFile.getName(), multipartFile);
        }

    }


    /**
     * XLS FILE PARSER
     */
    @Service(value = XLS)
    @RequiredArgsConstructor
    public class XLSFileParserServiceImpl implements FileParser {
        
        @Override
        public void parse(MultipartFile multipartFile) {
            Map<String, String> objectMap = cloudinaryService.asyncUploadCourse(multipartFile.getName(), multipartFile);
        }

    }

    /**
     * XLSX FILE PARSER
     */
    @Service(value = XLSX)
    @RequiredArgsConstructor
    public class XLSXFileParserServiceImpl implements FileParser {
        @Override
        public void parse(MultipartFile multipartFile) {
            Map<String, String> objectMap = cloudinaryService.asyncUploadCourse(multipartFile.getName(), multipartFile);
        }
    }
}