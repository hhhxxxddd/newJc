package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author：XudongHu
 * @className:uploadController
 * @description:
 * @date:16:23 2019/1/11
 * @Modifer:
 */
@RestController
@RequestMapping(value = "/upload")
@Api(tags = "通用上传")
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
    private static final String DEVPATH = "classpath";
    private static final String RUNPATH = "../" + DEVPATH;

    /**
     * 通用上传接口
     * 先使用这个上传到缓存中
     *
     * @param file
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "通用上传接口")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> fileUpload(@RequestParam("file") MultipartFile file, @RequestParam Integer id) throws IOException, IllegalStateException {
        File convertFile = new File("");
        String canonicalPath = convertFile.getCanonicalPath();
        System.out.println("类路径:"+this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        System.out.println("类加载的根路径:"+this.getClass().getResource("/").getPath());
        System.out.println("类所在的工程路径:"+this.getClass().getResource("").getPath());
        System.out.println("项目路径:"+canonicalPath);
        System.out.println("项目路径,不使用IO:"+System.getProperty("user.dir"));



        return ResultUtil.success("上传成功");
    }
}
