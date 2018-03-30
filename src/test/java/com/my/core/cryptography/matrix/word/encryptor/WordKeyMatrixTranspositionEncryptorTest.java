package com.my.core.cryptography.matrix.word.encryptor;

import com.my.core.cryptography.matrix.word.properties.WordKeyMatrixTranspositionProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;

public class WordKeyMatrixTranspositionEncryptorTest {

    private WordKeyMatrixTranspositionEncryptor wordKeyMatrixTranspositionEncryptor;
    private Properties properties;

    @Before
    public void setUp() throws Exception {
        wordKeyMatrixTranspositionEncryptor = new WordKeyMatrixTranspositionEncryptor();
        properties = new Properties();
        properties.setProperty(WordKeyMatrixTranspositionProperty.KEY_WORD.name(), "convenience");
    }

    @Test
    public void encrypt() throws Exception {
        String output = wordKeyMatrixTranspositionEncryptor.encrypt("hereisasecretmessageencipheredbytransposition", properties);
        Assert.assertThat(output, is("hecrnceyiisepsgdirntoaaesrmpnssroeebtetiaeehs"));
    }



}