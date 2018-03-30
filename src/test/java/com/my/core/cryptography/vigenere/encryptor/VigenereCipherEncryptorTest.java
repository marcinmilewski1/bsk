package com.my.core.cryptography.vigenere.encryptor;

import com.my.core.cryptography.vigenere.property.VigenereCipherProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;

public class VigenereCipherEncryptorTest {
    private VigenereCipherEncryptor vigenereCipherEncryptor;
    private Properties properties;

    @Before
    public void setUp() throws Exception {
        vigenereCipherEncryptor = new VigenereCipherEncryptor();
        properties = new Properties();
        properties.setProperty(VigenereCipherProperty.KEY_WORD.name(), "break");
    }

    @Test
    public void encrypt() throws Exception {
        String input = "cryptography";
        String output = vigenereCipherEncryptor.encrypt(input, properties);
        Assert.assertThat(output, is("dicpdpxvazip"));
    }

}