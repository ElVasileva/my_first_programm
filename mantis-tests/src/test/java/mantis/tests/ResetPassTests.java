package mantis.tests;

import mantis.model.MailMessage;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

public class ResetPassTests extends TestBase{

    @Ignore
    @Test
    public void testRegistration() throws IOException, MessagingException, InterruptedException {

        app.registration().login("administrator", "root");
        String password = "passwordNew";
        app.getDriver().get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        app.registration().selectUser();
        app.registration().resetPassword();




        long now = System.currentTimeMillis();
        String user = format("user%s", now);
//        String password = "password";
        String email = format("user%s@localhost@localdomain", now);
//        app.james().createUser(user, password);
//        app.registration().start(user, email);
//    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
//        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
//        String confirmationLink = findConfirmationLink(mailMessages, email);
//        app.registration().finish(confirmationLink, "password");
//        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
