package com.ssafy.teongbin.shortcut.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ShortcutResponseDto {
    private String nickname;

    private Double latitude;
    private Double longitude;
    private int zoom_level;


    public static ShortcutResponseDto fromEntity(String nickname, Double latitude, Double longitude, int zoom_level) {
        return new ShortcutResponseDto(nickname, longitude,latitude, zoom_level);
    }


}
