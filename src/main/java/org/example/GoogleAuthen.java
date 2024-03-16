package org.example;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GoogleAuthen {
    private static final Map<String, GoogleAuthenticatorKey> userKeys = new HashMap<>();

    private static final GoogleAuthenticator authenticator = new GoogleAuthenticator();

    public static void main(String[] args) throws IOException, WriterException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register user");
            System.out.println("2. Authenticate user");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    authenticateUser(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerUser(Scanner scanner) throws WriterException, IOException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (userKeys.containsKey(username)) {
            System.out.println("User already exists.");
            return;
        }

        GoogleAuthenticatorKey key = authenticator.createCredentials();

        String qrCodeData = "otpauth://totp/Example:%20alice@google.com?secret=" + key + "&issuer=Example";

        // 创建一个 QR 码编写器
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // 将字符串数据编码为二维矩阵
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 350, 350);

        // 将二维矩阵写入指定的文件路径
        Path path = FileSystems.getDefault().getPath("qr");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        userKeys.put(username, key);

        System.out.println("User registered successfully.");
        System.out.println("Secret Key: " + key.getKey());
        GoogleAuthenticator GoogleAuthenticator = new GoogleAuthenticator();
        System.out.println(GoogleAuthenticator.getTotpPassword(key.getKey()));
//        System.out.println("code: " + authenticator.getTotpPassword(String.valueOf(key)));
        System.out.println("Please add this secret key to your Google Authenticator app.");
    }

    private static void authenticateUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        GoogleAuthenticatorKey userKey = userKeys.get(username);
//        System.out.println("userKey" + userKey.getVerificationCode());
        if (userKey == null) {
            System.out.println("User does not exist.");
            return;
        }

        System.out.print("Enter one-time code: ");
        int code = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        boolean isCodeValid = authenticator.authorize(userKey.getKey(), code);
        if (isCodeValid) {
            System.out.println("Authentication successful!");
        } else {
            System.out.println("Authentication failed. Invalid one-time code.");
        }
    }
}