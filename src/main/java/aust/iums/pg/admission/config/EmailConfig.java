package aust.iums.pg.admission.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by Monjur-E-Morshed on 9/30/2020.
 */
@Configuration
public class EmailConfig {

  @Bean
  JavaMailSender mailSender(){
    JavaMailSenderImpl javaMailSender= new JavaMailSenderImpl();
   /* javaMailSender.setProtocol("SMTP");
    javaMailSender.setHost("127.0.0.1");
    javaMailSender.setPort(25);*/

    /*mail:
    host: smtp.gmail.com
    port: 587
    username: contact.nbms@gmail.com
    password: hhmardlmfmsbjtti
    protocol: smtp
    tls: true
    properties.mail.smtp:
    auth: true
    starttls.enable: true*/
    return javaMailSender;
  }
}
