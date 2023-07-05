package com.lms.changemaker.serviceimpl;

import com.lms.changemaker.entity.ProgramStandardDetail;
import com.lms.changemaker.repository.ProgramStandardDetailRepository;
import com.lms.changemaker.service.ProgramStandardDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramStandardDetailServiceImpl implements ProgramStandardDetailService {

    @Autowired
    private  ProgramStandardDetailRepository programStandardDetailRepository;

    @Override
    public ProgramStandardDetail addProgramStandardDetail(ProgramStandardDetail programStandardDetail) {
        return programStandardDetailRepository.save(programStandardDetail);
    }

    @Override
    public List<ProgramStandardDetail> findAllProgramStandardDetail() {
        return programStandardDetailRepository.findAll();
    }

    @Override
    public ProgramStandardDetail findProgramStandardDetailById(int idProgStandardDetail) {
        return programStandardDetailRepository.findById(idProgStandardDetail).get();
    }
}
