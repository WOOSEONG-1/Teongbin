package com.ssafy.teongbin.trash.dto.response;

import com.ssafy.teongbin.log.entity.Restlog;
import com.ssafy.teongbin.trash.entity.Trashcan;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTrashcanRestDtoV2 {
    private Long trashcanId;
    private int restPercent;
}
