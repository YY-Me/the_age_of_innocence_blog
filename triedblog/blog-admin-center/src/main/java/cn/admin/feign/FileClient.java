package cn.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("blog-file-center")
public interface FileClient {

    /**
     * 文章图片上传接口<br>
     * @Title: upload
     * @param @param file
     * @param @param prefix
     * @param @return
     * @return Object
     * @throws
     */
    @PostMapping(path = "/file/upload_file", produces = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Object upload(@RequestPart("file") MultipartFile file, @RequestParam("prefix") String prefix);

    /**
     * 创建文件夹
     * @Title: createFolder
     * @param @param folderName
     * @param @return
     * @return Object
     * @throws
     */
    @PostMapping(path = "/file/create_folder")
    Object createFolder(@RequestParam("folderName") String folderName);

}
