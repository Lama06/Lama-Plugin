package io.github.lama06.lamaplugin;

import java.io.*;
import java.util.Scanner;

public final class Util {
    /**
     * Schreibt etwas in eine Datei
     *
     * @param file    die Datei
     * @param content der Inhalt, der in die Datei geschrieben werden soll
     */
    public static void writeToFile(File file, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
        writer.close();
    }

    /**
     * Liest den Inhalt einer Datei
     *
     * @param file die Datei
     * @return den Inhalt der Datei
     */
    public static String readFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        StringBuilder content = new StringBuilder();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            content.append(line);
        }
        scanner.close();
        return content.toString();
    }

    public static String argsToString(String[] args) {
        StringBuilder builder = new StringBuilder();
        for(String arg : args) {
            builder.append(arg).append(" ");
        }
        return builder.toString();
    }
}
