package com.tcc.dto.mapper;

import com.tcc.dto.BranchOfficeDto;
import com.tcc.entity.BranchOffice;

public class BranchOfficeDtoMApper {

    public static BranchOfficeDto mapToBranchOfficeDto(BranchOffice bof, BranchOfficeDto bofDto){
        bofDto.setIsHeadOffice(bof.getIsHeadOffice());
        bofDto.setLoc(bof.getLoc());
        return bofDto;
    }

    public static BranchOffice mapToBranchOffice(BranchOffice bof, BranchOfficeDto bofDto){
        bof.setIsHeadOffice(bofDto.getIsHeadOffice());
        bof.setLoc(bofDto.getLoc());
        return bof;
    }

}
