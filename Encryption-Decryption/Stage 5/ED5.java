package EncryptionDecryption;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ED5 {

    public static void main(String[] args) throws IOException {
        String mode = "enc";
        int key = 0;
        String data = "";
        String in = null;
        String out = null;
        PrintStream printStream = System.out;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
                case "-in":
                    in = args[i + 1];
                    break;
                case "-out":
                    out = args[i + 1];
                    break;
            }
        }

        if (data.equals("") && in != null) {
            data = Files.readString(Path.of(in));
        }
        if (out != null) {
            printStream = new PrintStream(out);
        }

        switch (mode) {
            case "enc":
                String encryptedMessage = encrypt(data, key);
                printStream.print(encryptedMessage);
                break;
            case "dec":
                String decryptedMessage = decrypt(data, key);
                printStream.print(decryptedMessage);
                break;
        }
    }

    static String encrypt(String m, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            sb.append((char) (m.charAt(i) + k));
        }
        return sb.toString();
    }

    static String decrypt(String m, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            sb.append((char) (m.charAt(i) - k));
        }
        return sb.toString();
    }

}
