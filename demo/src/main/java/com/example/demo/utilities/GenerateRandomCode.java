package com.example.demo.utilities;

import java.util.Random;

public class GenerateRandomCode {
    public static String generateRandomCode(int codeLength) {
        // Độ dài của mã

        // Dãy ký tự có thể xuất hiện trong mã
        String characters = "0123456789ABCDEFGHJKLMNOW";

        // Tạo đối tượng Random
        Random random = new Random();

        // StringBuilder để xây dựng mã ngẫu nhiên
        StringBuilder codeBuilder = new StringBuilder();

        // Tạo mã ngẫu nhiên
        for (int i = 0; i < codeLength; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }
}
