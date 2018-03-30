package com.my.ui.creator.vigenere;

import com.google.common.collect.Maps;
import com.my.core.cryptography.vigenere.property.VigenereCipherProperty;
import com.my.ui.creator.AlgorithmFieldsCreator;

import java.awt.*;
import java.util.Map;

public class VigenereCipherFieldsCreator implements AlgorithmFieldsCreator {
    @Override
    public Map<Label, TextField> getFields() {
        Map<Label, TextField> labelTextFieldMap = Maps.newHashMap();
        TextField k0 = new TextField();
        k0.setName(VigenereCipherProperty.KEY_WORD.name());
        k0.setText("break");
        labelTextFieldMap.put(new Label("Key word"), k0);

        return labelTextFieldMap;
    }
}
