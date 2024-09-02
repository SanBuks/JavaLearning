package org.learn.java.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping
public class UpDownloadController {

    private static final Logger logger = LoggerFactory.getLogger(UpDownloadController.class);

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<byte[]> Download(HttpSession session) throws IOException {
        // #1. 获取文件绝对路径
        ServletContext context = session.getServletContext();
        String filename = "piggy.jpg";
        String path = context.getRealPath("/static/image/" + filename);
        logger.info("path {} ", path);

        // #2. 获取二进制数据
        byte[] data;
        try (InputStream is = Files.newInputStream(Paths.get(path))) {
            data = new byte[is.available()];
            int size = is.read(data);
            logger.info("{} bytes read", size);
        }

        // #3. 构建返回报文
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<>(data, headers, status);
        return entity;
    }

    // # 4. 创建上传路径文件夹
    private String createUploadDir(ServletContext context) throws IOException {
        String uploadPath = context.getRealPath("/upload");
        File file = new File(uploadPath);
        if (file.exists() && file.isFile()) {
            if (!file.delete()) throw new IOException("Could not delete file");
            if (!file.mkdir()) throw new IOException("Could not create directory");
        } else if (!file.exists()) {
            if (!file.mkdir()) throw new IOException("Could not create directory");
        }
        return uploadPath;
    }

    // # 5. 避免重复文件冲突
    private String newFilePath(String originFileName) {
        String fileFullName = originFileName == null ? "" : originFileName;
        String fileName = fileFullName.substring(0, fileFullName.lastIndexOf("."));
        String extName = fileFullName.substring(fileFullName.lastIndexOf(".") + 1);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return fileName + "_" + uuid + "." + extName;
    }

    // #6. 上传文件
    @PostMapping("/upload")
    public String Upload(MultipartFile photo, HttpSession session) throws IOException {
        ServletContext context = session.getServletContext();
        String uploadPath = createUploadDir(context);
        String newFileName = newFilePath(photo.getOriginalFilename());
        String filePath = uploadPath + File.separator + newFileName;
        photo.transferTo(new File(filePath));
        logger.info("{} 已上传为 {}, 大小: {} bytes", photo.getOriginalFilename(), newFileName, photo.getSize());
        return "target";
    }
}