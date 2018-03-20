package spring;

import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 一些项目中用到的spring代码
 * 供参考
 */
public class springCommon {
    /**
     * File对象转Multipartfile类型
     *
     * @throws FileNotFoundException
     */
    private void upload() throws FileNotFoundException {
        File file = new File("src/test/resources/input.txt");
        FileInputStream input = new FileInputStream(file);
        try {
            MultipartFile multipartFile =
                    new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
