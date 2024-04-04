package com.tcc.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchOfficeDto {
    private boolean isHeadOffice;

    private String loc;
}
