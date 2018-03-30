package com.my.core.cryptography.factory;

import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.caesar.decryptor.CaesarCipherDecryptor;
import com.my.core.cryptography.des.DesDecryptor;
import com.my.core.cryptography.enums.Algorithm;
import com.my.core.cryptography.matrix.number.decryptor.NumberKeyMatrixTranspositionDecryptor;
import com.my.core.cryptography.matrix.word.decryptor.WordKeyColumnVariantMatrixTranspositionDecryptor;
import com.my.core.cryptography.matrix.word.decryptor.WordKeyMatrixTranspositionDecryptor;
import com.my.core.cryptography.railfence.decryptor.RailFenceDecryptor;
import com.my.core.cryptography.stream.autokey.decryptor.CiphertextAutoKeyDecryptor;
import com.my.core.cryptography.stream.ssc.decryptor.SynchronousStreamDecipher;
import com.my.core.cryptography.vigenere.decryptor.VigenereCipherDecryptor;

public class DecryptorFactory {
    private DecryptorFactory() {
    }

    public static Decryptor getDecryptor(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceDecryptor();
            case NUMBER_KEY_MATRIX_TRANSPOSITION: return new NumberKeyMatrixTranspositionDecryptor();
            case WORD_KEY_MATRIX_TRANSPOSITION: return new WordKeyMatrixTranspositionDecryptor();
            case WORD_KEY_COLUMN_VARIANT_MATRIX_TRANSPOSITION: return new WordKeyColumnVariantMatrixTranspositionDecryptor();
            case CAESAR_CIPHER: return new CaesarCipherDecryptor();
            case VIGENERE_CIPHER: return new VigenereCipherDecryptor();
            case SYNCHRONOUS_STREAM: return new SynchronousStreamDecipher();
            case CIPHERTEXT_AUTOKEY: return new CiphertextAutoKeyDecryptor();
            case DES: return new DesDecryptor();
            default: throw new IllegalArgumentException();
        }
    }
}
