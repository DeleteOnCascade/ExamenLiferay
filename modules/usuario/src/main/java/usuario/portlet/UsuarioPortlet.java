package usuario.portlet;

import com.liferay.captcha.util.CaptchaUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.captcha.CaptchaTextException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessAction;

import org.osgi.service.component.annotations.Component;

import usuario.constants.UsuarioPortletKeys;
import usuario.servicio.model.Usuario;
import usuario.servicio.service.UsuarioLocalServiceUtil;

/**
 * @author Jaime Martin
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Usuario", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + UsuarioPortletKeys.USUARIO,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class UsuarioPortlet extends MVCPortlet {

	private Log log = LogFactoryUtil.getLog(this.getClass().getName());

	@ProcessAction(name = "addUsuario")
	public void addUsuario(ActionRequest request, ActionResponse response) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		final String name = ParamUtil.getString(request, "nombreUsuario");
		final String surname = ParamUtil.getString(request, "apellidoUsuario");
		final Date birthDay = ParamUtil.getDate(request, "fechaNacimiento", formatter);
		final String mail = ParamUtil.getString(request, "mailUsuario");

		new UsuarioLocalServiceUtil();
		Usuario usuario = UsuarioLocalServiceUtil
				.createUsuario(CounterLocalServiceUtil.increment(Usuario.class.getName()));

		usuario.setUserName(name);
		usuario.setUserSurname(surname);
		usuario.setUserBirthdate(birthDay);
		usuario.setUserMail(mail);
		usuario.setCreationDate(new java.util.Date());
		try {
			CaptchaUtil.check(request);
			log.info("CAPTCHA verification successful.");
			UsuarioLocalServiceUtil.addUsuario(usuario);
			sendEmail(mail);
		} catch (Exception exception) {
			if (exception instanceof CaptchaTextException) {
				SessionErrors.add(request, exception.getClass(), exception);
				log.error("CAPTCHA verification failed.");
			}
		}
	}

	@ProcessAction(name = "getUsuarios")
	public void getUsuarios(ActionRequest request, ActionResponse response) {
		List<Usuario> listaUsuarios = UsuarioLocalServiceUtil.getUsuarios(0,UsuarioLocalServiceUtil.getUsuariosCount());
		request.setAttribute("listaUsuarios", listaUsuarios);
		response.getRenderParameters().setValue("jspPage","/mostrarUsuarios.jsp");
	}
	
	private void sendEmail(String userEmail) {
		/// Recipient's email ID needs to be mentioned.
		String to = userEmail;

		// Sender's email ID needs to be mentioned
		String from = "testsometests@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("testsometests@gmail.com", "test$123");

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Bienvenido a Liferay!");

			// Now set the actual message
			message.setText("Tu usuario ha sido correctamente registrado :)");

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}