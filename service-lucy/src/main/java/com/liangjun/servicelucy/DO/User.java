package com.liangjun.servicelucy.DO;

import lombok.*;

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
