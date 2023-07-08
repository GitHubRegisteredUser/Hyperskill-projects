package EncryptionDecryption;

public class ED4 {

    public static void main(String[] args) {
        String mode = "enc";
        int key = 0;
        StringBuilder data = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data.append(args[i + 1]);
                    break;
            }
        }

        switch (mode) {
            case "enc":
                String encryptedMessage = encrypt(data.toString(), key);
                System.out.println(encryptedMessage);
                break;
            case "dec":
                String decryptedMessage = decrypt(data.toString(), key);
                System.out.println(decryptedMessage);
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
