package hd.assignment.utils;

import java.io.File;

public class TestHelpers {


    public static String getLocalFileUrl(String relativePath) {
        File file = new File(relativePath);
        return "file://" + file.getAbsolutePath();
    }
}
