package com.my.core.cryptography.vigenere.decryptor;

import com.my.core.cryptography.vigenere.property.VigenereCipherProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;

public class VigenereCipherDecryptorTest {
    private VigenereCipherDecryptor vigenereCipherDecryptor;
    private Properties properties;

    @Before
    public void setUp() throws Exception {
    vigenereCipherDecryptor = new VigenereCipherDecryptor();
        properties = new Properties();
        properties.setProperty(VigenereCipherProperty.KEY_WORD.name(), "break");
    }

    @Test
    public void decrypt() throws Exception {
        String input = "dicpdpxvazip";
        String output = vigenereCipherDecryptor.decrypt(input, properties);
        Assert.assertThat("cryptography", is(output));
    }



}