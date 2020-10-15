package nsi.contractManagement.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nsi.contractManagement.VO.FileVO;
import nsi.contractManagement.VO.MinIoProperties;
import nsi.contractManagement.config.response.ResponseResultBody;
import nsi.contractManagement.config.response.ResultStatus;
import nsi.contractManagement.utils.MinIoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Tao
 * @Time: 2020/10/12 20:08
 * @ProjectName: contract-management
 * @FileName: MinIoController.java
 * @IDE: IntelliJ IDEA
 */
@RestController
@RequestMapping("/api/minio")
@Api(tags = {"文件接口"})
@ResponseResultBody
public class MinIoController {

    @Autowired
    MinIoProperties minIoProperties;

    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/upload")
    public FileVO upload(@RequestParam("file") MultipartFile file)  {
        String fileUrl = MinIoUtil.upload(minIoProperties.getBucketName(), file);
        return FileVO.builder().fileUrl(fileUrl).name(file.getOriginalFilename()).build();

    }

    @ApiOperation(value = "下载文件")
    @GetMapping(value = "/download")
    public void download(@RequestParam("fileName") String fileName, HttpServletResponse response) {
        MinIoUtil.download(minIoProperties.getBucketName(), fileName, response);
    }

    @ApiOperation(value = "删除文件")
    @GetMapping(value = "/delete")
    public Enum<ResultStatus> delete(@RequestParam("fileName") String fileName) {
        MinIoUtil.deleteFile(minIoProperties.getBucketName(), fileName);
        return ResultStatus.SUCCESS;
    }

}

