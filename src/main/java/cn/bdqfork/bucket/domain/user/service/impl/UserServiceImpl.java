package cn.bdqfork.bucket.domain.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import cn.bdqfork.bucket.domain.user.entity.User;
import cn.bdqfork.bucket.domain.user.mapper.UserMapper;
import cn.bdqfork.bucket.domain.user.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
