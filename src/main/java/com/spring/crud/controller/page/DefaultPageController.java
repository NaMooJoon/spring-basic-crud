package com.spring.crud.controller.page;

import com.spring.crud.util.FileUpload;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
            logger.info("FileNotFoundException / ");
        } catch (IOException e) {
            logger.info("IOException /");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.info("final Exception / IOException");
                }
            }
        }
        return return_byte;
    }
}
