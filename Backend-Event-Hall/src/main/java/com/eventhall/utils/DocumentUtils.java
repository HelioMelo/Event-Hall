package com.eventhall.utils;

public class DocumentUtils {

    private DocumentUtils() {}

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) return false;
        if (cpf.chars().distinct().count() == 1) return false;

        int[] numbers = cpf.chars().map(c -> c - '0').toArray();
        int sum = 0;
        for (int i = 0; i < 9; i++) sum += numbers[i] * (10 - i);
        int check1 = sum % 11 < 2 ? 0 : 11 - (sum % 11);
        if (numbers[9] != check1) return false;

        sum = 0;
        for (int i = 0; i < 10; i++) sum += numbers[i] * (11 - i);
        int check2 = sum % 11 < 2 ? 0 : 11 - (sum % 11);
        return numbers[10] == check2;
    }

    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null || !cnpj.matches("\\d{14}")) return false;
        if (cnpj.chars().distinct().count() == 1) return false;

        int[] numbers = cnpj.chars().map(c -> c - '0').toArray();
        int[] weight1 = {5,4,3,2,9,8,7,6,5,4,3,2};
        int[] weight2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};

        int sum = 0;
        for (int i = 0; i < 12; i++) sum += numbers[i] * weight1[i];
        int check1 = sum % 11 < 2 ? 0 : 11 - (sum % 11);
        if (numbers[12] != check1) return false;

        sum = 0;
        for (int i = 0; i < 13; i++) sum += numbers[i] * weight2[i];
        int check2 = sum % 11 < 2 ? 0 : 11 - (sum % 11);
        return numbers[13] == check2;
    }

    public static void validateDocument(String doc) {
        if (doc == null) throw new IllegalArgumentException("Documento não pode ser nulo");
        doc = doc.replaceAll("[^\\d]", "");

        if (doc.length() == 11) {
            if (!isValidCPF(doc)) throw new IllegalArgumentException("CPF inválido");
        } else if (doc.length() == 14) {
            if (!isValidCNPJ(doc)) throw new IllegalArgumentException("CNPJ inválido");
        } else {
            throw new IllegalArgumentException("Documento deve ter 11 (CPF) ou 14 (CNPJ) dígitos");
        }
    }


}
