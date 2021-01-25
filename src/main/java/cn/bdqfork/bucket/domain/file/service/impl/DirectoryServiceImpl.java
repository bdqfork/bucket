package cn.bdqfork.bucket.domain.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import cn.bdqfork.bucket.domain.file.entity.Directory;
import cn.bdqfork.bucket.domain.file.mapper.DirectoryMapper;
import cn.bdqfork.bucket.domain.file.service.DirectoryService;

@Service
public class DirectoryServiceImpl extends ServiceImpl<DirectoryMapper, Directory> implements DirectoryService {

}
