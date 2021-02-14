package cn.bdqfork.bucket.domain.file.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    public static String digest(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("MD5");
        mDigest.update(input);
        byte[] result = mDigest.digest();
        return new BigInteger(1, result).toString(16);
    }

    public static void saveToDisk(MultipartFile file, String path) throws IOException{
        File dest = new File(new File(path).getAbsolutePath());
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        file.transferTo(dest);

    }

    public static void download(String name, String path, HttpServletResponse response) throws IOException{
        File file = new File(path);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + name);// 设置文件名
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
                byte[] buff = new byte[1024];
                OutputStream os = response.getOutputStream();
                int i = 0;
                while ((i = bis.read(buff)) != -1) {
                    os.write(buff, 0, i);
                    os.flush();
                }
            }
        }
    }
}
