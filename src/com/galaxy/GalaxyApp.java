package com.galaxy;

import com.galaxy.exceptions.NumberFormatException;
import com.galaxy.notes.GalaxyNotesParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GalaxyApp {

    public static void main(String[] args) {
        if (args.length<1) {
            System.out.println("Usage: java -cp galaxy.jar com.galaxy.GalaxyApp <path to file>");
            System.out.println("Or: java com.galaxy.GalaxyApp <path to file>");
            return;
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(args[0]);
            Scanner scanner = new Scanner(fileInputStream);
            GalaxyNotesParser parser = new GalaxyNotesParser();
            while (scanner.hasNextLine()) {
                String response = parser.processNotesLine(scanner.nextLine());
                if (response!=null) {
                    System.out.println(response);
                }
            }
            fileInputStream.close();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
}
