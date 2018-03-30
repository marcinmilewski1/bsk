package com.my.core.cryptography;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public interface Decryptor {
    String decrypt(String data, Properties properties);
    File decrypt(File data, Properties properties) throws IOException;
}
