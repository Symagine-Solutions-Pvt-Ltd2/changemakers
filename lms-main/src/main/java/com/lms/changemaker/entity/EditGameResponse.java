package com.lms.changemaker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditGameResponse {

    private String message;

    public EditGameResponse(String message) {
        this.message = message;
    }
}
