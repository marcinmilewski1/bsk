package com.my.ui.creator.railfence;

import com.google.common.collect.Maps;
import com.my.core.cryptography.railfence.properties.RailfenceProperty;
import com.my.ui.creator.AlgorithmFieldsCreator;

import java.awt.*;
import java.util.Map;

public class RailFenceFieldsCreator implements AlgorithmFieldsCreator {
    @Override
    public Map<Label, TextField> getFields() {
        Map<Label,TextField> labelToFieldMap =Maps.newHashMap();
        TextField depth = new TextField();
        depth.setName(RailfenceProperty.DEPTH.name());
        depth.setText("3");
        labelToFieldMap.put(new Label("Depth"), depth);
        return labelToFieldMap;
    }
}
