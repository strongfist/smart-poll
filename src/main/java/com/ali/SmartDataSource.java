package com.ali;

import cn.hutool.extra.expression.engine.rhino.RhinoEngine;
import cn.hutool.json.JSONObject;
import com.ali.util.IO;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SmartDataSource implements DataSource, Closeable {
    private final static org.slf4j.Logger log = LoggerFactory.getLogger(SmartConfig.class);
    private SmartConfig smartConfig;
    private DBConnectionPool connectionPool;

    public SmartDataSource(SmartConfig smartConfig) {

        this.connectionPool = defaultGetConnectionPool();
        this.smartConfig = smartConfig;
    }

    private DBConnectionPool defaultGetConnectionPool() {
        return new DBConnectionPool(new ThreadPoolExecutor(2,
                8,
                1l,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>()));
    }

    public SmartDataSource(String smartConfig) {
        System.out.println();
        JSONObject dbConfig = IO.getDbConfig(smartConfig);
        JSONObject poolConfig = dbConfig.getJSONObject("poolConfig");
        makePool(poolConfig);
        this.smartConfig = new SmartConfig(smartConfig);
    }

    private void makePool(JSONObject poolConfig) {
        log.info("begin pool init ....");
        LinkedBlockingQueue<Runnable> addThreadQue = new LinkedBlockingQueue<>();
        this.connectionPool = new DBConnectionPool(
                new ThreadPoolExecutor(
                        poolConfig.getInt("minIdle", 2),
                        poolConfig.getInt("maxIdle", 8),
                        1l,
                        TimeUnit.MINUTES,
                        addThreadQue));
    }

    @Override
    public void close() throws IOException {
        // TODO: 2023/1/28
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.connectionPool.getConnectionInstance();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;}

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
