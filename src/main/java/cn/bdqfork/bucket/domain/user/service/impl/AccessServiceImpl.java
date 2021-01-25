package cn.bdqfork.bucket.domain.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import cn.bdqfork.bucket.domain.user.entity.Access;
import cn.bdqfork.bucket.domain.user.mapper.AccessMapper;
import cn.bdqfork.bucket.domain.user.service.AccessService;

@Service
public class AccessServiceImpl extends ServiceImpl<AccessMapper, Access> implements AccessService {

}
