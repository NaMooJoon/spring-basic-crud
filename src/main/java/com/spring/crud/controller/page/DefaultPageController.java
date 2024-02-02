package com.spring.crud.controller.page;

import com.spring.crud.util.FileUpload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("")
@Controller
public class DefaultPageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/docs")
    public String getSwagger() {
        return "redirect:/swagger-ui/index.html";
    }

    @GetMapping({"", "/", "/index"})
    public String getIndex() {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/uploadfile/{file_name:.+}", method = {RequestMethod.GET, RequestMethod.POST})
    public byte[] getImage(@PathVariable("file_name") String filename, HttpServletRequest request) {
        logger.info("file_name: " + filename);
        byte[] return_byte = null;
        InputStream in = null;
        try {
            String root_path = FileUpload.path(request);
            logger.info("root_path: " + root_path);

            File file = new File(root_path + filename);
            in = new FileInputStream(file);
            return_byte = IOUtils.toByteArray(in);

        } catch (FileNotFoundException e) {
            logger.info("FileNotFoundException / " + filename);
        } catch (IOException e) {
            logger.info("IOException /" + filename);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.info("final Exception / IOException" + filename);
                }
            }
        }
        return return_byte;
    }

    @RequestMapping(value = "/download/{file_name:.+}", method = RequestMethod.GET)
    public void download(@PathVariable("file_name") String filename, @RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Download call: " + filename);

        String root_path = FileUpload.path(request);
        logger.info("root_path: " + root_path);
        File file = new File(root_path + filename);
        // 다음은 response 설정해주는 부분.
        String mimeType = URLConnection.guessContentTypeFromName(filename); // 파일의 mime타입 확인
        if (mimeType == null) { // mime타입이 없는 경우 application/octet-stream으로 설정
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType); // Response에 mimType 설정
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(), "utf-8") +"\"");
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file)); // InputStream 객체
        FileCopyUtils.copy(inputStream, response.getOutputStream()); // inputStream으로 파일을 읽고, outStream으로 파일을 쓴다.

    }
}
