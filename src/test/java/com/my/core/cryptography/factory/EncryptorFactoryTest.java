package com.my.core.cryptography.factory;

import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.enums.Algorithm;
import com.my.core.cryptography.railfence.encryptor.RailFenceEncryptor;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EncryptorFactoryTest {
    @Test
    public void getEncryptor() throws Exception {
        Encryptor encryptor = EncryptorFactory.getEncryptor(Algorithm.RAIL_FENCE);
        assertTrue(encryptor instanceof RailFenceEncryptor);
    }

}