/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huang.web;

import com.huang.bean.MessageBean;
import com.huang.util.UtilTools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.Directory;
import org.javaswift.joss.model.DirectoryOrObject;
import org.javaswift.joss.model.StoredObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping()
public class ContainerController extends BaseController {

    private Container getContainer(String path) {
        //need parse path
        String containerPath = path;
        Container container = UtilTools.getAccount().getContainer(containerPath);
        if (!container.exists()) {
            container.create();
        }
        return container;
    }

    @RequestMapping("/home")
    @ResponseBody
    public List get(String path) {
        Collection<StoredObject> objects = getContainer(path).list();
        List list = new ArrayList();
        for (StoredObject so : objects) {
            list.add(instance(so));
        }
        return list;
    }

    /**
     * 写文件夹的前端逻辑太麻烦了,懒得写了= =
     */
    @RequestMapping("/directory")
    @ResponseBody
    public Collection<DirectoryOrObject> getDic(String path) {
        Collection<DirectoryOrObject> objects = getContainer(path).listDirectory();
        return objects;
    }

    /**
     * 写文件夹的前端逻辑太麻烦了,懒得写了= =
     */
    @RequestMapping("/createdirectory")
    @ResponseBody
    public MessageBean createdirectory(String path, String directory) throws IOException {
        getContainer(path).getObject(directory + "/").uploadObject(new byte[0]);
        return new MessageBean(true, "");
    }

    @RequestMapping("/uploadfile")
    @ResponseBody
    public MessageBean upload(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        MultipartHttpServletRequest fileRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = fileRequest.getFile("qqfile");
        File tmpFile = File.createTempFile(file.getOriginalFilename(), null);
        file.transferTo(tmpFile);
        return new MessageBean(true, "", tmpFile.getAbsolutePath());
    }

    @RequestMapping("/createfile")
    @ResponseBody
    public MessageBean create(String path, String name, String filepath) throws IOException {
        name = name.replaceAll(",", "");
        if (getContainer(path).getObject(name).exists()) {
            return new MessageBean(false, "已有同名文件");
        }
        File file = new File(filepath);
        if (file.exists()) {
            try (FileInputStream fis = FileUtils.openInputStream(file)) {
                getContainer(path).getObject(name).uploadObject(fis);
                return new MessageBean(true, "上传成功");
            }
        }
        return new MessageBean(false, "上传失败");
    }

    @RequestMapping("/download")
    public void download(String path, HttpServletResponse response, String name) throws IOException {
        StoredObject storedObject = getContainer(path).getObject(name);
        if (storedObject.exists()) {
            String contentType = "application/octet-stream";
            response.setHeader("Content-Disposition", "attachment;filename=" + name);
            InputStream dataStream = storedObject.downloadObjectAsInputStream();
            response.setContentType(contentType);
            try (OutputStream responseStream = response.getOutputStream()) {
                FileCopyUtils.copy(dataStream, responseStream);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public MessageBean delete(String path, String name) {
        String[] split = name.split(",");
        for (String n : split) {
            getContainer(path).getObject(n).delete();
        }
        return new MessageBean(true, split);
    }

    /**
     * 文件夹太麻烦了,没文件夹功能还有复制的必要吗
     */
    @RequestMapping("/copy")
    @ResponseBody
    public MessageBean copy(String path, String name, String newpath) {
        Container container = getContainer(path);
        container.getObject(path).copyObject(container, container.getObject(newpath));
        return new MessageBean(true, "复制成功");
    }

    /**
     * 重命名,其实就是移动
     */
    @RequestMapping("/move")
    @ResponseBody
    public MessageBean move(String path, String name, String newname) {
        Container container = getContainer(path);
        container.getObject(name).copyObject(container, container.getObject(newname.replaceAll(",", "")));
        container.getObject(name).delete();
        return new MessageBean(true, "移动成功");
    }

    private FileObject instance(DirectoryOrObject so) {
        if (so.isDirectory()) {
            return new FileObject(so.getAsDirectory());
        } else {
            return new FileObject(so.getAsObject());
        }
    }

    class FileObject implements Serializable {

        String type;
        String filename;
        String filepath;
        long size;
        Date updateAt;

        public FileObject(StoredObject so) {
            this.type = "object";
            this.filename = so.getBareName();
            this.filepath = so.getURL();
            this.size = so.getContentLength();
            this.updateAt = so.getLastModifiedAsDate();
        }

        public FileObject(Directory so) {
            this.type = "dir";
            this.filename = so.getBareName();
            this.filepath = null;
            this.size = 0;
            this.updateAt = null;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getFilepath() {
            return filepath;
        }

        public void setFilepath(String filepath) {
            this.filepath = filepath;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public Date getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(Date updateAt) {
            this.updateAt = updateAt;
        }

    }

}
