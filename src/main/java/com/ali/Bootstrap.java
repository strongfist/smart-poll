package com.ali;

import cn.hutool.core.getter.OptNullBasicTypeFromStringGetter;

public class Bootstrap {
    public static void main(String[] args) {
        String fileLocation = "/Users/almasrixat/Desktop/try/smart-pool/src/main/resources/smart-config.json";
        SmartConfig smartConfig = new SmartConfig(fileLocation);
        SmartDataSource smartDataSource = new SmartDataSource(smartConfig);
        SmartDataSource smartDataSource1 = new SmartDataSource(fileLocation);


    }
}
