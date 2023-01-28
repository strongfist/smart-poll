package com.ali;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ali.util.IO;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;


public class SmartConfig {
    private final static Logger log = LoggerFactory.getLogger(SmartConfig.class);
    private String configFile;
    private String driverClassName;
    private String password;
    private String username;
    private String url;

    public SmartConfig(String configFile) {
        JSONObject dbConfig = IO.getDbConfig(configFile);
        configureThis(dbConfig);
    }

    private void configureThis(JSONObject dbConfig) {
        log.info("connection pool initializing begin");
        this.url = dbConfig.getStr("url");
        this.username = dbConfig.getStr("username");
        this.password = dbConfig.getStr("password");
        this.driverClassName = dbConfig.getStr("driverClassName");
    }

}
