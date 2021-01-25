package cn.bdqfork.bucket.domain.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import cn.bdqfork.bucket.domain.file.entity.Bucket;
import cn.bdqfork.bucket.domain.file.mapper.BucketMapper;
import cn.bdqfork.bucket.domain.file.service.BucketService;

@Service
public class BucketServiceImpl extends ServiceImpl<BucketMapper, Bucket> implements BucketService {

}
