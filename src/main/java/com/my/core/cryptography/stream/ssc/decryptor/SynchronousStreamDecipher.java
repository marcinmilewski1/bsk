package com.my.core.cryptography.stream.ssc.decryptor;

import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.stream.ssc.encryptor.SynchronousStreamCipher;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class SynchronousStreamDecipher implements Decryptor {
    @Override
    public String decrypt(String data, Properties properties) {
        throw new UnsupportedOperationException();
    }

    @Override
    public File decrypt(File data, Properties properties) throws IOException {
        SynchronousStreamCipher synchronousStreamCipher = new SynchronousStreamCipher();
        return synchronousStreamCipher.encrypt(data, properties);
    }
}
