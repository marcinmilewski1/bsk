package com.my.core.cryptography.caesar.decryptor;

import com.my.core.cryptography.caesar.encryptor.CaesarCipherEncryptor;
import com.my.core.cryptography.caesar.property.CaesarCipherProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;

public class CaesarCipherDecryptorTest {
    private CaesarCipherDecryptor caesarCipherDecryptor;
    private Properties properties;
    @Before
    public void setUp() throws Exception {
        caesarCipherDecryptor = new CaesarCipherDecryptor();
        properties = new Properties();
        properties.setProperty(CaesarCipherProperty.K0.name(), "4");
        properties.setProperty(CaesarCipherProperty.K1.name(), "5");
        properties.setProperty(CaesarCipherProperty.N.name(), "21");
    }

    @Test
    public void decrypt() throws Exception {
        String input = "testdata";
        CaesarCipherEncryptor caesarCipherEncryptor = new CaesarCipherEncryptor();
        String encrypted = caesarCipherEncryptor.encrypt(input, properties);
        String decrypted = caesarCipherDecryptor.decrypt(encrypted, properties);
        Assert.assertThat(decrypted, is(input));
    }

}