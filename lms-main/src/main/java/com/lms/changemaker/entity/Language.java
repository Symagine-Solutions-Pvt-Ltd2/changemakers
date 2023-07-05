package com.lms.changemaker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Language implements Serializable {
    private int languageId;
    private String languageName;
    private String languageCode;
}
