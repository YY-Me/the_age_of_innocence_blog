package cn.oauth2.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.commons.model.VerifyCode;
import cn.commons.utils.MiniConstant;
import cn.oauth2.utils.RedisUtil;
import cn.oauth2.utils.vercode.DrawIdentifyImgUtil;
import io.swagger.annotations.ApiOperation;
import sun.misc.BASE64Encoder;

/**
 * 
 * @ClassName:  VerifyCodeController   
 * @Description: 验证码控制器
 * @author: yuyong 
 * @date:   2018年9月23日 下午1:46:18   
 *     
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 
 * @note: 注意：本内容仅限于xxx公司内部传阅，禁止外泄以及用于其他的商业目
 */
@RestController
public class VerifyCodeController {

    @Resource
    RedisUtil redisUtil;

    @GetMapping("/oauth2/verCode")
    @ApiOperation(value = "获取验证码")
    VerifyCode getCode(String histCode) throws IOException {
        if (StringUtils.isNotBlank(histCode)) {
            redisUtil.del(histCode);
        }
        DrawIdentifyImgUtil idCode = new DrawIdentifyImgUtil();
        String code = DrawIdentifyImgUtil.genCodeStr(4);
        BufferedImage img = idCode.drawImg(code);
        String codeStr = this.encodeToString(img);
        //redis存储
        String id = MiniConstant.getVerifyCodeBycodeKey(UUID.randomUUID().toString().replaceAll("-", ""));
        VerifyCode verifyCode = new VerifyCode(id, codeStr);
        redisUtil.set(id, code, 180);
        return verifyCode;
    }

    /**
     * 将图片转换成base64格式进行存储
     * @param imagePath
     * @return
     */
    public String encodeToString(BufferedImage img) throws IOException {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "PNG", bos);
            byte[] imageBytes = bos.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = "data:image/jpg;base64," + encoder.encode(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
}
