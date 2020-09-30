package aust.iums.pg.admission.service;

import aust.iums.pg.admission.dto.ApplicationForm;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Monjur-E-Morshed on 9/30/2020.
 */
@Service
public class PgAdmissionMailService {
  @Value("${spring.mail.username}")
  String form;

  private final Logger log = LoggerFactory.getLogger(PgAdmissionMailService.class);

  private final SpringTemplateEngine templateEngine;

  private final JavaMailSender javaMailSender;

  public PgAdmissionMailService(SpringTemplateEngine pTemplateEngine, JavaMailSender pJavaMailSender) {
    templateEngine = pTemplateEngine;
    javaMailSender = pJavaMailSender;
  }


  @Async
  public void sendEmailFromTemplate(ApplicationForm pApplicationForm, String fileName , String titleKey, ByteArrayInputStream fileContent) throws Exception {
    if (pApplicationForm.getEmail() == null) {
      log.debug("Email doesn't exist for user '{}'",pApplicationForm.getApplicationSerialNumber());
      return;
    }
    String fName=pApplicationForm.getFirstName().toUpperCase();
    String  mName=pApplicationForm.getMiddleName() ==null ? " ":pApplicationForm.getMiddleName().toUpperCase();
    String lName=pApplicationForm.getLastName()==null ? " ":pApplicationForm.getLastName().toUpperCase();
    String  fullName=fName+mName+lName;
    Context context = new Context();
    context.setVariable("studentName", fullName);
    context.setVariable("serialNo", pApplicationForm.getApplicationSerialNumber());
   // context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
    String content = templateEngine.process("mail/applicationSuccessEmail", context);
    // String subject = messageSource.getMessage(titleKey, null, null);
    sendEmail(pApplicationForm.getEmail(), content, titleKey, fileContent, fileName, true, true);
  }

  @Async
  public void sendEmail(String to,  String content, String subject, ByteArrayInputStream fileContent, String fileName, boolean isMultipart, boolean isHtml) throws Exception{
    log.debug("Send email[multipart '{}' and html '{}'] to '{}' with  and subject '{}'",
        isMultipart, isHtml, to, subject);

    // Prepare message using a Spring helper
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
      message.setTo(to);
      message.setFrom(form);
      message.setSubject(subject);
      message.setText(content, isHtml);
      message.addAttachment(fileName, new ByteArrayResource(IOUtils.toByteArray(fileContent)), "application/pdf");
//            fileContent.close();
      javaMailSender.send(mimeMessage);
      log.debug("Sent email to User '{}'", to);
    }  catch (MailException | MessagingException e) {
      log.warn("Email could not be sent to user '{}'", to, e);
    }
  }

}

