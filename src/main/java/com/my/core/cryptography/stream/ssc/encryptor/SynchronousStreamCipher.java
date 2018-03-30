package com.my.core.cryptography.stream.ssc.encryptor;

import com.my.core.cryptography.Encryptor;
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

public class SynchronousStreamCipher implements Encryptor {
    private StatefulLfsrGenerator lfsrGenerator;
    private static Logger logger = Logger.getLogger(SynchronousStreamCipher.class);

    @Override
    public String encrypt(String data, Properties properties) {
        throw new UnsupportedOperationException();
    }
        //0011101100111000100110000011011100010101001000001111011101011110
    @Override
    public File encrypt(File data, Properties properties) throws IOException {
        logger.debug("Start encryption");
        String polynomialString = properties.getProperty(SynchronousStreamProperty.POLYNOMIAL.name());
        String seedString = properties.getProperty(SynchronousStreamProperty.SEED.name());
        String outputFilePath = properties.getProperty(SynchronousStreamProperty.OUTPUT_FILE_PATH.name());

        if (polynomialString == null || polynomialString.isEmpty()) throw new IllegalArgumentException();
        if (seedString == null || seedString.length() != polynomialString.length())
            throw new IllegalArgumentException();
        if (outputFilePath == null || outputFilePath.isEmpty()) throw new IllegalArgumentException("Output file path is null");

        lfsrGenerator = new StatefulLfsrGenerator(getBooleanArray(polynomialString), getBooleanArray(seedString));

        byte[] dataBytes = Files.readAllBytes(data.toPath());
        logger.debug("Input data size = " + dataBytes.length);

        BitSet dataBitSet = BitSet.valueOf(dataBytes);
        boolean[] outputBitArray = new boolean[dataBytes.length * 8];

        for (int i = 0; i < dataBytes.length * 8; i++) {
            boolean generatedBit = lfsrGenerator.generateNext();
            lfsrGenerator.shiftRightNoCarry();
            lfsrGenerator.setFirstStateBit(generatedBit);
            outputBitArray[i] = xor(generatedBit, dataBitSet.get(i));
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
