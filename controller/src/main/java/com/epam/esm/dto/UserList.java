package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserList {

    private List<UserDto> items;
    private int usersCount;

    public UserList(List<UserDto> items) {
        this.items = items;
    }

    public UserList(List<UserDto> items, int usersCount) {
        this.items = items;
        this.usersCount = usersCount;
    }
}
