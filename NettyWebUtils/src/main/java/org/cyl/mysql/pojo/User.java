package org.cyl.mysql.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class User {
    private Long id;
    private String username;
    private String password;
}
