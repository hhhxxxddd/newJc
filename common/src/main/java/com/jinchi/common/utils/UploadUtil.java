package com.jinchi.common.utils;

import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author：XudongHu
 * @className:uploadUtil
 * @description: 文件上传下载工具类
 * @date:13:51 2019/1/16
 * @Modifer:
 */
public class UploadUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadUtil.class);
    private static final String[] PIC_FORMAT = {".jpg", ".jpeg", ".gif", ".bmp", ".png"};


    /**
     * 地址验证环节
     *
     * @return
     */
    private static String verifyPath(String runningPath, String fileName) {
        File path = new File(runningPath.substring(0,runningPath.length()-1));
        //如果文件夹不存在 就创建
        if (!path.exists()) path.mkdirs();
        return runningPath + fileName;
    }

    /**
     * 上传文件
     *
     * @param runningPath jar运行时的存储路径
     * @param file        文件本体
     * @param maxSize     文件最大为 单位为MB
     * @return 文件最终存储的名字
     */
    public static String upload(String runningPath, MultipartFile file, Integer maxSize, String postfix) throws IOException {
        //后缀验证
        Assert.isTrue(file.getOriginalFilename().endsWith(postfix), String.format("仅能上传后缀为%s的文件", postfix));
        //大小验证
        long size = file.getSize();
        Assert.isTrue(!StringUtils.isEmpty(file) && 0 < size && size < maxSize * 1048576, String.format("上传文件非法,规格超出%dM", maxSize));

        File serverFile = new File(verifyPath(runningPath, NumberGenerator.fileCode(postfix)));

        serverFile.createNewFile();
        FileOutputStream out = new FileOutputStream(serverFile);
        out.write(file.getBytes());
        out.close();
        //返回服务器名
        return serverFile.getName();
    }

    /**
     * 上传图片
     *
     * @param runningPath 运行时路径
     * @param file        文件
     * @param maxSize     文件最大值 单位为MB
     * @return
     * @throws IOException
     */
    public static String uploadPic(String runningPath, MultipartFile file, Integer maxSize) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String postfix = "";
        for (int i = 0; i < PIC_FORMAT.length; i++) {
            postfix = originalFilename.endsWith(PIC_FORMAT[i]) ? PIC_FORMAT[i] : "";
            if (postfix.length() > 0) break;
        }
        return upload(runningPath, file, maxSize, postfix);
    }

    /**
     * 删除文件
     *
     * @param runningPath
     * @param fileName
     * @return
     */
    public static String deleteFile(String runningPath, String fileName) {
        File file = new File(verifyPath(runningPath, fileName));
        LOGGER.info("文件{}存在", file.exists() ? "" : "不");
        file.delete();
        LOGGER.info("{}文件{}删除", file.getName(), file.exists() ? "未" : "已");
        return String.format("文件{%s}已删除", file.getName());
    }

}
