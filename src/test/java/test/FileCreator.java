package test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class FileCreator {

    static final String numbers = "0123456789";
    static final String russianLetters = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    static final String englishLetters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String randomName(int len) {
        StringBuilder buffer = new StringBuilder(russianLetters).append(numbers).append(englishLetters);
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(buffer.charAt(new SecureRandom().nextInt(buffer.length())));
        return sb.toString();
    }

    public void createNewFile(String name, String text) throws IOException {
        File statText = new File(name);
        statText.createNewFile();
        FileOutputStream is = new FileOutputStream(statText);
        OutputStreamWriter osw = new OutputStreamWriter(is);
        Writer w = new BufferedWriter(osw);
        w.write(text);
        w.close();
    }

    public boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir (new File(dir, children[i]));

                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }


    public String readFile(String path)throws IOException {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, Charset.defaultCharset());
    }

}
