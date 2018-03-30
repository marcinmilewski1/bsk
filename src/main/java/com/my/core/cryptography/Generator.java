package com.my.core.cryptography;

import java.util.Properties;

public interface Generator {
    boolean[] generate(Properties properties, int number);
}
