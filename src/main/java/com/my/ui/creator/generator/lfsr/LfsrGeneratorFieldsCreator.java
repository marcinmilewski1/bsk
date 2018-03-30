package com.my.ui.creator.generator.lfsr;

import com.google.common.collect.Maps;
import com.my.core.cryptography.generator.stream.property.LfsrGeneratorProperty;
import com.my.ui.creator.AlgorithmFieldsCreator;

import java.awt.*;
import java.util.Map;

public class LfsrGeneratorFieldsCreator implements AlgorithmFieldsCreator {
    @Override
    public Map<Label, TextField> getFields() {
        Map<Label, TextField> labelTextFieldMap = Maps.newHashMap();

        TextField polynomial = new TextField();
        polynomial.setName(LfsrGeneratorProperty.POLYNOMIAL.name());
        polynomial.setText("1001");
        labelTextFieldMap.put(new Label("Polynomial"), polynomial);

        TextField seed = new TextField();
        seed.setName(LfsrGeneratorProperty.SEED.name());
        seed.setText("1010");
        labelTextFieldMap.put(new Label("Seed"), seed);

        return labelTextFieldMap;
    }
}
