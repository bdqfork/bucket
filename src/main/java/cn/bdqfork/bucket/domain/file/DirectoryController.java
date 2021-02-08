package cn.bdqfork.bucket.domain.file;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.bdqfork.bucket.domain.file.entity.Directory;
import cn.bdqfork.bucket.domain.file.entity.File;
import cn.bdqfork.bucket.domain.file.service.DirectoryService;
import cn.bdqfork.bucket.domain.file.service.FileService;
import cn.bdqfork.bucket.domain.file.vo.DirectoryVO;
import cn.bdqfork.bucket.domain.file.vo.ShowDirectoryCondition;
import cn.bdqfork.bucket.handler.exception.OperationException;

@RestController
@RequestMapping("directory")
public class DirectoryController {

    private final Log log = LogFactory.getLog(DirectoryController.class);

    @Autowired
    private DirectoryService directoryService;

    @Autowired
    private FileService fileService;

    @PostMapping
    public Object add(@RequestBody DirectoryVO directoryVO) throws OperationException {
        Directory directory = new Directory();
        try {
            BeanUtils.copyProperties(directory, directoryVO);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            throw new OperationException("新建目录失败");
        }
        directoryService.save(directory);
        return true;
    }

    @DeleteMapping("/remove/{dirId}")
    public Object remove(@PathVariable("dirId") Long dirId) throws OperationException {
        LambdaQueryWrapper<Directory> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Directory::getParentId, dirId);
        int childDirCount = directoryService.count(lambdaQueryWrapper);
        if (childDirCount > 0) {
            throw new OperationException("目录不为空");
        }
        LambdaQueryWrapper<File> fileLambdaQueryWrapper = Wrappers.lambdaQuery();
        fileLambdaQueryWrapper.eq(File::getDirectoryId, dirId);
        int fileCount = fileService.count(fileLambdaQueryWrapper);
        if(fileCount > 0) {
            throw new OperationException("目录不为空");
        }
        directoryService.removeById(dirId);
        return true;
    }

    @DeleteMapping("removeAll/{dirId}")
    public Object removeAll(@PathVariable("dirId") Long dirId) {
        removeChild(dirId);
        return true;
    }

    private void removeChild(Long dirId) {
        LambdaQueryWrapper<Directory> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Directory::getParentId, dirId);
        directoryService.remove(lambdaQueryWrapper);
        LambdaQueryWrapper<File> fileLambdaQueryWrapper = Wrappers.lambdaQuery();
        fileLambdaQueryWrapper.eq(File::getDirectoryId, dirId);
        File updateFile = new File();
        updateFile.setVersion(0);
        fileService.update(updateFile, fileLambdaQueryWrapper);
        directoryService.removeById(dirId);
    }

    public Object getDirectorys(ShowDirectoryCondition condition) {
        LambdaQueryWrapper<Directory> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Directory::getBucketId, condition.getBucketId());
        lambdaQueryWrapper.eq(Directory::getParentId, condition.getParentId());
        return directoryService.list(lambdaQueryWrapper);
    }
    
}
