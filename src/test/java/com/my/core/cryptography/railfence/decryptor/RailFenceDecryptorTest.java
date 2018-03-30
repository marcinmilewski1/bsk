package com.my.core.cryptography.railfence.decryptor;

import com.my.core.cryptography.railfence.properties.RailfenceProperty;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RailFenceDecryptorTest {
    RailFenceDecryptor railFenceDecryptor;

    @Before
    public void setUp() throws Exception {
        railFenceDecryptor = new RailFenceDecryptor();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoDepthIsGivenShouldThrowException() throws Exception {
        String output = railFenceDecryptor.decrypt("Cryptography", new Properties());
    }

    @Test
    public void whenDepthIs3AndDataIsCtarporpyyghShouldBeCryptography() throws Exception {
        Properties properties = new Properties();
        properties.setProperty(RailfenceProperty.DEPTH.name(), "3");
        String output = railFenceDecryptor.decrypt("ctarporpyygh", properties);
        assertTrue(output.equals("cryptography"));
    }

    @Test
    public void depth2() throws Exception {
        Properties properties = new Properties();
        properties.setProperty(RailfenceProperty.DEPTH.name(), "2");
        String output = railFenceDecryptor.decrypt("cytgahrporpy", properties);
        assertThat(output, is("cryptography"));
    }

    @Test
    public void depth4() throws Exception {
        Properties properties = new Properties();
        properties.setProperty(RailfenceProperty.DEPTH.name(), "4");
        String output = railFenceDecryptor.decrypt("cgroryytahpp", properties);
        assertThat(output, is("cryptography"));
    }
}