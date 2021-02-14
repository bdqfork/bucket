package cn.bdqfork.bucket.domain.file;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.bdqfork.bucket.domain.file.entity.File;
import cn.bdqfork.bucket.domain.file.service.FileService;
import cn.bdqfork.bucket.domain.file.utils.FileUtils;
import cn.bdqfork.bucket.handler.exception.OperationException;

@RestController
@RequestMapping("file")
public class FileController {

    @Value("${file.location}")
    private String location;

    @Autowired
    private FileService fileService;

    @PostMapping("/{dirId}")
    public Object upload(@RequestParam("file") MultipartFile file, @PathVariable("dirId") Long dirId)
            throws OperationException, NoSuchAlgorithmException, IOException {
        if (file.isEmpty()) {
            throw new OperationException("上传失败");
        }
        
        String fileName = file.getOriginalFilename();
        String fileHash = FileUtils.digest(file.getBytes());

        File entity = new File();

        entity.setName(fileName);
        entity.setVersion(1);
        entity.setDirectoryId(dirId);
        entity.setHashcode(fileHash);

        fileService.save(entity);

        if(location.charAt(location.length()-1) != '/') {
            location = location + "/";
        }        

        String path = location + fileHash;
        FileUtils.saveToDisk(file, path);

        return true;
    }

    @GetMapping("/dir/{dirId}")
    public Object getFiles(@PathVariable("dirId") Long dirId) {
        LambdaQueryWrapper<File> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(File::getDirectoryId, dirId);
        return fileService.list(lambdaQueryWrapper);
    }

    @GetMapping("/{fileId}")
    public Object downloadFile(HttpServletResponse response, @PathVariable("fileId") Long fileId) {
        File file = fileService.getById(fileId);
        if(file == null) {
            return "文件不存在";
        }
        if(location.charAt(location.length()-1) != '/') {
            location = location + "/";
        }
        String path = location + file.getHashcode();
        FileUtils.download(file.getName(), path, response);
        return "success";
    }
}