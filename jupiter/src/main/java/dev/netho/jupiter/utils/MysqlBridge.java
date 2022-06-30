package dev.netho.jupiter.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlBridge {

    private static int MAX_CONNECTIONS=10;

    private static String URL_DB = "//wagnerweinert.com.br:3306/";
    private static String DB_NAME = "info21_angelo";
    private static String USERNAME = "info21_angelo";
    private static String PASSWORD = "Comprar123!";

    private static String CON_STRING="jdbc:mysql:"+URL_DB+DB_NAME;

    private Connection[] connections;

    private static MysqlBridge instance;

    private MysqlBridge() {
        connections = new Connection[MAX_CONNECTIONS];
    }

    public static MysqlBridge getInstance() {
        if(instance != null) {
            return instance;
        }
        instance = new MysqlBridge();
        return instance;
    }

    public Connection getConnection() throws SQLException {

        for(int i=0;i<MAX_CONNECTIONS;i++) {
            if(instance.connections[i]==null || instance.connections[i].isClosed()) {
                instance.connections[i] = DriverManager.getConnection(CON_STRING,USERNAME,PASSWORD);
                return instance.connections[i];
            }
        }
        throw new SQLException("Max connections");

    }


}
