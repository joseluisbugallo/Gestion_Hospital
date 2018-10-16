package business;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CorreoElectronico {
	//Correos de medicos
	//medicoips2018@gmail.com  // ingenieriasoftware
	//medico2ips2018@gmail.com
	
	String destinatario;
	String asunto;
	String cuerpo;
	
	public CorreoElectronico(String destinatario, String asunto, String cuerpo)
	{
		this.destinatario= destinatario;
		this.asunto =  asunto;
		this.cuerpo = cuerpo;
		
	}
	
	public boolean enviarCorreo()
	{
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String remitente = "gestionhospitalips";  //Para la dirección nomcuenta@gmail.com
	    String clave = "ingenieriasoftware";

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", "ingenieriasoftware");    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	        return true;
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	        return false;
	    }
	}

}
