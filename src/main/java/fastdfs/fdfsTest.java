package fastdfs;

import base.Json;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class fdfsTest {
    /**
     * 图片上传
     *
     * @param file
     * @param image_url
     */
    @RequestMapping("/uploadImage.form")
    public void uploadImage(@RequestParam(value = "file", required = false) MultipartFile file, String image_url) {
        Json json = new Json();
        StorageClient1 client = null;
        FileInputStream input = null;
        InputStreamReader in = null;
        try {
            //获取上传文件名
            String fileName = file.getOriginalFilename();
            //获取文件名
            String fileName_str = fileName.substring(0, fileName.lastIndexOf("."));
            //文件类型
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            byte[] images = file.getBytes();
            client = FdfsClientPool.getConnection(3000);
            //fastDFS方式
            String fileId = "";
            //设置元信息
            NameValuePair[] metaList = new NameValuePair[3];
            metaList[0] = new NameValuePair("fileName", fileName_str);
            //扩展名
            metaList[1] = new NameValuePair("fileExtName", fileExt);
            metaList[2] = new NameValuePair("fileLength", images.length + "");
            //上传文件
            fileId = client.upload_file1(images, fileExt, metaList);
            if (image_url != null && !image_url.equals("")) {
                //修改时换了图片，删除原来的图片
                client.delete_file1(image_url);
            }
            json.setMsg("成功");
            json.setSuccess(true);
            json.setObj(fileId);
        } catch (Exception e) {
            json.setMsg("失败");
            json.setSuccess(false);
        } finally {
            if (client != null) {
                FdfsClientPool.remove(client);
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        super.writeJson(json);
    }
}
