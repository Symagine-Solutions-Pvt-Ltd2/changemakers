package com.lms.changemaker.serviceimpl;

import com.lms.changemaker.repository.CertificateDetailRepo;
import com.lms.changemaker.entity.CertificateDetail;
import com.lms.changemaker.service.CertificateDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CertificateDetailServiceImpl implements CertificateDetailService {

    @Autowired
    CertificateDetailRepo certificateDetailRepo;

    @Override
    @Transactional
    public CertificateDetail addCertificateDetailProg(CertificateDetail certificateDetail) {
        return certificateDetailRepo.save(certificateDetail);
    }

    @Override
    public List<CertificateDetail> findAllCertificateDetail() {
        return certificateDetailRepo.findAll();
    }

    @Override
    public CertificateDetail findCertificateDetailByCertifyId(int certifyId) {
        return certificateDetailRepo.findById(certifyId).get();
    }

    @Override
    public List<CertificateDetail> getCertificateDetailByStudentId(int studentId) {
        return certificateDetailRepo.getCertificateDetailByStudentId(studentId);
    }

    @Override
    public CertificateDetail getCertificateDetailByStuProg(int progId, int studentId) {
        return certificateDetailRepo.getCertificateDetailByStuProg(progId,studentId);
    }

    @Override
    public List<CertificateDetail> getCertificateDetailBySchoolId(int schoolId) {
        return certificateDetailRepo.getCertificateDetailBySchoolId(schoolId);
    }

    @Override
    public List<CertificateDetail> getCertificateDetailByUserId(int userId) {
        return certificateDetailRepo.getCertificateDetailByUserId(userId);
    }

    @Override
    @Transactional
    public int saveCertificateUrl(String certificateUrl, int studentId, int progId) {
        return certificateDetailRepo.saveCertificateUrl(certificateUrl,studentId,progId);
    }
}
