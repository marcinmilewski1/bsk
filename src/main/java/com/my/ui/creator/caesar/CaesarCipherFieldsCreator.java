package com.my.ui.creator.caesar;

import com.google.common.collect.Maps;
import com.my.core.cryptography.caesar.property.CaesarCipherProperty;
import com.my.ui.creator.AlgorithmFieldsCreator;

import java.awt.*;
import java.util.Map;

public class CaesarCipherFieldsCreator implements AlgorithmFieldsCreator {
    @Override
    public Map<Label, TextField> getFields() {
        Map<Label, TextField> labelTextFieldMap = Maps.newHashMap();
        TextField k0 = new TextField();
        k0.setName(CaesarCipherProperty.K0.name());
        k0.setText("4");
        labelTextFieldMap.put(new Label("K0"), k0);

        TextField k1 = new TextField();
        k1.setName(CaesarCipherProperty.K1.name());
        k1.setText("5");
        labelTextFieldMap.put(new Label("K1"), k1);

        TextField n = new TextField();
        n.setName(CaesarCipherProperty.N.name());
        n.setText("21");
        labelTextFieldMap.put(new Label("N"), n);
        return labelTextFieldMap;
    }
}
