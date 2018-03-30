package com.my.core.cryptography;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public interface Encryptor {
    String encrypt(String data, Properties properties);
    File encrypt(File data, Properties properties) throws IOException;
}
