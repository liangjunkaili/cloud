package com.liangjun.servicemiya.DO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private Integer id;

    private String name;

    private String password;

    private String phone;
}
