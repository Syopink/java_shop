package Process;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class sendMail {
    public void sendEmail(String recipient, String verificationCode){
//        final String username = "giahuynguyen610@gmail.com";  // Email gửi
//        final String password = "bcsb cfks uuyh srvn";         // Mật khẩu hoặc App password
        
        final String username = "quantri.vietproshop@gmail.com";  // Email gửi
        final String password = "tjpj rclg ithn rkby";         // Mật khẩu hoặc App password

        // Khởi tạo các thuộc tính của kết nối SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Tạo session với thông tin xác thực
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Mã xác thực: " + verificationCode);
            message.setText("Mã xác thực của bạn là: " + verificationCode);

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi gửi email: " + e.getMessage());
        }
    }
}
