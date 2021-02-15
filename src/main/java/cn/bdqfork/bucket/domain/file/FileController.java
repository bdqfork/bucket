package cn.bdqfork.bucket.domain.file;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Delete;
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
import cn.bdqfork.bucket.handler.result.CommonResult;

@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${file.location}")
    private String location;

    @Autowired
    private FileService fileService;

    /**
     * 上传文件到目录，返回hashcode
     * 
     * @param file  文件流
     * @param dirId 目录id
     * @return CommonResult<hashcode>
     */
    @PostMapping("")
    public CommonResult<String> upload(@RequestParam("file") MultipartFile file, @RequestParam("dirId") Long dirId) {
        return CommonResult.success();
    }

    /**
     * 获取文件列表，如果目录下没有文件，返回空列表
     * 
     * @param dirId 目录id
     * @return CommonResult<List<File>>
     */
    @GetMapping("/list")
    public CommonResult<List<File>> list(@RequestParam("dirId") Long dirId) {
        return CommonResult.success();
    }

    /**
     * 删除文件，如果该文件的数据引用大于1，删除数据库记录即可，如果引用等于1，同时删除磁盘上的文件
     * 
     * @param hashcode hash码
     * @return CommonResult<Boolean>
     */
    @Delete("/{hashcode}")
    public CommonResult<Boolean> remove(@PathVariable("hashcode") String hashcode) {
        return CommonResult.success();
    }

    /**
     * 下载文件
     * 
     * @param hashcode hash码
     * @param response 下载流
     * @return
     */
    @GetMapping("/{hashcode}")
    public CommonResult<Boolean> download(@PathVariable("hashcode") String hashcode, HttpServletResponse response) {
        return CommonResult.success(true);
    }
}