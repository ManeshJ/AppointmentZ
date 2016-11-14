package com.intelligentz.appointmentz.model.Data;

import com.intelligentz.appointmentz.exception.IdeabizException;
import com.intelligentz.appointmentz.model.ApplicationInformation;

import java.io.*;
import java.util.Properties;

/**
 * Created by anushkas on 10/19/15.
 */
public class DataImp implements DataInterface {


    public ApplicationInformation getAppInfo(String applicationId) {
        ApplicationInformation applicationInformation =  new ApplicationInformation();


        return applicationInformation;
    }

    public ApplicationInformation loadPropFile() throws IdeabizException{
        ApplicationInformation applicationInformation =  new ApplicationInformation();
        Properties prop = new Properties();
        InputStream input = null;
        File dataSourceFile = new File(System.getProperty("user.dir") + File.separator + "config.properties");
        if (!dataSourceFile.exists()) {
            System.out.println("File not found:" + System.getProperty("user.dir") );
            throw new IdeabizException("Properties File Not Found: "+ System.getProperty("user.dir"));
        }

        try {

            input = new FileInputStream(dataSourceFile);
            prop.load(input);
            applicationInformation.setRefreshToken(prop.getProperty("auth_refreshToken"));
            applicationInformation.setTokenAPIURL(prop.getProperty("auth_url"));
            applicationInformation.setConsumerSecret(prop.getProperty("auth_consumerSecret"));
            applicationInformation.setConsumerKey(prop.getProperty("auth_consumerKey"));
            applicationInformation.setAccessToken(prop.getProperty("auth_accessToken"));
            applicationInformation.setApplicationId(prop.getProperty("app_id"));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return applicationInformation;
    }

    public boolean saveTokenFile(ApplicationInformation applicationInformation) {
        Properties prop = new Properties();
        OutputStream output = null;

        try {
            File dataSourceFile = new File(System.getProperty("user.dir") + File.separator + "config.properties");
            if (!dataSourceFile.exists()) {
                System.out.println("File not found");
                return false;
            }


            output = new FileOutputStream(dataSourceFile);

            prop.setProperty("auth_url", applicationInformation.getTokenAPIURL());
            prop.setProperty("auth_consumerKey", applicationInformation.getConsumerKey());
            prop.setProperty("auth_consumerSecret", applicationInformation.getConsumerSecret());
            prop.setProperty("auth_refreshToken", applicationInformation.getRefreshToken());
            prop.setProperty("auth_accessToken", applicationInformation.getAccessToken());
            prop.setProperty("auth_scope", "PRODUCTION");
            prop.setProperty("app_id", "206");

            // save properties to project root folder
            prop.store(output, null);
            return true;

        } catch (IOException io) {
            io.printStackTrace();
            return false;
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }





}
