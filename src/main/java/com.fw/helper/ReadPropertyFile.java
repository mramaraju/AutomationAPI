package com.fw.helper;

import com.fw.utils.PropertiesFileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ReadPropertyFile {

   PropertiesFileReader NR;

    {
        try {

            NR = new PropertiesFileReader(new File("./conf/Automation.properties"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public  String apiUrl=NR.getValue("APIURL");
    public  String APIUsedCarEndPoint=NR.getValue("APIUSEDCARENDPOINT");



}
