package com.ali;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.util.concurrent.ThreadPoolExecutor;

@Data
@NoArgsConstructor
public class DBConnectionPool {
    private int maxIdleTime;
    private int minIdle;
    private ThreadPoolExecutor getConnectionThreadPool;

    public DBConnectionPool(ThreadPoolExecutor getConnectionThreadPool) {
        this.getConnectionThreadPool = getConnectionThreadPool;
    }

    // TODO: 2023/1/28 implement get connection 
    public Connection getConnectionInstance() {
        return null;
    }
}
