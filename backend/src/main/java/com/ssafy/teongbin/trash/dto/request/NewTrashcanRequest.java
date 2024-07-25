package com.ssafy.teongbin.trash.dto.request;

import lombok.Data;

import java.awt.*;

@Data
public class NewTrashcanRequest {

    private String serialNumber;
    private String nickname;
    private Point location;

}
