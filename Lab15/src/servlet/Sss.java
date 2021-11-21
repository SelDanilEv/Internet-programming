package servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import org.jsoup.Jsoup;
import java.util.Properties;

public class Sss extends HttpServlet {
    private String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart)  throws MessagingException, IOException{
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final String to = request.getParameter("mail");
        final String mes = request.getParameter("message");
        final String from = getServletContext().getInitParameter("from");
        final String username = getServletContext().getInitParameter("username");
        final String password = getServletContext().getInitParameter("password");
        final String host = getServletContext().getInitParameter("host");
        final String port = getServletContext().getInitParameter("port");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            message.setSubject("Subject");

            message.setText(mes);

            Transport.send(message);

            response.getWriter().write("Sent message successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String hostImaps = getServletContext().getInitParameter("hostImaps");
            int portImaps = Integer.parseInt(getServletContext().getInitParameter("portImaps"));
            String hostPop = getServletContext().getInitParameter("hostPop");
            int portPop = Integer.parseInt(getServletContext().getInitParameter("portPop"));
            String user = getServletContext().getInitParameter("username");
            String password = getServletContext().getInitParameter("password");

            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol", "pop3s");
            properties.setProperty("mail.pop3s.host", hostPop);
            properties.setProperty("mail.pop3s.port", String.valueOf(portPop));
            properties.setProperty("mail.pop3s.auth", "true");
            properties.setProperty("mail.pop3s.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.pop3s.ssl.trust", "*");
            Session session = Session.getDefaultInstance(properties);

//            Store store = session.getStore("imaps");
//            store.connect(hostImaps, portImaps, user, password);

            Store store = session.getStore("pop3s");
            store.connect(hostPop, portPop, user, password);

            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();

            StringBuilder builder = new StringBuilder();

            if (messages.length == 0) {
                System.out.println("No messages found.");
                builder.append("No messages found.");
            }

            for (int i = messages.length - 1; i > 0; i--) {
                builder.append("From : ").append(messages[i].getFrom()[0]).append("<br>");
                builder.append("Subject : ").append(messages[i].getSubject()).append("<br>");
                builder.append("Message : ").append(getTextFromMessage(messages[i])).append("<br>");
                builder.append("Sent Date : ").append(messages[i].getSentDate()).append("<br>");
                response.setContentType("text/html");
            }

            inbox.close(true);
            store.close();

            response.getWriter().write(builder.toString());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
