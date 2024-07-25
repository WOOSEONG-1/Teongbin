package com.ssafy.teongbin.trash.dto.request;

import lombok.Data;

import java.awt.*;
import java.time.LocalDateTime;

@Data
public class NewTrashcanRequest {

    private String serial_number;
    private String nickname;
    private Point location;

}
