package com.mss.tuess.util;

import java.io.File;
import java.util.Iterator; 
import sun.plugin.dom.core.Document;
import sun.plugin.dom.core.Element;
import sun.security.krb5.Config;

/**
 *Reference from http://martin3000.iteye.com/blog/1326578
 * @author Martin3000
 *
 */
public class XMLReader {
    // config file

    private static String filename = "conf.xml";
    private static Config config;

    /**
     * Get config from file
     *
     * @return
     */
    public static Config loadconfig() {
        if (config == null) {
           // config = getconfig();
        }
        return config;
    }
//
//    private static Config getconfig() {
//        Config config = new Config();
//        try {
//            File f = new File(filename);
//            if (!f.exists()) {
//                System.out.println("  Error : Config file doesn't exist!");
//                System.exit(1);
//            }
//            SAXReader reader = new SAXReader();
//            Document doc;
//            doc = reader.read(f);
//            Element root = doc.getRootElement();
//            Element data;
//            Iterator<?> itr = root.elementIterator("VALUE");
//            data = (Element) itr.next();
//
//            config.server = data.elementText("server").trim();
//            config.user = data.elementText("user").trim();
//            config.pass = data.elementText("pass").trim();
//            config.port = data.elementText("port").trim();
//            config.dbname = data.elementText("dbname").trim();
//
//        } catch (Exception ex) {
//            System.out.println("Error : " + ex.toString());
//        }
//        return config;
//
//    }
}
