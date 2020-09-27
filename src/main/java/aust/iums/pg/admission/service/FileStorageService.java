package aust.iums.pg.admission.service;

import aust.iums.pg.admission.dto.ApplicationForm;
import aust.iums.pg.admission.enums.FileTypeEnum;
import aust.iums.pg.admission.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;
/**
 * Created by Md. Minhaz Ur Rahman on 9/20/2020.
 */


@Service
public class FileStorageService {

    @Value("${pgAdmission.file.image.max-image-size}")
    double maxSize;

    @Value("${pgAdmission.file.image.photo-height}")
    int photoHeight;

    @Value("${pgAdmission.file.image.signature-height}")
    int signatureHeight;

    @Value("${pgAdmission.file.image.width}")
    int width;


    @Value("${pgAdmission.file.max-document-size}")
    double maxDocumentSize;


    @Autowired
    UploadPathService uploadPathService;
    @Autowired
    ApplicantRepository mApplicantRepository;

    public void saveFile(MultipartFile file, String type, ApplicationForm form, String docType) throws IOException {
        String fileName = file.getOriginalFilename();
        String applicantSerialNo = form.getApplicationSerialNumber();

        String modifiedFileName="";
        if(type=="photo" || type=="signature")
             modifiedFileName =  applicantSerialNo + "." + FilenameUtils.getExtension(fileName);
        else if(type=="document")
             modifiedFileName =  docType + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);

        Path path = uploadPathService.getFilePath(modifiedFileName, type, form);
        Files.write(path, file.getBytes());
        /*fileStorage.setFileName(modifiedFileName);
        fileStorage.setFileType("photo");*/


    }

    public void validate(MultipartFile file, FileTypeEnum type) throws IOException {
        if (type == FileTypeEnum.PHOTO) {
            if (!(file.getOriginalFilename().endsWith(".jpg") || file.getOriginalFilename().endsWith(".jpeg")))
                throw new RuntimeException("Invalid applicant  photo format type( accepted: jpeg, jpg)");
            if (!validateDimension(true, file)) {
                throw new RuntimeException("Invalid applicant  photo dimension. Photo must have a dimensions of " + width + " px x " + photoHeight + " px (width x height)");
            }
            if (file.getSize() / 1000 > maxSize) {
                throw new RuntimeException("Invalid applicant photo size. Maximum allowed photo size is " + maxSize + " KB");
            }

        } else if (type == FileTypeEnum.SIGNATURE) {
            if (!(file.getOriginalFilename().endsWith(".jpg") || file.getOriginalFilename().endsWith(".jpeg")))
                throw new RuntimeException("Invalid applicant signature format type( accepted: jpeg, jpg)");
            if (!validateDimension(false, file)) {
                throw new RuntimeException("Invalid applicant signature dimension. Signature must have a dimensions of " + width + " px x " + photoHeight + " px (width x height)");
            }
            if ((file.getSize() / 1000) > maxSize) {
                throw new RuntimeException("Invalid applicant signature size. Maximum allowed signature size is " + maxSize + " KB");
            }
        } else if (type == FileTypeEnum.BSC || type==FileTypeEnum.HSC || type==FileTypeEnum.SSC ||type== FileTypeEnum.BSC || type==FileTypeEnum.MSC || type==FileTypeEnum.EXPERIENCE) {
            if (!(file.getOriginalFilename().endsWith(".jpg") || file.getOriginalFilename().endsWith(".png") || file.getOriginalFilename().endsWith(".jpeg") || file.getOriginalFilename().endsWith(".pdf") || file.getOriginalFilename().endsWith(".zip")))
                throw new RuntimeException("Invalid applicant file type( accepted: jpeg, jpg, png, pdf, zip)");
            if ((file.getSize() / 1000) > maxDocumentSize) {
                throw new RuntimeException("Invalid applicant file size. Maximum allowed file size is " + maxDocumentSize + " KB");
            }
        }

    }

    private Boolean validateDimension(Boolean isPhoto, MultipartFile file) throws IOException {
        BufferedImage buffered = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
        Integer pWidth, pHeight;
        int checkHeight;
        Boolean response;
        pWidth = buffered.getWidth();
        pHeight = buffered.getHeight();
        if (isPhoto) {
            checkHeight = photoHeight;
        } else {
            checkHeight = signatureHeight;
        }

        if (pHeight.intValue() == checkHeight && pWidth.intValue() == width) {
            response = true;
        } else {
            response = false;
        }
        return response;
    }
}
