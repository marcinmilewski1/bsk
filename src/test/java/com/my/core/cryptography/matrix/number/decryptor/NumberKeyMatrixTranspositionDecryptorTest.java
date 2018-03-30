package com.my.core.cryptography.matrix.number.decryptor;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;

public class NumberKeyMatrixTranspositionDecryptorTest {
    @Test
    public void when5x5AndYropascohtpargyAndKey34152ShouldBeCryptographyosa() throws Exception {
        NumberKeyMatrixTranspositionDecryptor numberKeyMatrixTranspositionDecryptor = new NumberKeyMatrixTranspositionDecryptor();
        String output = numberKeyMatrixTranspositionDecryptor.decrypt("yropascohtpargy", new Properties());
        Assert.assertThat(output, is("cryptographyosa"));
    }

    @Test
    public void whenTextIsLongerForOneMatrixShoudlReadFromMore() throws Exception {
        NumberKeyMatrixTranspositionDecryptor numberKeyMatrixTranspositionDecryptor = new NumberKeyMatrixTranspositionDecryptor();
        String output = numberKeyMatrixTranspositionDecryptor.decrypt("yroyrpaspacohcotpatprgyrgoshay", new Properties());
        Assert.assertThat(output, is("cryptographyosacryptographyosa"));
    }
}