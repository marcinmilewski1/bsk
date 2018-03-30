package com.my.core.cryptography.matrix.number.encryptor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;

public class NumberKeyMatrixTranspositionEncryptorTest {

    private NumberKeyMatrixTranspositionEncryptor numberKeyMatrixTranspositionEncryptor;

    @Before
    public void setUp() throws Exception {
        numberKeyMatrixTranspositionEncryptor = new NumberKeyMatrixTranspositionEncryptor();
    }

    @Test
    public void whenTextCryptographyosaAnd5x5AndKey34152ShouldBeYropascohtpargy() throws Exception {
        String output = numberKeyMatrixTranspositionEncryptor.encrypt("cryptographyosa", new Properties());
        Assert.assertThat(output, is("yropascohtpargy"));
    }

    @Test
    public void whenTextIsLongerForOneMatrixShoudlReadFromMore() throws Exception {
        String output = numberKeyMatrixTranspositionEncryptor.encrypt("cryptographyosacryptographyosa", new Properties());
        Assert.assertThat(output, is("yroyrpaspacohcotpatprgyrgoshay"));
    }

}