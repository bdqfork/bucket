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

import cn.bdqfork.bucket.domain.file.entity.Directory;
import cn.bdqfork.bucket.domain.file.service.DirectoryService;
import cn.bdqfork.bucket.handler.result.CommonResult;

@RestController
@RequestMapping("/directories")
public class DirectoryController {

    @Autowired
    private DirectoryService directoryService;

    /**
     * 添加目录
     * 
     * @param name     目录名称
     * @param bucketId 桶id
     * @param parentId 父目录
     * @return CommonResult<Boolean>
     */
    @PostMapping("")
    public CommonResult<Boolean> add(@RequestParam String name, @RequestParam Long bucketId,
            @RequestParam Long parentId) {
        Directory directory = new Directory();
        directory.setName(name);
        directory.setBucketId(bucketId);
        directory.setParentId(parentId);
        return CommonResult.success(directoryService.save(directory));
    }

    /**
     * 判断目录是否为空。如果目录包含子目录或者文件，则返回false，否则返回true。
     * 
     * @param dirId 目录id
     * @return CommonResult<Boolean>
     */
    @GetMapping("/{dirId}/empty")
    public CommonResult<Boolean> empty(@PathVariable("dirId") Long dirId) {
        // TODO: 判断目录是否为空
        return CommonResult.success();
    }

    /**
     * 删除目录。如果force为true，不进行非空检测。如果recursion为true，递归删除空子目录。
     * 
     * @param dirId     目录id
     * @param force     是否强制删除
     * @param recursion 是否递归删除子目录
     * @return CommonResult<Boolean>
     */
    @DeleteMapping("/{dirId}")
    public CommonResult<Boolean> remove(@PathVariable("dirId") Long dirId, @RequestParam Boolean force,
            @RequestParam Boolean recursion) {
        return CommonResult.success();
    }

    /**
     * 获取目录列表，如果为空，表示是最后一层目录。
     * 
     * @param bucketId 桶id
     * @param parentId 父目录id
     * @return CommonResult<List<Directory>>
     */
    @GetMapping("/list")
    public CommonResult<List<Directory>> getDirectorys(@RequestParam("bucketId") Long bucketId,
            @RequestParam("parentId") Long parentId) {
        LambdaQueryWrapper<Directory> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Directory::getBucketId, bucketId);
        lambdaQueryWrapper.eq(Directory::getParentId, parentId);
        return CommonResult.success(directoryService.list(lambdaQueryWrapper));
    }

}
