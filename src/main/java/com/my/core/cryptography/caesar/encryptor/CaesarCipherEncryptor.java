package com.my.core.cryptography.caesar.encryptor;

import com.google.common.math.IntMath;
import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.caesar.property.CaesarCipherProperty;

import java.io.File;
import java.util.Properties;

import static com.my.core.util.AlphabetBiMap.alphabet;
import static com.my.core.util.AlphabetBiMap.characterValueMap;

public class CaesarCipherEncryptor implements Encryptor {

    @Override
    public String encrypt(String data, Properties properties) {
        Integer k0 = Integer.valueOf(properties.getProperty(CaesarCipherProperty.K0.name()));
        Integer k1 = Integer.valueOf(properties.getProperty(CaesarCipherProperty.K1.name()));
        Integer n = Integer.valueOf(properties.getProperty(CaesarCipherProperty.N.name()));
        if (n > alphabet.size()) throw new IllegalArgumentException("n cannot be bigger than alphabet size");
        if (!(coprime(k0, n) && coprime(k1, n))) throw new IllegalArgumentException("k1, k0 are not coprime with n");
        return encryptInternal(data.replaceAll("\\s+", ""), k0, k1, n);
    }

    @Override
    public File encrypt(File data, Properties properties) {
        return null;
    }

    private String encryptInternal(String data, Integer k0, Integer k1, Integer n) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            encrypted.append(encryptChar(data.charAt(i), k0, k1, n));
        }
        return encrypted.toString();
    }

    private final Character encryptChar(char c, Integer k0, Integer k1, Integer n) {
        int a = characterValueMap.get(c);
        return alphabet.get(((a * k1) + k0) % n);
    }


    private final boolean coprime(Integer a, Integer b) {
        return IntMath.gcd(a, b) == 1 ? true : false;
    }
}
