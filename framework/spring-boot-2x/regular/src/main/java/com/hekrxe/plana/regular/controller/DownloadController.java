package com.hekrxe.plana.regular.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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
        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; fileName=\"" + path.trim() + ".zip\"");

            zos = new ZipOutputStream(response.getOutputStream());
            File file = new File("/home/oppo/" + path);
            zip("", file, zos);
            zos.finish();
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            IOUtils.closeQuietly(zos);
        }
    }

    private static void zip(String parent, File file, ZipOutputStream zos) throws Exception {
        String child;
        if (parent.length() == 0) {
            child = file.getName();
        } else {
            child = parent + "/" + file.getName();
        }

        if (file.isFile()) {
            zos.putNextEntry(new ZipEntry(child));
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fileInputStream);
            int count;
            byte[] bytes = new byte[1024 * 1024];
            while ((count = bis.read(bytes)) > 0) {
                zos.write(bytes, 0, count);
            }
            bis.close();
            fileInputStream.close();
            zos.closeEntry();
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (null != files && files.length > 0) {
                for (File f : files) {
                    zip(child, f, zos);
                }
            } else {
                zos.putNextEntry(new ZipEntry(child + "/"));
            }
        }
    }

}
