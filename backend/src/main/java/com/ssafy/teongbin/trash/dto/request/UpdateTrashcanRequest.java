package com.ssafy.teongbin.trash.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

@Data
public class UpdateTrashcanRequest {
    private String nickname;
    private Point location;
}
