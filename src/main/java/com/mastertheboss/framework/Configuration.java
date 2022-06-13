package com.mastertheboss.framework;

import org.apache.commons.configuration.ConfigurationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    public static String appConfigPath = System.getenv("MySoftware")+"/Configuration.properties";

    private Properties appProperties = new Properties();

    protected Configuration() {
        setAppProperties();
    }

    public static Configuration of() {
        return new Configuration();
    }


    public Properties getAppProperties() {
        return appProperties;
    }

    public String getPath(String property){return getAppProperties().getProperty(property);}

    public void setAppProperties() {
        try {
            appProperties.load(new FileInputStream(appConfigPath));

        } catch (IOException e) {
            System.out.println(e.getMessage());
            MyLogger.ErrorLog(Class.class,e.getMessage());
        }

    }

    public static void main(String[] args) throws ConfigurationException, IOException {
//       String s = Configuration.of().getPath("source");
//        System.out.println(s);
//        PropertiesConfiguration config = new PropertiesConfiguration("src/main/java/framework/Configuration.properties");
//
//        config.setProperty("source", "C:/Users/MohsenFakhari/OneDrive - QCENTRIS/Dokumente/prepration works/Oebb Projekt/xml/XmlTestFiles/Source");
//        config.save();

//        System.setProperty("test123","xxx");


       String s = Configuration.of().getPath("text");
        System.out.println(s);


    }



}
