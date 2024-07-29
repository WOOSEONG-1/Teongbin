package com.ssafy.teongbin.trash.dto.response;

import com.ssafy.teongbin.log.entity.Restlog;
import com.ssafy.teongbin.trash.entity.Trashcan;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserLogDto {
    private Long trashcanId;
    private List<RestDto> restPercentList; // Restlog의 rest_percent 정보

    public UserLogDto(Trashcan trashcan) {
        this.trashcanId = trashcan.getId();
        this.restPercentList = trashcan.getRestlogs().stream()
                .map(RestDto::new)
                .collect(Collectors.toList());
        //잔량 리스트가 비어있을 경우 0을 하나 넣어주는 코드입니다.
        if (this.restPercentList.isEmpty()) {
            this.restPercentList.add(new RestDto(0));
        }
    }

    @Data
    static class RestDto {

        public RestDto(Restlog restlog) {
            this.restPercent = restlog.getRestPercent();
        }
        public int restPercent;

        public RestDto(int restPercent) {
            this.restPercent = restPercent;
        }
    }
}
