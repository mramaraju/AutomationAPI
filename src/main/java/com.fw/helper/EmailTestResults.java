package com.fw.helper;

import com.fw.framework.FWStaticStore;
import com.fw.framework.email.EmailClient;
import com.fw.framework.infra.CliProcessor;
import com.fw.framework.infra.FWRunner;
import com.fw.framework.infra.LogWrapper;
import com.fw.framework.infra.TestContext;
import com.fw.framework.parser.FrameworkConfigParser;
import com.fw.utils.UtilsZip;

import java.io.File;

public class EmailTestResults {


   public void Email() throws Exception {


       //check if email client enabled in the config file and then send email
       if (FWStaticStore.frameworkConfig.isEnableEmailClient()) {
           String profile = "dev";

           // get the profile
           FrameworkConfigParser ECP = new FrameworkConfigParser(true, null == CliProcessor.getProfile() ? profile : CliProcessor.getProfile());
           //EmailClient EC = new EmailClient(ECP.getEmailReceiversEmail(), ECP.getEmailSendersEmail(), "mahesbe@gmail.com", "MumDad!@#271986", ECP.getEmailSMTPServer(),
           // ECP.getEmailSMTPSSLPort(), ECP.getEmailSMTPAuthRequired(), "true", ECP.getEmailSubject(), ECP.getEmailBody());


           EmailClient EC = new EmailClient(ECP.getEmailReceiversEmail(), ECP.getEmailSendersEmail(), new File(ECP.getEmailAuthSettingsFilePath()),
                   "gmail",ECP.getEmailSMTPServer(),ECP.getEmailSMTPSSLPort(),ECP.getEmailSMTPAuthRequired(),"true",ECP.getEmailSubject(), ECP.getEmailBody());



           //Delete the reporting zip
           File f = new File("/TestResults.zip");
           if (f.exists() && !f.isDirectory()) {
               f.delete();
           }

           //initialize the zip and send all the logs to the user
           UtilsZip UZ = new UtilsZip();
           UZ.zip(new File("./reporting"), new File("./TestResults.zip"));
           EC.sendEmailWithAnAttachment("./TestResults.zip");

       }

   }


}
