package com.my.core.cryptography.des;

import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.generator.stream.util.BinaryUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import static com.my.core.cryptography.generator.stream.util.BinaryUtils.toByteArrayLSBLeft;


/**
 * Created by marcin on 5/5/16.
 */
public class DesEncryptor implements Encryptor {
    private static Logger logger = Logger.getLogger(DesEncryptor.class);
    @Override
    public String encrypt(String data, Properties properties) {
        return null;
    }

    @Override
    public File encrypt(File data, Properties properties) throws IOException {
        String outputFilePath = properties.getProperty(DesProperty.OUTPUT_FILE_PATH.name());
        String givenKey = properties.getProperty(DesProperty.KEY.name());
        logger.trace("Given Key: " + givenKey);
        boolean[] key = DesAlgorithm.create64BitKey(DesUtils.get64KeyBlock(properties));
        logger.trace("Key+: " + BinaryUtils.toString(key));
        boolean[][] subKeys = DesAlgorithm.create16Subkeys(key);
        logger.trace("Sub keys: " + BinaryUtils.toString(subKeys));

        byte[] dataBytes = Files.readAllBytes(data.toPath());
        int complementBytes = 8 - dataBytes.length % 8;
        boolean[][] blocks = DesUtils.createBlocks(dataBytes);

        boolean[][] encryptedBlocks = new boolean[blocks.length][];
        for (int i = 0; i < blocks.length; i++) {
            encryptedBlocks[i] = DesAlgorithm.encryptBlock(blocks[i], subKeys);
        }

        if (complementBytes > 0 && complementBytes != 8) {
            createFileWithComplementBitsNumber(outputFilePath, complementBytes);
        }
        return createFile(outputFilePath, toByteArrayLSBLeft(encryptedBlocks));
    }

    private void createFileWithComplementBitsNumber(String outputFilePath, int complementBytes) throws IOException {
        String outputPath = outputFilePath.concat(".compl");
        logger.debug("Creating complement output file: " + outputFilePath + " with " + complementBytes + " bytes number.");
        File outputFile = new File(outputPath);
        FileOutputStream stream = new FileOutputStream(outputFile);
        stream.write(String.valueOf(complementBytes).getBytes());
        stream.close();
    }

    private File createFile(String outputFilePath, byte[] output) throws IOException {
        logger.debug("Creating output file, output size = " + output.length);
        File outputFile = new File(outputFilePath);
        FileOutputStream stream = new FileOutputStream(outputFile);
        stream.write(output);
        stream.close();
        return outputFile;
    }


}
