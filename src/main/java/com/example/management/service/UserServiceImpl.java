package com.example.management.service;

import com.example.management.entity.UserEntity;
import com.example.management.model.User;
import com.example.management.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity=new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities=userRepository.findAll();
       List<User> users=userEntities.stream().map(userEntity -> new User(userEntity.getId(),userEntity.getFirstName()
        , userEntity.getLastName(), userEntity.getEmailId())).collect(Collectors.toList());
        return users;
    }

    @Override
    public User getUser(long id) {
        UserEntity userEntity=userRepository.findById(id).get();
        User user=new User();
        BeanUtils.copyProperties(userEntity,user);
        return user;
    }

    @Override
    public User deleteUser(long id) {

        UserEntity userEntity=userRepository.findById(id).get();
        User user =new User();
        BeanUtils.copyProperties(userEntity,user);
        userRepository.delete(userEntity);
        return user;
    }

    @Override
    public User updateUser(long id, User user) {
        UserEntity userEntity=userRepository.findById(id).get();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmailId(user.getEmailId());
        userRepository.save(userEntity);
        return user;
    }

}
