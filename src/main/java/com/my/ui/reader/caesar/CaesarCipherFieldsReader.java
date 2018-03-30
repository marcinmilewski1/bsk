package com.my.ui.reader.caesar;

import com.my.core.cryptography.caesar.property.CaesarCipherProperty;
import com.my.ui.reader.AlgorithmFieldsReader;

import java.awt.*;
import java.util.Map;
import java.util.Properties;

public class CaesarCipherFieldsReader implements AlgorithmFieldsReader {
    @Override
    public Properties read(Map<Label, TextField> labelTextFieldMap) {
        if (labelTextFieldMap.isEmpty()) throw new IllegalArgumentException();
        Properties properties = new Properties();
        for (Map.Entry<Label, TextField> labelTextFieldEntry : labelTextFieldMap.entrySet()) {
            CaesarCipherProperty caesarCipherProperty = CaesarCipherProperty.valueOf(labelTextFieldEntry.getValue().getName());
            switch (caesarCipherProperty) {
                case K0: properties.setProperty(CaesarCipherProperty.K0.name(), labelTextFieldEntry.getValue().getText()); break;
                case K1: properties.setProperty(CaesarCipherProperty.K1.name(), labelTextFieldEntry.getValue().getText()); break;
                case N: properties.setProperty(CaesarCipherProperty.N.name(), labelTextFieldEntry.getValue().getText()); break;
            }
        }
        return properties;
    }
}
