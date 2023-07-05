package com.lms.changemaker.service;

import com.lms.changemaker.entity.CertificateDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CertificateDetailService {
	
	CertificateDetail addCertificateDetailProg(CertificateDetail CertificateDetail);
		
    List<CertificateDetail> findAllCertificateDetail();

    CertificateDetail findCertificateDetailByCertifyId(int certifyId);

    List<CertificateDetail> getCertificateDetailByStudentId(int studentId);

    CertificateDetail getCertificateDetailByStuProg(int progId,int studentId);

    List<CertificateDetail> getCertificateDetailBySchoolId(int schoolId);
    
    List<CertificateDetail> getCertificateDetailByUserId(int userId);

    int saveCertificateUrl(String certificateUrl,int studentId,int progId);
    
}
