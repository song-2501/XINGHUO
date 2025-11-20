import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*; // JAF的核心类，处理附件

public class AttachmentMailSender {

    public static void main(String[] args) {
        // ------------------------------
        // 1. 配置SMTP参数（根据邮箱类型调整）
        // ------------------------------
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.qq.com"); // QQ邮箱SMTP服务器
        props.put("mail.smtp.port", "465"); // SSL加密端口（必须开启）
        props.put("mail.smtp.auth", "true"); // 需要账号密码认证
        props.put("mail.smtp.ssl.enable", "true"); // 开启SSL（QQ邮箱强制要求）
        props.put("mail.debug", "true"); // 开启调试（可选，打印发送日志）

        // ------------------------------
        // 2. 创建认证器（输入发件人邮箱和授权码）
        // ------------------------------
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String senderEmail = "467621965@qq.com"; // 发件人QQ邮箱
                String authCode = "czfkjiglxpracbai";
                return new PasswordAuthentication(senderEmail, authCode);
            }
        };

        // ------------------------------
        // 3. 创建邮件会话（Session）
        // ------------------------------
        Session session = Session.getInstance(props, authenticator);

        try {
            // ------------------------------
            // 4. 构建带附件的邮件内容（MimeMessage）
            // ------------------------------
            MimeMessage message = new MimeMessage(session);

            // 4.1 设置发件人（必须与认证器中的邮箱一致）
            message.setFrom(new InternetAddress("467621965@qq.com"));

            // 4.2 设置收件人（TO=直接发送，CC=抄送，BCC=密送）
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("1271269222@qq.com")); // 收件人邮箱

            // 4.3 设置邮件主题（UTF-8编码避免乱码）
            message.setSubject("睡着");

            // 创建MimeMultipart（类型为"mixed"，表示包含文本和附件）
            MimeMultipart multipart = new MimeMultipart("mixed");

            // ① 添加文本内容部分
            MimeBodyPart textPart = new MimeBodyPart();
            String textContent = "今天是小雷同学第一次开播呢~~~";
            textPart.setText(textContent, "UTF-8"); // 文本内容+编码
            multipart.addBodyPart(textPart); // 将文本部分加入多部分

            // ② 添加附件部分（关键！）
            MimeBodyPart attachmentPart = new MimeBodyPart();
            String attachmentPath = "D:\\test.pdf"; // 附件的绝对路径（替换成你的文件路径）

            // 使用FileDataSource读取附件文件
            DataSource dataSource = new FileDataSource(attachmentPath);
            // 将DataSource关联到DataHandler（JAF的核心类，处理数据传输）
            attachmentPart.setDataHandler(new DataHandler(dataSource));
            // 设置附件文件名（用MimeUtility编码，避免中文乱码）
            String fileName = "test.pdf"; // 收件人看到的附件名（可自定义）
            attachmentPart.setFileName(MimeUtility.encodeText(fileName, "UTF-8", "B"));

            multipart.addBodyPart(attachmentPart); // 将附件部分加入多部分

            // ------------------------------
            // 4.5 将多部分内容设置到邮件中
            // ------------------------------
            message.setContent(multipart);

            // ------------------------------
            // 5. 发送邮件
            // ------------------------------
            Transport.send(message);
            System.out.println("邮件发送成功！");

        } catch (Exception e) { // 捕获所有异常（MessagingException、UnsupportedEncodingException等）
            e.printStackTrace();
            System.out.println("邮件发送失败：" + e.getMessage());
        }
    }
}

