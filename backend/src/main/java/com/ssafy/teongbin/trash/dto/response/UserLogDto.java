package com.ssafy.teongbin.trash.dto.response;

import com.ssafy.teongbin.log.entity.Restlog;
import com.ssafy.teongbin.trash.entity.Trashcan;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserLogDto {
    private Long trashcanId;
    private List<Integer> restPercentList; // Restlog의 rest_percent 정보

    public UserLogDto(Trashcan trashcan) {
        this.trashcanId = trashcan.getId();
        this.restPercentList = trashcan.getRestlogs().stream()
                .map(Restlog::getRestPercent)
                .collect(Collectors.toList());
    }
}
