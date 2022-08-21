package com.exam.examserver.service.impl;

import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.repo.RoleRepository;
import com.exam.examserver.repo.UserRepository;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * creating user
     * @param user
     * @param userRoles
     * @return
     * @throws Exception
     */
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local=this.userRepository.findByUsername(user.getUsername());
        if(local != null){
            System.out.println("User is already there");
            throw new Exception("User already present");
        }else {
            for (UserRole userRole:userRoles){
                roleRepository.save(userRole.getRole());
            }
            user.getUserRole().addAll(userRoles);
            local =this.userRepository.save(user);
        }
        return local;
    }

    /**
     * getting user by username
     * @param username
     * @return
     */
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }
}
