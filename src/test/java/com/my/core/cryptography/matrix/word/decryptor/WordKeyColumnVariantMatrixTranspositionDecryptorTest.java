package com.my.core.cryptography.matrix.word.decryptor;

import com.my.core.cryptography.matrix.word.properties.WordKeyMatrixTranspositionProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;

public class WordKeyColumnVariantMatrixTranspositionDecryptorTest {

    private WordKeyColumnVariantMatrixTranspositionDecryptor wordKeyMatrixTranspositionDecryptor;
    private Properties properties;

    @Before
    public void setUp() throws Exception {
        wordKeyMatrixTranspositionDecryptor = new WordKeyColumnVariantMatrixTranspositionDecryptor();
        properties = new Properties();
        properties.setProperty(WordKeyMatrixTranspositionProperty.KEY_WORD.name(), "convenience");
    }

    @Test
    public void decrypt() throws Exception {
        String input = "heespnirrsseeseiyascbtemgepnandictrtahsoieero";
        String output = wordKeyMatrixTranspositionDecryptor.decrypt(input, properties);
        Assert.assertThat(output, is("hereisasecretmessageencipheredbytransposition"));
    }

}