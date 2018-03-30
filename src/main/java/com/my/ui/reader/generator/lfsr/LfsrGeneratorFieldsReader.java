package com.my.ui.reader.generator.lfsr;

import com.my.core.cryptography.generator.stream.property.LfsrGeneratorProperty;
import com.my.ui.reader.AlgorithmFieldsReader;

import java.awt.*;
import java.util.Map;
import java.util.Properties;

public class LfsrGeneratorFieldsReader implements AlgorithmFieldsReader{
    @Override
    public Properties read(Map<Label, TextField> labelTextFieldMap) {
        if (labelTextFieldMap.isEmpty()) throw new IllegalArgumentException();
        Properties properties = new Properties();
        for (Map.Entry<Label, TextField> labelTextFieldEntry : labelTextFieldMap.entrySet()) {
            LfsrGeneratorProperty lfsrGeneratorProperty = LfsrGeneratorProperty.valueOf(labelTextFieldEntry.getValue().getName());
            switch (lfsrGeneratorProperty) {
                case POLYNOMIAL: properties.setProperty(LfsrGeneratorProperty.POLYNOMIAL.name(), labelTextFieldEntry.getValue().getText()); break;
                case SEED: properties.setProperty(LfsrGeneratorProperty.SEED.name(), labelTextFieldEntry.getValue().getText()); break;
            }
        }
        return properties;
    }
}
