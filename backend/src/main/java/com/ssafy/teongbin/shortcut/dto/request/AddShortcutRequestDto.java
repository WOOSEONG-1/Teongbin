package com.ssafy.teongbin.shortcut.dto.request;

import lombok.Data;


@Data
public class AddShortcutRequestDto {
    String nickname;
    private Point location;
    int zoom_level;

}
