package com.ali.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ali.SmartException;
import com.mysql.cj.util.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class IO {
    public IO() {
    }

    public static JSONObject getDbConfig(String configFile) {
        if (StringUtils.isNullOrEmpty(configFile))
            throw new SmartException(" env smartConfig file must not empty! ");
        return JSONUtil.readJSONObject(new File(configFile), StandardCharsets.UTF_8);
    }
}
