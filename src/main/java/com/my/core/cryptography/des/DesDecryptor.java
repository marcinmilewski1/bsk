package com.my.core.cryptography.des;

import com.my.core.cryptography.Decryptor;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import static com.my.core.cryptography.generator.stream.util.BinaryUtils.toByteArrayLSBLeft;

/**
 * Created by marcin on 5/5/16.
 */
public class DesDecryptor implements Decryptor {
    private static Logger logger = Logger.getLogger(DesDecryptor.class);

    @Override
    public String decrypt(String data, Properties properties) {
        return null;
    }

    @Override
    public File decrypt(File data, Properties properties) throws IOException {
        String outputFilePath = properties.getProperty(DesProperty.OUTPUT_FILE_PATH.name());
        boolean[] key = DesAlgorithm.create64BitKey(DesUtils.get64KeyBlock(properties));
        boolean[][] subKeys = DesAlgorithm.create16Subkeys(key);
        byte[] dataBytes = Files.readAllBytes(data.toPath());
        int complementaryBytes = getComplementaryBytesNumber(data);
        boolean[][] blocks = DesUtils.createBlocks(dataBytes);

        boolean[][] decryptedBlocks = new boolean[blocks.length][];
        for (int i = 0; i < blocks.length; i++) {
            decryptedBlocks[i] = DesAlgorithm.decryptBlock(blocks[i], subKeys);
        }

        return createFile(outputFilePath, toByteArrayLSBLeft(decryptedBlocks), complementaryBytes);
    }



    private int getComplementaryBytesNumber(File data) {
        int complementaryBitsNumber = 0;
        try {
            if (Files.exists(Paths.get(data.toPath().toString().concat(".compl")))) {
                List<String> content = Files.readAllLines(Paths.get(data.toPath().toString().concat(".compl")));
                if (content.size() != 1) {
                    logger.warn(".compl file is corrupted, decrypted data with 0 complement at end may be different than original");
                }
                else {
                    complementaryBitsNumber = Integer.parseInt(content.get(0));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.warn(".compl file is corrupted, decrypted data with 0 complement at end may be different than original");
        }
        return complementaryBitsNumber;
    }

    private File createFile(String outputFilePath, byte[] output, int complementaryBytes) throws IOException {
        logger.debug("Creating output file, output size = " + output.length);
        File outputFile = new File(outputFilePath);
        FileOutputStream stream = new FileOutputStream(outputFile);
        if (complementaryBytes != 0 ) {
            logger.debug("Decryption - writing without last " + complementaryBytes + " bytes because of incomplete last block when encrypted. Saving bytes: " + (output.length - complementaryBytes));
            int length = output.length - complementaryBytes;
            stream.write(output, 0, length);
        } else {
            stream.write(output);
        }
        stream.close();
        return outputFile;
    }

}
