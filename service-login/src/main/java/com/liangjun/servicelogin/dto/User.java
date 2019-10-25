package com.liangjun.servicelogin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private BigInteger id;

    @NotBlank(message = "名称不能为空")
    private String name;
    @NotBlank(message = "名称不能为空")
    private String password;
    @Pattern(regexp = "[0-9]{11}")
    private String phone;

    private Boolean deleted;

    private String gmtCreate;

    private String gmtModified;

    private BigDecimal score;
}
