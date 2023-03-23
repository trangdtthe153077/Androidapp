package com.example.firebase.Model;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static List<Comic> comicList = new ArrayList<>();
    public static Comic comicSelected;
    public static List<Chapter> chapterList;
    public static Chapter chapterSelected;
    public static int chapterIndex=-1;

    public static String formatString(String name) {
        StringBuilder finalResult = new StringBuilder(name.length()>15?name.substring(0,15)+"...":name);
        return finalResult.toString();
    }
}
