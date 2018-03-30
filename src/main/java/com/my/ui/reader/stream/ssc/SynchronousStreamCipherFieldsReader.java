package com.my.ui.reader.stream.ssc;

import com.my.core.cryptography.stream.ssc.property.SynchronousStreamProperty;
import com.my.ui.reader.AlgorithmFieldsReader;

import java.awt.*;
import java.util.Map;
import java.util.Properties;

public class SynchronousStreamCipherFieldsReader implements AlgorithmFieldsReader {
    @Override
    public Properties read(Map<Label, TextField> labelTextFieldMap) {
        if (labelTextFieldMap.isEmpty()) throw new IllegalArgumentException();
        Properties properties = new Properties();
        for (Map.Entry<Label, TextField> labelTextFieldEntry : labelTextFieldMap.entrySet()) {
            SynchronousStreamProperty synchronousStreamProperty = SynchronousStreamProperty.valueOf(labelTextFieldEntry.getValue().getName());
            switch (synchronousStreamProperty) {
                case POLYNOMIAL: properties.setProperty(SynchronousStreamProperty.POLYNOMIAL.name(), labelTextFieldEntry.getValue().getText()); break;
                case SEED: properties.setProperty(SynchronousStreamProperty.SEED.name(), labelTextFieldEntry.getValue().getText()); break;
                case OUTPUT_FILE_PATH: properties.setProperty(SynchronousStreamProperty.OUTPUT_FILE_PATH.name(), labelTextFieldEntry.getValue().getText()); break;
            }
        }
        return properties;
    }
}
