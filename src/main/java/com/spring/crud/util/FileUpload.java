package com.spring.crud.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
    private static final String BUCKET_NAME = "test-user-pub";
    private static final String READ_S3_URL = "https://test-user-pub.s3.ap-northeast-2.amazonaws.com/";
    private static final String PROJECT_FOLDER_NAME = "test";
    private static final String ACCESS_KEY= "";
    private static final String SECRET_KEY = "";

    public static String s3(MultipartFile file) throws IOException {
        String returnValue = "";
        try {
            AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
            AmazonS3 s3 = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.AP_NORTHEAST_2)
                    .build();

            if (file != null) {
                Date date = new Date();
                String temp_date = date.getTime() + "";
                String filename = setFileName(file);
                String extension = FilenameUtils.getExtension(filename);
                if (extension == null || "".equals(extension)) {
                    extension = "nnn";
                }

                if (!(filename == null || "".equals(filename))) {
                    filename = filename.replace(" ", "");
                    String uuid = UUID.randomUUID().toString().replace("-", "");
                    filename = uuid + "_file" + "." + extension;

                    returnValue = temp_date + "_" + filename;

                    ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentLength(file.getSize());
                    metadata.setContentType(file.getContentType());

                    try {
                        PutObjectRequest putObjectRequest = new PutObjectRequest(
                                BUCKET_NAME + "/" + PROJECT_FOLDER_NAME,
                                returnValue,
                                file.getInputStream(),
                                metadata
                        );
                        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
                        s3.putObject(putObjectRequest);
                        System.out.println("Done!");

                        returnValue = READ_S3_URL + PROJECT_FOLDER_NAME + "/" + temp_date + "_" + filename;
                        System.out.println("returnValue = " + returnValue);
                    } catch (AmazonServiceException e) {
                        System.err.println(e.getErrorMessage());
                        System.exit(1);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return returnValue;
    }

    public static String path(HttpServletRequest request) throws IOException {
        // getRealPath("/"): Gets the real path corresponding to the given virtual path ("/").
        String root_path = request.getSession().getServletContext().getRealPath("/");

        // 서버 업로드 이후
        root_path = root_path.replace("wtpwebapps", "uploadfiles");
        root_path = root_path.replace("webapps", "uploadfiles");
        root_path = root_path.replace("\\", "/");

        if (root_path.contains("C:/")) { // for windows
            root_path = "C:/workspace";
        } else {
            root_path = "Users/workspace";
        }
        String attach_path = "/uploadfiles/";
        return root_path + attach_path;
    }

    /**
     * Local Server에 파일을 등록하는 메서드
     * @param file
     * @param request
     * @return file path URL
     * @throws IOException
     */
    public static String local(MultipartFile file, HttpServletRequest request) throws IOException {
        String returnValue = "";

        try {
            String root_path = path(request);
            setDir(root_path);
            String filename = setFileName(file);
            /**
             * 여기서 사실 파일이 유효한지도 확인해야함.
             * 그리고 용량이 너무 크다면 거절할 수 도 있음
             * 파일의 용량이 너무 클 경우 이미지를 3개에서 6개 정도의 형태로 저장
             * 1. 썸네일 용 : 크기를 극단적으로 줄임, 가로세로 50px으로 충분히 보임
             * 2. 중간 정도 해상도
             * 3. 고해상도
             * 4. 원본
             * ...
             */
            FileCopyUtils.copy(file.getBytes(), new File(root_path + filename));
            returnValue = "/uploadfile/" + filename;
        } catch (IOException e) {
            System.err.println("Exception : " + e);
        }

        return returnValue;
    }

    private static String setFileName(MultipartFile file) {
        String result = "";
        if (!(file == null || "".equals(file.getOriginalFilename() + ""))) {
            Date date = new Date();
            String temp_date = date.getTime() + "";
            String filename = file.getOriginalFilename(); // file.jpg
            String extension = FilenameUtils.getExtension(filename); // jpg
            if (extension == null || "".equals(extension)) {
                extension = "nnn";
            }

            filename = "file" + "." + extension;
            result = temp_date + "_" + filename; // 92032037230952_file.jpg
        }
        return result;
    }

    private static void setDir(String root_path) throws IOException {
        File newfile = new File(root_path);
        if(!newfile.exists()) {
            newfile.mkdirs();
        }
    }
}
