package com.my.core.cryptography.stream.autokey.decryptor;

import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.generator.stream.lfsr.StatefulLfsrGenerator;
import com.my.core.cryptography.stream.ssc.property.SynchronousStreamProperty;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.BitSet;
import java.util.Properties;

import static com.my.core.cryptography.generator.stream.util.BinaryUtils.*;

public class CiphertextAutoKeyDecryptor implements Decryptor {
    private static Logger logger = Logger.getLogger(CiphertextAutoKeyDecryptor.class);

    private StatefulLfsrGenerator statefulLfsrGenerator;


    @Override
    public String decrypt(String data, Properties properties) {
        return null;
    }

    @Override
    public File decrypt(File data, Properties properties) throws IOException {
        logger.debug("Start decryption");
        String polynomialString = properties.getProperty(SynchronousStreamProperty.POLYNOMIAL.name());
        String generatorStateString = properties.getProperty(SynchronousStreamProperty.SEED.name());
        String outputFilePath = properties.getProperty(SynchronousStreamProperty.OUTPUT_FILE_PATH.name());

        if (polynomialString == null || polynomialString.isEmpty()) throw new IllegalArgumentException();
        if (generatorStateString == null || generatorStateString.length() != polynomialString.length())
            throw new IllegalArgumentException();
        if (outputFilePath == null || outputFilePath.isEmpty())
            throw new IllegalArgumentException("Output file path is null");

        statefulLfsrGenerator = new StatefulLfsrGenerator(getBooleanArray(polynomialString), getBooleanArray(generatorStateString));

        byte[] dataBytes = Files.readAllBytes(data.toPath());
        logger.debug("Input data size = " + dataBytes.length);

        BitSet dataBitSet = BitSet.valueOf(dataBytes);
        boolean[] outputBitArray = new boolean[dataBytes.length * 8];

        for (int i = 0; i < dataBytes.length * 8; i++) {
            boolean generatedBit = statefulLfsrGenerator.generateNext();
            boolean xoredBit = xor(dataBitSet.get(i), generatedBit);
            statefulLfsrGenerator.shiftRightNoCarry();
            statefulLfsrGenerator.setFirstStateBit(dataBitSet.get(i));
            outputBitArray[i] = xoredBit;
        }
        return createFile(outputFilePath, toByteArrayLSBRight(outputBitArray));
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
