package com.ssafy.teongbin.shortcut.dto.request;

import lombok.Data;

import java.awt.*;

@Data
public class AddShortcutRequestDto {
    private String nickname;
    private Point location;
    private int zoom_level;

}
