package nsi.contractManagement.utils;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.PutObjectOptions;
import io.minio.messages.Bucket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nsi.contractManagement.VO.MinIoProperties;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: Tao
 * @Time: 2020/10/12 20:02
 * @ProjectName: contract-management
 * @FileName: MinIoUtil.java
 * @IDE: IntelliJ IDEA
 */
@Slf4j
@Component
public class MinIoUtil {

    @Autowired
    MinIoProperties minIoProperties;

    private static MinioClient minioClient;


    @PostConstruct
    public void init() {
        try {
            minioClient = new MinioClient(minIoProperties.getUrl(), minIoProperties.getAccessKey(),
                    minIoProperties.getSecretKey());
            createBucket(minIoProperties.getBucketName());
        } catch (Exception e) {
            e.printStackTrace();
            log.error (String.valueOf(e.fillInStackTrace()));
        }
    }


    @SneakyThrows(Exception.class)
    public static boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(bucketName);
    }


    @SneakyThrows(Exception.class)
    public static void createBucket(String bucketName) {
        boolean isExist = minioClient.bucketExists(bucketName);
        if (!isExist) {
            minioClient.makeBucket(bucketName);
        }
    }


    @SneakyThrows(Exception.class)
    public static List<Bucket> getAllBuckets() {
        return minioClient.listBuckets();
    }

    @SneakyThrows(Exception.class)
    public static void upload(String bucketName, String fileName, String filePath) {
        minioClient.putObject(bucketName, fileName, filePath, null);
    }


    @SneakyThrows(Exception.class)
    public static String upload(String bucketName, String fileName, InputStream stream) {
        minioClient.putObject(bucketName, fileName, stream, new PutObjectOptions(stream.available(), -1));
        return getFileUrl(bucketName, fileName);
    }


    @SneakyThrows(Exception.class)
    public static String upload(String bucketName, MultipartFile file) {
        final InputStream is = file.getInputStream();
        final String fileName = file.getOriginalFilename();
        minioClient.putObject(bucketName, fileName, is, new PutObjectOptions(is.available(), -1));
        is.close();
        return getFileUrl(bucketName, fileName);
    }


    @SneakyThrows(Exception.class)
    public static void deleteFile(String bucketName, String fileName) {
        minioClient.removeObject(bucketName, fileName);
    }

    
    @SneakyThrows(Exception.class)
    public static void download(String bucketName, String fileName, HttpServletResponse response) {
        // 获取对象的元数据
        final ObjectStat stat = minioClient.statObject(bucketName, fileName);
        response.setContentType(stat.contentType());
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        InputStream is = minioClient.getObject(bucketName, fileName);
        IOUtils.copy(is, response.getOutputStream());
        is.close();
    }


    @SneakyThrows(Exception.class)
    public static String getFileUrl(String bucketName, String fileName) {
        return minioClient.presignedGetObject(bucketName, fileName);
    }

}

