package aust.iums.pg.admission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Md. Minhaz Ur Rahman on 9/20/2020.
 */

@Service
public class UploadPathService {

    @Value("${pgAdmission.file.image.photo-destination}")
    String apPhotoBasePath;

    @Value("${pgAdmission.file.image.signature-destination}")
    String apSignatureBasePath;

    @Value("${pgAdmission.file.image.file-destination}")
    String apFileBasePath;

    @Autowired
    ServletContext context;

    public Path getFilePath(String modifiedFileName, String type) throws IOException {

        String basePath = "";
        String semester = "spring-2020";
        String faculty ="masters";
        String application_sn="150204071";
        if (type == "photo") {
            basePath = apPhotoBasePath;
        } else if (type == "signature") {
            basePath = apSignatureBasePath;
        } else if (type == "document") {
            basePath = apFileBasePath;
        }

        Path directory = Paths.get(basePath+"/"+semester);

        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }
        directory = Paths.get(basePath+"/"+semester+"/"+faculty);
        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }

        directory = Paths.get(basePath+"/"+semester+"/"+faculty+"/"+application_sn);
        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }

        Path path = Paths.get(basePath+"/"+semester+"/"+faculty+"/"+application_sn+"/"+modifiedFileName);

        return path;
    }
}

