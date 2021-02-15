package cn.bdqfork.bucket.domain.file;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.bdqfork.bucket.domain.file.entity.Bucket;
import cn.bdqfork.bucket.domain.file.service.BucketService;
import cn.bdqfork.bucket.handler.result.CommonResult;

@RestController
@RequestMapping("/buckets")
public class BucketController {

    @Autowired
    private BucketService bucketService;

    /**
     * 添加桶，同时创建根目录/
     * 
     * @param name      桶名称
     * @param userId    用户id
     * @param privilege 桶权限
     * @return CommonResult<Boolean>
     */
    @PostMapping("")
    public CommonResult<Boolean> add(@RequestParam String name, @RequestParam Long userId,
            @RequestParam Byte privilege) {
        return CommonResult.success();
    }

    /**
     * 判断bucket是否为空
     * 
     * @param bucketId 桶id
     * @return CommonResult<Boolean>
     */
    @GetMapping("/{bucketId}/empty")
    public CommonResult<Boolean> empty(@PathVariable("bucketId") Long bucketId) {
        return CommonResult.success(true);
    }

    /**
     * 删除桶。如果force为true，不进行非空检测。如果recursion为true，递归删除空子目录。
     * 
     * @param bucketId  桶id
     * @param force     是否强制删除
     * @param recursion 是否递归删除子目录
     * @return CommonResult<Boolean>
     */
    @DeleteMapping("/{bucketId}")
    public CommonResult<Boolean> remove(@PathVariable("bucketId") Long bucketId, @RequestParam Boolean force,
            @RequestParam Boolean recursion) {
        // TODO: 关联文件删除
        return CommonResult.success(bucketService.removeById(bucketId));
    }

    /**
     * 获取bucket列表
     * 
     * @param userId 用户id
     * @return CommonResult<List<Bucket>>
     */
    @GetMapping("/list")
    public CommonResult<List<Bucket>> getAll(@RequestParam("userId") Long userId) {
        LambdaQueryWrapper<Bucket> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Bucket::getUserId, userId);
        List<Bucket> buckets = bucketService.list(lambdaQueryWrapper);
        return CommonResult.success(buckets);
    }
}
