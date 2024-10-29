import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        System.out.println("This program creates a folder in LocalLow\\George Wei named FindAndReplace. The files in FindAndReplace are the files that will be renamed.\nThe program searches each file name in FindAndReplace for any instance of the String to find and will replace it. The program will first ask for the String to find and then for the String to replace it with.");
        //Create a folder path to store for the program
        File studioFolder = new File(System.getProperty("user.home") + "\\AppData\\LocalLow\\George Wei");
        if (!studioFolder.exists()){
            studioFolder.mkdir();
        }
        File programFolder = new File(System.getProperty("user.home") + "\\AppData\\LocalLow\\George Wei\\FindAndReplace");
        if (!programFolder.exists()){
            programFolder.mkdir();
        }
        BufferedReader readguy = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the String to find.");
        String findWord = readguy.readLine();
        if (findWord.equals("")){
            System.out.println("Don't do that. I'm ending the program before you do that.");
            System.exit(0);
        }
        System.out.println("Please enter the String to replace it with.");
        String replaceWord = readguy.readLine();
        File[] files = programFolder.listFiles();
        for (int i = 0; i < files.length; i++){
            File file = files[i];
            for (int e = 0; e < file.getName().length() - findWord.length(); e++){
                if (file.getName().substring(e, e + findWord.length()).equals(findWord)){
                    File newFile = new File(file.getPath().substring(0, file.getPath().length() - file.getName().length()) + file.getName().substring(0, e) + replaceWord + file.getName().substring(e + findWord.length()));
                    if (newFile.exists()) throw new java.io.IOException("file exists");
                    boolean success = file.renameTo(newFile);
                    if (success) {
                        file = newFile;
                    } else {
                        System.out.println("Failed to rename " + file.getName());
                    }
                    e += replaceWord.length() - 1;
                }
            }
        }
    }
}