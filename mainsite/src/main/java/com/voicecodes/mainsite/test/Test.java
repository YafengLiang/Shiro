package com.voicecodes.mainsite.test;

import com.voicecodes.domain.utils.EncryptAes256;
import com.voicecodes.mainsite.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;

import static com.voicecodes.domain.utils.EncryptAes256.Encrypt;

/**
 * @Author: YafengLiang
 * @Description:
 * @Date: Created in  15:35 2018/6/20
 */

public class Test {

    public static void main(String[] args) throws Exception {
        String password  = EncryptAes256.EncodeHex(Encrypt("123456"));
        System.out.println(password);
    }
}
