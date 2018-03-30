package com.my.ui.reader.factory;

import com.my.core.cryptography.enums.Algorithm;
import com.my.ui.reader.AlgorithmFieldsReader;
import com.my.ui.reader.caesar.CaesarCipherFieldsReader;
import com.my.ui.reader.des.DesAlgorithmFieldsReader;
import com.my.ui.reader.generator.lfsr.LfsrGeneratorFieldsReader;
import com.my.ui.reader.matrix.number.NumberKeyMatrixTranspositionFieldsReader;
import com.my.ui.reader.matrix.word.WordKeyColumnVariantMatrixTranspositionFieldsReader;
import com.my.ui.reader.matrix.word.WordKeyMatrixTranspositionFieldsReader;
import com.my.ui.reader.railfence.RailFenceFieldsReader;
import com.my.ui.reader.stream.autokey.CiphertextAutoKeyFieldsReader;
import com.my.ui.reader.stream.ssc.SynchronousStreamCipherFieldsReader;
import com.my.ui.reader.vigenere.VigenereCipherFieldsReader;

public class AlgorithmFieldsReaderFactory {
    private AlgorithmFieldsReaderFactory() {
    }

    public static AlgorithmFieldsReader getAlgorithmFieldsReader(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceFieldsReader();
            case NUMBER_KEY_MATRIX_TRANSPOSITION: return new NumberKeyMatrixTranspositionFieldsReader();
            case WORD_KEY_MATRIX_TRANSPOSITION: return new WordKeyMatrixTranspositionFieldsReader();
            case WORD_KEY_COLUMN_VARIANT_MATRIX_TRANSPOSITION: return new WordKeyColumnVariantMatrixTranspositionFieldsReader();
            case CAESAR_CIPHER: return new CaesarCipherFieldsReader();
            case VIGENERE_CIPHER: return new VigenereCipherFieldsReader();
            case LFSR_GENERATOR: return new LfsrGeneratorFieldsReader();
            case SYNCHRONOUS_STREAM: return new SynchronousStreamCipherFieldsReader();
            case CIPHERTEXT_AUTOKEY: return new CiphertextAutoKeyFieldsReader();
            case DES:return new DesAlgorithmFieldsReader();
            default: throw new IllegalArgumentException();
        }
    }
}
