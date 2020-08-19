package mantis.appmanager;

import mantis.model.MailMessage;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MailHelper {
  private final Wiser wiser;
  private ApplicationManager app;

  public MailHelper(ApplicationManager app) {
    this.app = app;
    this.wiser = new Wiser();
  }

  public List<MailMessage> waitForMail(int count, long timeout) {
    long start = System.currentTimeMillis();
    while (System.currentTimeMillis() < start + timeout) {
      if (wiser.getMessages().size() >= count) {
        return wiser.getMessages().stream().map(m -> toModelMail(m)).collect(toList());
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    throw new Error("No mail :(");
  }

  public static MailMessage toModelMail(WiserMessage m) {
    try {
      MimeMessage mm = m.getMimeMessage();
      return new MailMessage (mm.getAllRecipients()[0].toString(), (String) mm.getContent());
    } catch (MessagingException | IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public void start(){
    wiser.start();
  }

  public void stop(){
    wiser.stop();
  }

  public String findConfirmationLink(String email, List<MailMessage> mailMessages) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}
