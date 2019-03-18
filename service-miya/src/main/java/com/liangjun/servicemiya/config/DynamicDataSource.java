package com.liangjun.servicemiya.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class DynamicDataSource extends AbstractRoutingDataSource {

    static final Map<DatabaseType, List<String>> METHOD_TYPE_MAP = new HashMap<>();
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        DatabaseType type = DatabaseContextHolder.getDataBaseType();
        log.info("=========datasource======"+type);
        return type;
    }
    void setMethodTypeMap(DatabaseType typeMap,String content){
        List<String> list = Arrays.asList(content.split(","));
        METHOD_TYPE_MAP.put(typeMap,list);
    }
}
