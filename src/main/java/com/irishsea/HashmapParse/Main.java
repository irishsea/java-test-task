package com.irishsea.HashmapParse;


import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> parserMap = new HashMap<>();
        File fileXml = new File("address.xml");
//        try {
//            FileInputStream fis = new FileInputStream(fileXml);
//            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
//            BufferedReader reader = new BufferedReader(isr);
//            String str;
//            while ((str = reader.readLine()) != null){
//                System.out.println(str);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        XMLParser xmlParser = new XMLParser(fileXml);
        xmlParser.parse(parserMap);
        for (String key : parserMap.keySet()) {
            int count = parserMap.get(key);
            if (count > 1) {
                System.out.println(key + " " + count);
            }
        }
    }
}
