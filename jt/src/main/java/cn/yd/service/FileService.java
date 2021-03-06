package cn.yd.service;


import cn.yd.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ImageVO upload(MultipartFile file);

    void deleteFile(String virtualPath);
}