package com.liangjun.servicemiya.config;

public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();
    public static void setDataBaseType(DatabaseType type){
        contextHolder.set(type);
    }
    public static DatabaseType getDataBaseType(){
        return contextHolder.get();
    }
}
