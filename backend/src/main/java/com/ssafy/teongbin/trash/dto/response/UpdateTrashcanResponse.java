package com.ssafy.teongbin.trash.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

@Data
@AllArgsConstructor
public class UpdateTrashcanResponse {
    private Long id;
    private String nickname;
    private Point location;
}
