package com.my.ui.reader.des;

import com.my.core.cryptography.des.DesProperty;
import com.my.core.cryptography.stream.ssc.property.SynchronousStreamProperty;
import com.my.ui.reader.AlgorithmFieldsReader;

import java.awt.*;
import java.util.Map;
import java.util.Properties;

public class DesAlgorithmFieldsReader implements AlgorithmFieldsReader {
    @Override
    public Properties read(Map<Label, TextField> labelTextFieldMap) {
        if (labelTextFieldMap.isEmpty()) throw new IllegalArgumentException();
        Properties properties = new Properties();
        for (Map.Entry<Label, TextField> labelTextFieldEntry : labelTextFieldMap.entrySet()) {
            DesProperty desProperty = DesProperty.valueOf(labelTextFieldEntry.getValue().getName());
            switch (desProperty) {
                case OUTPUT_FILE_PATH: properties.setProperty(DesProperty.OUTPUT_FILE_PATH.name(), labelTextFieldEntry.getValue().getText()); break;
                case KEY: properties.setProperty(DesProperty.KEY.name(), labelTextFieldEntry.getValue().getText()); break;
            }
        }
        return properties;
    }
}
