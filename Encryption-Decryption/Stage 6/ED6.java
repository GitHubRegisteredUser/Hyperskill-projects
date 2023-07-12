package EncryptionDecryption;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ED6 {

    public static void main(String[] args) throws IOException {
        String mode = "enc";
        int key = 0;
        String data = "";
        String in = null;
        String out = null;
        PrintStream printStream = System.out;
        Algorithm algorithm = new Shift();


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
                case "-alg":
                    if (args[i + 1].equals("unicode")) {
                        algorithm = new Unicode();
                    }
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
                String encryptedMessage = algorithm.encrypt(data, key);
                printStream.print(encryptedMessage);
                break;
            case "dec":
                String decryptedMessage = algorithm.decrypt(data, key);
                printStream.print(decryptedMessage);
                break;
        }
    }

}

class Unicode implements Algorithm {

    @Override
    public String encrypt(String m, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            sb.append((char) (m.charAt(i) + k));
        }
        return sb.toString();
    }

    @Override
    public String decrypt(String m, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            sb.append((char) (m.charAt(i) - k));
        }
        return sb.toString();
    }

}

class Shift implements Algorithm {

    @Override
    public String encrypt(String m, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            if (m.charAt(i) <= 122 && m.charAt(i) >= 97) {
                sb.append((char) ((m.charAt(i) + k - 97) % 26 + 97));
            } else if (m.charAt(i) <= 90 && m.charAt(i) >= 65) {
                sb.append((char) ((m.charAt(i) + k - 65) % 26 + 65));
            } else {
                sb.append(m.charAt(i));
            }
        }
        return sb.toString();
    }

    @Override
    public String decrypt(String m, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            if (m.charAt(i) <= 122 && m.charAt(i) >= 97) {
                sb.append((char) ((m.charAt(i) + 26 - k - 97) % 26 + 97));
            } else if (m.charAt(i) <= 90 && m.charAt(i) >= 65) {
                sb.append((char) ((m.charAt(i) + 26 - k - 65) % 26 + 65));
            } else {
                sb.append(m.charAt(i));
            }
        }
        return sb.toString();
    }

}

interface Algorithm {

    String encrypt(String data, int key);

    String decrypt(String data, int key);

}
