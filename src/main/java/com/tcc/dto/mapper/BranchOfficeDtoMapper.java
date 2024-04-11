package com.tcc.dto.mapper;

import com.tcc.dto.BranchOfficeDto;
import com.tcc.entity.BranchOffice;

public class BranchOfficeDtoMapper {

        public static BranchOfficeDto boffToDto(BranchOffice office , BranchOfficeDto officeDto){
                officeDto.setIsHeadOffice(office.getIsHeadOffice());
                officeDto.setLoc(office.getLoc());
                officeDto.setBranchId(office.getBranchId());
                officeDto.setConsignments(office.getConsignments());
                officeDto.setEmployees(office.getEmployees());
                officeDto.setTrucks(office.getTrucks());
              return officeDto;
        }

        public static BranchOffice dtoToboff(BranchOffice office , BranchOfficeDto officeDto){
                office.setIsHeadOffice(officeDto.getIsHeadOffice());
                office.setLoc(officeDto.getLoc());
                office.setBranchId(officeDto.getBranchId());
                office.setConsignments(officeDto.getConsignments());
                office.setEmployees(officeDto.getEmployees());
                office.setTrucks(officeDto.getTrucks());
                return office;
        }
        }
