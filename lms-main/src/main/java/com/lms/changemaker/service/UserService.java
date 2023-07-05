package com.lms.changemaker.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.lms.changemaker.entity.EditProgramAssignSubAdmin;
import com.lms.changemaker.entity.ProgramAssignedDetail;
import com.lms.changemaker.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

	
	User addUser(User user);
		
    List<User> findAllUsers();
   
    List<User> findAllSubAdmins(int type);
    
    Optional<User> findUserByUserId(int taskId);
    
    User findUserByUserName(String username);

    User findUserByEmail(String email);

	int autologin(User student);

   
    String editSubAdmin(User userForm);

    String registerSubAdmin(User user);

	List<User> getSubAdminDashBoardList(Locale locale);

	int autologinSubadmin(User user);

    @Transactional
    void saveUserToken(String token, int userId);

    int changePassword(User user);

    String getAssignedPrograms(User user);

    void editAssignProg(EditProgramAssignSubAdmin editProgramAssignSubAdmin, User user);
}
