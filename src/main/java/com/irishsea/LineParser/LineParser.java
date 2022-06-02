package com.irishsea.LineParser;

import java.util.regex.Pattern;


public class LineParser implements Comparable<LineParser> {

    private String city;

    private final Pattern pattern = Pattern
            .compile("\\s*;\\s*"
                    , Pattern.CASE_INSENSITIVE);



    public String getCity(String city){
        return city;
    }

    public void parseLine(String lineForParse) {
        String[] words = pattern.split(lineForParse);
        city = words[0];
    }


    @Override
    public int compareTo(LineParser o) {
        return 0;
    }
}
