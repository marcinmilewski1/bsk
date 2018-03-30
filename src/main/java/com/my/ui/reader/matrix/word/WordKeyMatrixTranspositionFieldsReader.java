package com.my.ui.reader.matrix.word;

import com.my.core.cryptography.matrix.word.properties.WordKeyMatrixTranspositionProperty;
import com.my.ui.reader.AlgorithmFieldsReader;

import java.awt.*;
import java.util.Map;
import java.util.Properties;

public class WordKeyMatrixTranspositionFieldsReader implements AlgorithmFieldsReader {
    @Override
    public Properties read(Map<Label, TextField> labelTextFieldMap) {
        if (labelTextFieldMap.isEmpty()) throw new IllegalArgumentException();
        Properties properties = new Properties();
        for (Map.Entry<Label, TextField> labelTextFieldEntry : labelTextFieldMap.entrySet()) {
            WordKeyMatrixTranspositionProperty item = WordKeyMatrixTranspositionProperty.valueOf(labelTextFieldEntry.getValue().getName());
            switch (item) {
                case KEY_WORD: properties.setProperty(WordKeyMatrixTranspositionProperty.KEY_WORD.name(), labelTextFieldEntry.getValue().getText()); break;
            }
        }
        return properties;    }
}
