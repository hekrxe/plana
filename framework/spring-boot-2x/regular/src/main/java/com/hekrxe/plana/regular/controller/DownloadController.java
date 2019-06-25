package com.hekrxe.plana.regular.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author tanhuayou on 2019/06/25
 */
@RestController
public class DownloadController {

    @GetMapping("/download")
    public void download(@RequestParam("path") String path,
                         HttpServletResponse response) {
        System.out.println("path=" + path);
        doDownload(path, response);
    }

    private void doDownload(String path, HttpServletResponse response) {
        ZipOutputStream zos = null;
        BufferedOutputStream bos = null;
        try {
            response.setContentType("application/force-download;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(path.trim(), "utf-8") + ".zip");
            zos = new ZipOutputStream(response.getOutputStream());
            bos = new BufferedOutputStream(zos);
            File file = new File(path);
            zip(".", file, zos, bos);
            bos.flush();
            zos.finish();
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            IOUtils.closeQuietly(bos);
            IOUtils.closeQuietly(zos);
        }
    }

    private static void zip(String parent, File file, ZipOutputStream zos, BufferedOutputStream bos) throws Exception {
        if (file.isFile()) {
            zos.putNextEntry(new ZipEntry(parent + "/" + file.getName()));
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fileInputStream);
            int count;
            byte[] bytes = new byte[1024 * 1024];
            while ((count = bis.read(bytes)) > 0) {
                zos.write(bytes, 0, count);
            }
            bis.close();
            fileInputStream.close();
            bos.flush();
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (null != files) {
                zos.putNextEntry(new ZipEntry(parent + "/" + file.getName() + "/"));
                for (File f : files) {
                    zip(parent + "/" + file.getName(), f, zos, bos);
                }
            }
        }
    }

}
