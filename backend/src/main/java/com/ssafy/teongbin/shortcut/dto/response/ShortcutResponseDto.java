package com.ssafy.teongbin.shortcut.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ShortcutResponseDto {
    private String nickname;
    private Point location;
    private int zoom_level;


    public static ShortcutResponseDto fromEntity(String nickname, Point location, int zoom_level) {
        return new ShortcutResponseDto(nickname, location, zoom_level);
    }












}
