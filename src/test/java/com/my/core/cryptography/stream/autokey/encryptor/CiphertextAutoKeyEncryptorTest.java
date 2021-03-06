package com.my.core.cryptography.stream.autokey.encryptor;

import com.my.core.cryptography.generator.stream.property.LfsrGeneratorProperty;
import com.my.core.cryptography.stream.autokey.decryptor.CiphertextAutoKeyDecryptor;
import com.my.core.cryptography.stream.ssc.property.SynchronousStreamProperty;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CiphertextAutoKeyEncryptorTest {
    private CiphertextAutoKeyEncryptor ciphertextAutoKeyEncryptor;
    private CiphertextAutoKeyDecryptor ciphertextAutoKeyDecryptor;
    private Properties properties;
    private File inputFile;
    private File output;

    @Before
    public void setUp() throws Exception {
        ciphertextAutoKeyEncryptor = new CiphertextAutoKeyEncryptor();
        ciphertextAutoKeyDecryptor = new CiphertextAutoKeyDecryptor();
        properties = new Properties();

        properties.setProperty(LfsrGeneratorProperty.POLYNOMIAL.name(), "1001");
        properties.setProperty(LfsrGeneratorProperty.SEED.name(), "1010");
        inputFile = new File(this.getClass().getResource("/test.txt").toURI());

        File parentDirectory = inputFile.getParentFile();
        output = new File(parentDirectory, "output.txt");
        properties.setProperty(SynchronousStreamProperty.OUTPUT_FILE_PATH.name(), output.getPath());
    }


    @Test
    public void inputFileTest() throws Exception {
        assertThat(inputFile.exists(), is(true));
        assertThat(inputFile.length(), greaterThan(0l));
    }

    @Test
    public void encryptOutFileShouldSameLengthAsInputFile() throws Exception {
        File outputFile = ciphertextAutoKeyEncryptor.encrypt(inputFile, properties);
        assertThat(outputFile.length(), is(inputFile.length()));
    }

    @Test
    public void encryptDecryptTest() throws Exception {
        File encrypted = ciphertextAutoKeyEncryptor.encrypt(inputFile, properties);

        properties.setProperty(SynchronousStreamProperty.OUTPUT_FILE_PATH.name(), output.getPath());
        File decrypted = ciphertextAutoKeyDecryptor.decrypt(encrypted, properties);

        assertThat(decrypted.length(), is(inputFile.length()));
        byte[] decryptedData = Files.readAllBytes(decrypted.toPath());
        byte[] inputData = Files.readAllBytes(inputFile.toPath());
        for (int i = 0; i < encrypted.length(); i++) {
            assertTrue(inputData[i] == decryptedData[i]);
        }
    }

}