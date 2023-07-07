package EncryptionDecryption;

import java.util.Scanner;

public class ED3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        String message = scanner.nextLine();
        int key = scanner.nextInt();
        if (operation.equals("enc")) {
            String encryptedMessage = encrypt(message, key);
            System.out.println(encryptedMessage);
        } else {
            String decryptedMessage = decrypt(message, key);
            System.out.println(decryptedMessage);
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
