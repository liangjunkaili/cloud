package com.liangjun.servicemiya.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public enum DatabaseType {
    master("write"),slave("read");
    DatabaseType(String name){
        this.name = name;
    }
    @Getter
    @Setter
    private String name;
}
