package com.cms.ioCreams.core.io.test;

import com.cms.ioCreams.core.io.FileSystemResource;
import com.cms.ioCreams.core.io.FileSystemResourceLoader;
import com.cms.ioCreams.core.io.Resource;
import com.cms.ioCreams.core.io.ResourceLoader;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;



public class Test {



    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Test.class);

        logger.info("调试");

        ResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource("/Users/creams/Desktop/test.txt");
        try {
            System.out.println(resource.getFile().getAbsolutePath());
            System.out.println(resource.contentLength());
            System.out.println(resource.getFilename());
            System.out.println(resource.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("test.properties");
        try {
            System.out.println(file.getAbsolutePath());
            InputStream inputStream = new FileInputStream(file);
            int a = 0;
            while ((a = inputStream.read()) != -1){
                System.out.print((char) a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
