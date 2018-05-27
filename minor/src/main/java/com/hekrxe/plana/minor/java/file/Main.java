package com.hekrxe.plana.minor.java.file;

import java.io.*;
import java.util.Scanner;

/**
 * User: tanhuayou
 * Date: 2018/5/26
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String relativelyPath = System.getProperty("user.dir");
        // /Users/tanhuayou/code/plana
        System.out.println(relativelyPath);


    }

    private static void fileRW() throws IOException {
        String fileName = "aaa.txt";
        String eof = "I am fine,too!";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            writer.write(text);
            writer.write("\n");
            if (eof.equalsIgnoreCase(text)) {
                break;
            }
        }
        writer.flush();
        writer.close();
        int line = 1;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String text = null;
        while ((text = reader.readLine()) != null) {
            System.out.println(line + " " + text);
            line++;
        }
        reader.close();
    }
}
