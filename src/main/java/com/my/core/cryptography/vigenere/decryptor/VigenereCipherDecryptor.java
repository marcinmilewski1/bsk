package com.my.core.cryptography.vigenere.decryptor;

import com.google.common.collect.ImmutableBiMap;
import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.vigenere.property.VigenereCipherProperty;

import java.io.File;
import java.util.Properties;

import static com.my.core.util.AlphabetBiMap.*;

public class VigenereCipherDecryptor implements Decryptor {
    @Override
    public String decrypt(String data, Properties properties) {
        String keyword = properties.getProperty(VigenereCipherProperty.KEY_WORD.name());
        if (keyword.isEmpty()) throw new IllegalArgumentException("k cannot be empty");
        String key = generateStraightKey(keyword, data.length()).toLowerCase();
        return decryptInternal(data.toLowerCase(), key);
    }

    @Override
    public File decrypt(File data, Properties properties) {
        return null;
    }

    private String generateStraightKey(String keyword, int length) {
        StringBuilder sb = new StringBuilder();
        int keywordLength = keyword.length();
        for (int i = 0; i < length; i++) {
            sb.append(keyword.charAt(i % keywordLength));
        }
        return sb.toString();
    }

    private String decryptInternal(String data, String key) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            decrypted.append(decryptChar(data.charAt(i), key.charAt(i)));
        }
        return decrypted.toString();
    }

    private char decryptChar(char data, char key) {
        int keyValue = characterValueMap.get(key);
        ImmutableBiMap<Integer, Character> keyAlphabet = alphabets.get(keyValue);
        int encodedCharIdex = keyAlphabet.inverse().get(data);
        return alphabet.get(encodedCharIdex);
    }

}
