package EncryptionDecryption;

public class ED1 {

    public static void main(String[] args) {
        String message = "we found a treasure!";
        String encryptedMessage = encrypt(message);
        System.out.println(encryptedMessage);
    }

    static String encrypt(String s) {
        char min = 97;
        char max = 122;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) <= 122 && s.charAt(i) >= 97) {
                sb.append((char) (min - s.charAt(i) + max));
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

}
