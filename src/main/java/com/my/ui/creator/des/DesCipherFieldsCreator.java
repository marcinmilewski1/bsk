package com.my.ui.creator.des;

import com.google.common.collect.Maps;
import com.my.core.cryptography.des.DesProperty;
import com.my.core.cryptography.stream.ssc.property.SynchronousStreamProperty;
import com.my.ui.creator.AlgorithmFieldsCreator;

import java.awt.*;
import java.util.Map;

public class DesCipherFieldsCreator implements AlgorithmFieldsCreator {
    @Override
    public Map<Label, TextField> getFields() {
        Map<Label, TextField> labelTextFieldMap = Maps.newHashMap();
        Font font1 = new Font("SansSerif", Font.PLAIN, 11);

        TextField key = new TextField();
        key.setName(DesProperty.KEY.name());
        key.setText("00111011 00111000 10011000 00110111 00010101 00100000 11110111 01011110");
        key.setMaximumSize(new Dimension(500, 40));
        key.setPreferredSize(new Dimension(500, 40));
        key.setFont(font1);
        labelTextFieldMap.put(new Label("Key"), key);

        TextField outputPath = new TextField();
        outputPath.setName(SynchronousStreamProperty.OUTPUT_FILE_PATH.name());
        outputPath.setText("/home/marcin/output");
//        outputPath.setMinimumSize(new Dimension(200, 40));
//        outputPath.setPreferredSize(new Dimension(200, 40));
        labelTextFieldMap.put(new Label("Output path"), outputPath);

        return labelTextFieldMap;
    }
}
