package aust.iums.pg.admission.service;

import aust.iums.pg.admission.dto.ApplicationForm;
import aust.iums.pg.admission.enums.FileTypeEnum;
import aust.iums.pg.admission.helper.AdmissionHelper;
import aust.iums.pg.admission.model.Semester;
import aust.iums.pg.admission.repository.ApplicantRepository;
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

    @Autowired
    AdmissionHelper mHelper;

    @Autowired
    ApplicantRepository mApplicantRepository;


    public Path getFilePath(String modifiedFileName, FileTypeEnum type, ApplicationForm form) throws IOException {
        Semester semes;
        String basePath = "";
        semes = mHelper.getActiveSemester();

        String semester = semes.getSemesterName().replaceAll(" ", "-").toLowerCase();
        String program = form.getProgramName();
        String application_sn = form.getApplicationSerialNumber();
        if (type == FileTypeEnum.PHOTO) {
            basePath = apPhotoBasePath;
        } else if (type == FileTypeEnum.SIGNATURE) {
            basePath = apSignatureBasePath;
        } else if (type == FileTypeEnum.SSC || type == FileTypeEnum.HSC || type == FileTypeEnum.BSC || type == FileTypeEnum.MSC || type == FileTypeEnum.EXPERIENCE) {
            basePath = apFileBasePath;
        }

        Path directory = Paths.get(basePath + "/" + semester);

        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }
        directory = Paths.get(basePath + "/" + semester + "/" + program);
        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }
        if (type == FileTypeEnum.SSC || type == FileTypeEnum.HSC || type == FileTypeEnum.BSC || type == FileTypeEnum.MSC) {
            directory = Paths.get(basePath + "/" + semester + "/" + program + "/" + "EDUCATION");
            if (!Files.exists(directory)) {
                Files.createDirectory(directory);
            }
            directory = Paths.get(basePath + "/" + semester + "/" + program +  "/" + "EDUCATION" + "/" + application_sn);
            if (!Files.exists(directory)) {
                Files.createDirectory(directory);
            }
            Path path = Paths.get(basePath + "/" + semester + "/" + program +  "/" + "EDUCATION" + "/" + application_sn + "/" + modifiedFileName);

            return path;
        }
        if(type == FileTypeEnum.EXPERIENCE){
            directory = Paths.get(basePath + "/" + semester + "/" + program + "/" + FileTypeEnum.EXPERIENCE);
            if (!Files.exists(directory)) {
                Files.createDirectory(directory);
            }
            directory = Paths.get(basePath + "/" + semester + "/" + program + "/" + FileTypeEnum.EXPERIENCE + "/" + application_sn );
            if (!Files.exists(directory)) {
                Files.createDirectory(directory);
            }
            Path path = Paths.get(basePath + "/" + semester + "/" + program + "/" + FileTypeEnum.EXPERIENCE + "/" + application_sn  + "/" + modifiedFileName);

            return path;
        }
        else{
            Path path = Paths.get(basePath + "/" + semester + "/" + program + "/" + modifiedFileName);

            return path;
        }
    }
}

