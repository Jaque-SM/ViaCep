package projeto.web.viacep;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ViaCepApplication {
  		
	public static void main(String[] args) {
		SpringApplication.run(ViaCepApplication.class, args);
		
		String username="meuemail@outlook.com";
		String senha="minhasenha";
		
        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Hotmail */
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
    
        

        Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                         protected PasswordAuthentication getPasswordAuthentication()
                         {
                               return new PasswordAuthentication(username, senha);
                         }
                    });

        	/** Ativa Debug para sessão */
        		session.setDebug(true);
        

        try {

              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress(username)); //Remetente

              message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse("emaildestino@hotmail.com")); //Destinatário(s)
              
              message.setSubject("TESTE – CEP -\n"
              		+ "Seleção DEV Java - <Jaqueline S. Moreno>.");//Assunto
              
              
              MimeBodyPart mbp1 = new MimeBodyPart();
              mbp1.setText("Anexo dos dados salvos no bd MySQL");
              
              MimeBodyPart mbp2 = new MimeBodyPart();
              FileDataSource anexo1 = new FileDataSource("C:\\Users\\JAQUE\\Desktop\\Projects\\"
              		+ "ViaCep\\src\\main\\java\\projeto\\web\\viacep\\serviceImpl\\dados.json");
              mbp2.setDataHandler(new DataHandler(anexo1));
              mbp2.setFileName(anexo1.getName());
              
              MimeBodyPart mbp3 = new MimeBodyPart();
              mbp3.setText("Anexo do Script do MySQL");
              
              MimeBodyPart mbp4 = new MimeBodyPart();
              FileDataSource anexo2 = new FileDataSource("C:\\Users\\JAQUE\\Desktop\\"
              		+ "Projects\\ViaCep\\src\\main\\java\\projeto\\web\\viacep\\script_bd.txt");
              mbp4.setDataHandler(new DataHandler(anexo2));
              mbp4.setFileName(anexo2.getName());
              
              MimeBodyPart mbp5 = new MimeBodyPart();
              mbp5.setText("\nLink do Github: \n"
              		+ "https://github.com/Jaque-SM/ViaCep");
              
              Multipart mp = new MimeMultipart();

              //mensagem
              mp.addBodyPart(mbp1);
                     
              //anexo dos arquivos salvos no bd
              mp.addBodyPart(mbp2);
              
              //mensagem
              mp.addBodyPart(mbp3);
              
              //anexo do scrip do bd
              mp.addBodyPart(mbp4);
              
              mp.addBodyPart(mbp5);
            
              // adiciona a Multipart na mensagem
              message.setContent(mp);
              
              /**Método para enviar a mensagem criada*/
              Transport.send(message, username, senha);

              System.out.println("Feito!!!");

         } catch (MessagingException e) {
              throw new RuntimeException(e);
        }
        
        
		
	
	}
		
	

}
