package com.my.ui.creator.factory;

import com.my.core.cryptography.enums.Algorithm;
import com.my.ui.creator.AlgorithmFieldsCreator;
import com.my.ui.creator.caesar.CaesarCipherFieldsCreator;
import com.my.ui.creator.des.DesCipherFieldsCreator;
import com.my.ui.creator.generator.lfsr.LfsrGeneratorFieldsCreator;
import com.my.ui.creator.matrix.number.NumberKeyMatrixTranspositionFieldsCreator;
import com.my.ui.creator.matrix.word.WordKeyColumnVariantMatrixTranspositionFieldsCreator;
import com.my.ui.creator.matrix.word.WordKeyMatrixTranspositionFieldsCreator;
import com.my.ui.creator.railfence.RailFenceFieldsCreator;
import com.my.ui.creator.stream.autokey.CiphertextAutoKeyFieldsCreator;
import com.my.ui.creator.stream.ssc.SynchronousStreamCipherFieldsCreator;
import com.my.ui.creator.vigenere.VigenereCipherFieldsCreator;

public class AlgorithmFieldsCreatorFactory {
    private AlgorithmFieldsCreatorFactory() {
    }

    public static AlgorithmFieldsCreator getCreator(Algorithm algorithm) {
        switch (algorithm) {
            case RAIL_FENCE: return new RailFenceFieldsCreator();
            case NUMBER_KEY_MATRIX_TRANSPOSITION: return new NumberKeyMatrixTranspositionFieldsCreator();
            case WORD_KEY_MATRIX_TRANSPOSITION: return new WordKeyMatrixTranspositionFieldsCreator();
            case WORD_KEY_COLUMN_VARIANT_MATRIX_TRANSPOSITION: return new WordKeyColumnVariantMatrixTranspositionFieldsCreator();
            case CAESAR_CIPHER: return new CaesarCipherFieldsCreator();
            case VIGENERE_CIPHER: return new VigenereCipherFieldsCreator();
            case LFSR_GENERATOR: return new LfsrGeneratorFieldsCreator();
            case SYNCHRONOUS_STREAM: return new SynchronousStreamCipherFieldsCreator();
            case CIPHERTEXT_AUTOKEY: return new CiphertextAutoKeyFieldsCreator();
            case DES: return new DesCipherFieldsCreator();
            default: throw new IllegalArgumentException();
        }
    }
}
