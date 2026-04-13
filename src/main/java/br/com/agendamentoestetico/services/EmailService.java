package br.com.agendamentoestetico.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${app.frontend.url}")
    private String baseUrl;

    // O Spring injeta o valor do properties direto no construtor
    public EmailService(@Value("${app.frontend.url}") String baseUrl, JavaMailSender mailSender,
            @Value("${spring.mail.username}") String from) {
        this.baseUrl = baseUrl;
        this.mailSender = mailSender;
        this.from = from;
    }

    public void enviarEmailSimples(String destinatario, String assunto, String corpo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(destinatario);
        message.setSubject(assunto);
        message.setText(corpo);
        mailSender.send(message);
    }

    public void enviarEmailHtml(String destinatario, String assunto, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(from);
        helper.setTo(destinatario);
        helper.setSubject(assunto);
        helper.setText(htmlBody, true); // O 'true' indica que o conteúdo é HTML
        mailSender.send(message);
    }

    public void enviarEmailRecuperacaoSenha(String nome, String destinatario, String token) {

        String url = baseUrl + "/auth/recuperar-senha?token=" + token;
        String htmlEmail = """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }
                        .container { max-width: 600px; margin: 20px auto; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }
                        .header { background-color: #2c3e50; color: #ffffff; padding: 30px; text-align: center; }
                        .content { padding: 30px; line-height: 1.6; color: #333333; }
                        .button-container { text-align: center; margin: 30px 0; }
                        .button { background-color: #3498db; color: #ffffff; padding: 15px 25px; text-decoration: none; border-radius: 5px; font-weight: bold; }
                        .footer { background-color: #f9f9f9; color: #777777; padding: 20px; text-align: center; font-size: 12px; }
                        .warning { font-size: 13px; color: #999; margin-top: 20px; border-top: 1px solid #eee; padding-top: 20px; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1 style="margin:0;">Recuperação de Senha</h1>
                        </div>
                        <div class="content">
                            <p>Olá, <strong>%s</strong>,</p>
                            <p>Recebemos uma solicitação para redefinir a sua senha do sistema. Se você deseja prosseguir, clique no botão abaixo:</p>

                            <div class="button-container">
                                <a href="%s" class="button">Redefinir minha senha</a>
                            </div>

                            <p>Este link é válido por apenas alguns minutos por motivos de segurança.</p>

                            <div class="warning">
                                <p><strong>Não solicitou esta alteração?</strong><br>
                                Se você não solicitou a recuperação de senha, pode ignorar este e-mail com segurança. Nenhuma alteração foi feita na sua conta e o token de acesso irá expirar automaticamente.</p>
                            </div>
                        </div>
                        <div class="footer">
                            <p>&copy; 2026 Gian Arantes. Todos os direitos reservados.</p>
                        </div>
                    </div>
                </body>
                </html>
                """
                .formatted(nome, url);

        try {
            enviarEmailHtml(destinatario, "Recuperação de Senha", htmlEmail);
            System.out.println("Email enviado com sucesso!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}