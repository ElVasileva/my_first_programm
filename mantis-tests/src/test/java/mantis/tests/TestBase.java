package mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import mantis.appmanager.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    protected static ApplicationManager app;
    protected WebDriver wd;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
      app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

  public boolean isIssueOpen(int issueId) throws MalformedURLException, RemoteException, ServiceException {
    MantisConnectPortType mc = app.soap().getMantisConnect();
    biz.futureware.mantis.rpc.soap.client.IssueData issueData =
        mc.mc_issue_get(app.getProperty("web.adminLogin")
            , app.getProperty("web.adminPassword")
            , BigInteger.valueOf(issueId));
    Issue issue = new Issue().withResolution(issueData.getResolution().getName());
    return issue.getResolution().equals("open");

  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
