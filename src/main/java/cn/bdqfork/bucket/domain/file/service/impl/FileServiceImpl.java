package cn.bdqfork.bucket.domain.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import cn.bdqfork.bucket.domain.file.entity.File;
import cn.bdqfork.bucket.domain.file.mapper.FileMapper;
import cn.bdqfork.bucket.domain.file.service.FileService;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

}
