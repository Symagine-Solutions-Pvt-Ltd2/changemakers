package com.lms.changemaker.repository;

import com.lms.changemaker.entity.CertificateDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateDetailRepo extends JpaRepository<CertificateDetail, Integer> {

	
	@Query("SELECT u FROM CertificateDetail u WHERE u.studentId = :studentId")
	public	List<CertificateDetail> getCertificateDetailByStudentId(int studentId);
	
	@Query("SELECT u FROM CertificateDetail u WHERE u.progId = :progId and u.studentId= :studentId")
	public	CertificateDetail getCertificateDetailByStuProg(int progId,int studentId);


	@Query("SELECT u FROM CertificateDetail u WHERE  u.schoolId= :schoolId")
	public	List<CertificateDetail> getCertificateDetailBySchoolId(int schoolId);
	
	@Modifying
	@Query("UPDATE CertificateDetail u set u.certificateUrl =:certificateUrl  WHERE u.studentId = :studentId and u.progId = :progId")
	public int saveCertificateUrl(String certificateUrl,int studentId,int progId);

	@Query("SELECT u FROM CertificateDetail u WHERE  u.userId= :userId")
    List<CertificateDetail> getCertificateDetailByUserId(int userId);
}

