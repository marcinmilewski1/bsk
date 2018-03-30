package com.my.ui.reader.vigenere;

import com.my.core.cryptography.vigenere.property.VigenereCipherProperty;
import com.my.ui.reader.AlgorithmFieldsReader;

import java.awt.*;
import java.util.Map;
import java.util.Properties;


public class VigenereCipherFieldsReader implements AlgorithmFieldsReader {
    @Override
    public Properties read(Map<Label, TextField> labelTextFieldMap) {
        if (labelTextFieldMap.isEmpty()) throw new IllegalArgumentException();
        Properties properties = new Properties();
        for (Map.Entry<Label, TextField> labelTextFieldEntry : labelTextFieldMap.entrySet()) {
            VigenereCipherProperty vigenereCipherProperty = VigenereCipherProperty.KEY_WORD.valueOf(labelTextFieldEntry.getValue().getName());
            switch (vigenereCipherProperty) {
                case KEY_WORD: properties.setProperty(VigenereCipherProperty.KEY_WORD.name(), labelTextFieldEntry.getValue().getText()); break;
            }
        }
        return properties;
    }
}
