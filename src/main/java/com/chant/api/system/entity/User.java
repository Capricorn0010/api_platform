package com.chant.api.system.entity;

import com.chant.api.system.enums.RoleEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class User  {

    private String name;

    private String password;

    private RoleEnum role;

}
