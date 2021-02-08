package cn.bdqfork.bucket.domain.file;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.bdqfork.bucket.domain.file.entity.Bucket;
import cn.bdqfork.bucket.domain.file.service.BucketService;
import cn.bdqfork.bucket.domain.file.vo.BucketVO;
import cn.bdqfork.bucket.handler.exception.OperationException;

@RestController
@RequestMapping("bucket")
public class BucketController {

    private final Log log = LogFactory.getLog(BucketController.class); 

    @Autowired
    private BucketService bucketService;
    
    @PostMapping
    public Object add(@RequestBody BucketVO bucketVO) throws OperationException {
        Bucket bucket = new Bucket();
        try {
            BeanUtils.copyProperties(bucket, bucketVO);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            throw new OperationException("新建bucket失败");
        }
        bucketService.save(bucket);
        return true;
    }

    @DeleteMapping("/{bucketId}")
    public Object remove(@PathVariable("bucketId") Long bucketId) {
        bucketService.removeById(bucketId);
        return true;
    }

    @GetMapping("/all/{userId}")
    public Object getAll(@PathVariable("userId") Long userId) {
        LambdaQueryWrapper<Bucket> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Bucket::getUserId, userId);
        return bucketService.list(lambdaQueryWrapper);
    }
}
