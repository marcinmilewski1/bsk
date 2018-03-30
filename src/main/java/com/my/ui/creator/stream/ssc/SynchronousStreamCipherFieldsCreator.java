package com.my.ui.creator.stream.ssc;

import com.google.common.collect.Maps;
import com.my.core.cryptography.stream.ssc.property.SynchronousStreamProperty;
import com.my.ui.creator.AlgorithmFieldsCreator;

import java.awt.*;
import java.util.Map;

public class SynchronousStreamCipherFieldsCreator implements AlgorithmFieldsCreator {
    @Override
    public Map<Label, TextField> getFields() {
        Map<Label, TextField> labelTextFieldMap = Maps.newHashMap();

        TextField polynomial = new TextField();
        polynomial.setName(SynchronousStreamProperty.POLYNOMIAL.name());
        polynomial.setText("1001");
        labelTextFieldMap.put(new Label("Polynomial"), polynomial);

        TextField seed = new TextField();
        seed.setName(SynchronousStreamProperty.SEED.name());
        seed.setText("1010");
        labelTextFieldMap.put(new Label("Seed"), seed);

        TextField outputPath = new TextField();
        outputPath.setName(SynchronousStreamProperty.OUTPUT_FILE_PATH.name());
        outputPath.setText("/home/marcin/output");
        labelTextFieldMap.put(new Label("Output path"), outputPath);

        return labelTextFieldMap;
    }
}
