package EncryptionDecryption;

import java.util.Scanner;

public class ED2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        int key = scanner.nextInt();
        String encryptedMessage = encrypt(message, key);
        System.out.println(encryptedMessage);
    }

    static String encrypt(String m, int k) {
        char min = 97;
        char max = 122;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            if (m.charAt(i) <= max && m.charAt(i) >= min) {
                sb.append((char) ((m.charAt(i) + k - min) % 26 + min));
            } else {
                sb.append(m.charAt(i));
            }
        }
        return sb.toString();
    }

}
