package com.my.core.cryptography.matrix.word.encryptor;

import com.my.core.cryptography.matrix.word.properties.WordKeyMatrixTranspositionProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;

public class WordKeyColumnVariantMatrixTranspositionEncryptorTest {

    private WordKeyColumnVariantMatrixTranspositionEncryptor wordKeyColumnVariantMatrixTranspositionEncryptor;
    private Properties properties;
    private String input;

    @Before
    public void setUp() throws Exception {
        this.wordKeyColumnVariantMatrixTranspositionEncryptor = new WordKeyColumnVariantMatrixTranspositionEncryptor();
        properties = new Properties();
        properties.setProperty(WordKeyMatrixTranspositionProperty.KEY_WORD.name(), "convenience");
        input = "hereisasecretmessageencipheredbytransposition";
    }

    @Test
    public void encrypt() throws Exception {
        String output = wordKeyColumnVariantMatrixTranspositionEncryptor.encrypt(input, properties);
        Assert.assertThat(output, is("heespnirrsseeseiyascbtemgepnandictrtahsoieero"));
    }

}