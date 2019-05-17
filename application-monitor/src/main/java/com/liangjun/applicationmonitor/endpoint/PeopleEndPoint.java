package com.liangjun.applicationmonitor.endpoint;

import com.liangjun.applicationmonitor.dto.People;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Endpoint(id = "people")
@Component
public class PeopleEndPoint {
    private final Map<String, People> peopleMap = new HashMap<>();
    PeopleEndPoint(){
        this.peopleMap.put("1",People.builder().name("1111").build());
        this.peopleMap.put("2",People.builder().name("2222").build());
        this.peopleMap.put("3",People.builder().name("3333").build());
    }
    @ReadOperation
    public List<People> getAll(){
        return new ArrayList<>(this.peopleMap.values());
    }
    @ReadOperation
    public People getPeople(@Selector String name){
        return this.peopleMap.get(name);
    }
    @WriteOperation
    public void updatePeople(@Selector String name,String fullName){
        this.peopleMap.put(name,People.builder().name(fullName).build());
    }
}
