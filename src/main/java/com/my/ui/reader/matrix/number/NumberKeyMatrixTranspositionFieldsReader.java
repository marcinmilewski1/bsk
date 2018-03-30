package com.my.ui.reader.matrix.number;

import com.my.core.cryptography.matrix.properties.NumberKeyMatrixTranspositionProperty;
import com.my.ui.reader.AlgorithmFieldsReader;

import java.awt.*;
import java.util.Map;
import java.util.Properties;

public class NumberKeyMatrixTranspositionFieldsReader implements AlgorithmFieldsReader {

    @Override
    public Properties read(Map<Label, TextField> labelTextFieldMap) {
        if (labelTextFieldMap.isEmpty()) throw new IllegalArgumentException();
        Properties properties = new Properties();
        for (Map.Entry<Label, TextField> labelTextFieldEntry : labelTextFieldMap.entrySet()) {
            NumberKeyMatrixTranspositionProperty numberKeyMatrixTranspositionProperty = NumberKeyMatrixTranspositionProperty.valueOf(labelTextFieldEntry.getValue().getName());
            switch (numberKeyMatrixTranspositionProperty) {
                case KEY: properties.setProperty(NumberKeyMatrixTranspositionProperty.KEY.name(), labelTextFieldEntry.getValue().getText()); break;
            }
        }
        return properties;    }
}
