//package com.shuaibi.zaizaigoods.util;
//
//import com.github.tobato.fastdfs.domain.StorePath;
//import com.github.tobato.fastdfs.domain.ThumbImageConfig;
//import com.github.tobato.fastdfs.service.FastFileStorageClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//
///**
// * 工具类
// */
//@Component
//public class FastDFSClientWrapper {
//
//    @Autowired
//    private FastFileStorageClient storageClient;
//
//    @Autowired
//    private ThumbImageConfig fdfsConfig;
//
//
//    public void testUpload() throws FileNotFoundException {
//        File file = new File("D:\\test\\baby.png");
//        // 上传并且生成缩略图
//        StorePath storePath = this.storageClient.uploadFile(
//                new FileInputStream(file), file.length(), "png", null);
//        // 带分组的路径
//        System.out.println(storePath.getFullPath());
//        // 不带分组的路径
//        System.out.println(storePath.getPath());
//    }
//}