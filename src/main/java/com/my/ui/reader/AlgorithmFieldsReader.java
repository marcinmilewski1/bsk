package com.my.ui.reader;

import java.awt.*;
import java.util.Map;
import java.util.Properties;

public interface AlgorithmFieldsReader {
    Properties read(Map<Label, TextField> labelTextFieldMap);

}
