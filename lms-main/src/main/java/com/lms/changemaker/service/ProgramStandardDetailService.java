package com.lms.changemaker.service;

import com.lms.changemaker.entity.CertificateDetail;
import com.lms.changemaker.entity.ProgramStandardDetail;

import java.util.List;

public interface ProgramStandardDetailService {
    ProgramStandardDetail addProgramStandardDetail(ProgramStandardDetail programStandardDetail);

    List<ProgramStandardDetail> findAllProgramStandardDetail();

    ProgramStandardDetail findProgramStandardDetailById(int idProgStandardDetail);

}
