package com.ssafy.teongbin.shortcut.dto.request;

import lombok.Data;

import java.awt.*;

@Data
public class AddShortcutRequestDto {
    String nickname;
//    private Point location;
    String location;
    int zoom_level;

}
