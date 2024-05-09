package com.example.smsbackspringboot.demos.vo.param;

import com.example.smsbackspringboot.demos.entiy.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStaffParam extends Staff {
    private Long roleId;
}
