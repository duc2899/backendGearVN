package com.example.demo.utilities;

import java.text.NumberFormat;
import java.util.Locale;

public class ConvertMoneyVietNam {
    public ConvertMoneyVietNam() {
    }

    public String formatMoney(double amount) {
        // Tạo một đối tượng NumberFormat cho tiền tệ Việt Nam
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        // Định dạng số tiền
        return currencyFormatter.format(amount);
    }

    public String formatMoney(int amount) {
        // Tạo một đối tượng NumberFormat cho tiền tệ Việt Nam
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        // Định dạng số tiền
        return currencyFormatter.format(amount);
    }
}
