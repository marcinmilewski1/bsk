package com.my.ui.creator.matrix.number;

import com.google.common.collect.Maps;
import com.my.core.cryptography.matrix.properties.NumberKeyMatrixTranspositionProperty;
import com.my.ui.creator.AlgorithmFieldsCreator;

import java.awt.*;
import java.util.Map;

public class NumberKeyMatrixTranspositionFieldsCreator implements AlgorithmFieldsCreator{
    @Override
    public Map<Label, TextField> getFields() {
        Map<Label, TextField> labelTextFieldMap = Maps.newHashMap();
        TextField numberKey = new TextField();
        numberKey.setName(NumberKeyMatrixTranspositionProperty.KEY.name());
        numberKey.setText("3,4,1,5,2");
        numberKey.setEditable(false);
        labelTextFieldMap.put(new Label("Key"), numberKey);
        return labelTextFieldMap;
    }
}
