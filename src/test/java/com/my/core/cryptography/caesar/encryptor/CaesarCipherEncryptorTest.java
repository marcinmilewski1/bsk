package com.my.core.cryptography.caesar.encryptor;

import com.my.core.cryptography.caesar.property.CaesarCipherProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class CaesarCipherEncryptorTest {
    private CaesarCipherEncryptor caesarCipherEncryptor = new CaesarCipherEncryptor();

    @Before
    public void setUp() throws Exception {
    caesarCipherEncryptor = new CaesarCipherEncryptor();

    }

    @Test
    public void testk0k1coprimeWithN() throws Exception {
        Properties properties = new Properties();
        properties.setProperty(CaesarCipherProperty.K0.name(), "4");
        properties.setProperty(CaesarCipherProperty.K1.name(), "5");
        properties.setProperty(CaesarCipherProperty.N.name(), "21");
        String output = caesarCipherEncryptor.encrypt("testdata", properties);
        Assert.assertThat(output, is(notNullValue()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testk0k1DontcoprimeWithN() throws Exception {
        Properties properties = new Properties();
        properties.setProperty(CaesarCipherProperty.K0.name(), "4");
        properties.setProperty(CaesarCipherProperty.K1.name(), "7");
        properties.setProperty(CaesarCipherProperty.N.name(), "21");
        String output = caesarCipherEncryptor.encrypt("testdata", properties);
    }

    @Test
    public void testOutputShouldHaveSameLengthAsInput() throws Exception {
        Properties properties = new Properties();
        properties.setProperty(CaesarCipherProperty.K0.name(), "4");
        properties.setProperty(CaesarCipherProperty.K1.name(), "5");
        properties.setProperty(CaesarCipherProperty.N.name(), "21");
        String input = "test data";
        String output = caesarCipherEncryptor.encrypt(input, properties);
        Assert.assertThat(output.length(), is(input.length() -1)); // no white chars
    }


}