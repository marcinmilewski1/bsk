package com.my.ui.reader.railfence;

import com.my.core.cryptography.railfence.properties.RailfenceProperty;
import com.my.ui.reader.AlgorithmFieldsReader;

import java.awt.*;
import java.util.Map;
import java.util.Properties;

public class RailFenceFieldsReader implements AlgorithmFieldsReader {

    @Override
    public Properties read(Map<Label, TextField> labelTextFieldMap) {
        if (labelTextFieldMap.isEmpty()) throw new IllegalArgumentException();
        Properties properties = new Properties();
        for (Map.Entry<Label, TextField> labelTextFieldEntry : labelTextFieldMap.entrySet()) {
            RailfenceProperty railFencePropertyItem = RailfenceProperty.valueOf(labelTextFieldEntry.getValue().getName());
            switch (railFencePropertyItem) {
                case DEPTH: properties.setProperty(RailfenceProperty.DEPTH.name(), labelTextFieldEntry.getValue().getText()); break;
            }
        }
        return properties;
    }
}
