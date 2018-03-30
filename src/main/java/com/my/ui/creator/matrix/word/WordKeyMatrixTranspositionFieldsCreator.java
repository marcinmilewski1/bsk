package com.my.ui.creator.matrix.word;

import com.google.common.collect.Maps;
import com.my.core.cryptography.matrix.word.properties.WordKeyMatrixTranspositionProperty;
import com.my.ui.creator.AlgorithmFieldsCreator;

import java.awt.*;
import java.util.Map;

public class WordKeyMatrixTranspositionFieldsCreator implements AlgorithmFieldsCreator {
    @Override
    public Map<Label, TextField> getFields() {
        Map<Label, TextField> labelTextFieldMap = Maps.newHashMap();
        TextField wordKey = new TextField();
        wordKey.setName(WordKeyMatrixTranspositionProperty.KEY_WORD.name());
        wordKey.setText("convenience");
        wordKey.setEditable(true);
        labelTextFieldMap.put(new Label("Key"), wordKey);
        return labelTextFieldMap;
    }
}
