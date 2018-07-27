package com.haoxie.note.modules.mobile.service;

import com.haoxie.note.modules.mobile.entity.DmUser;
import com.haoxie.note.common.security.Digests;
import com.haoxie.note.common.utils.Encodes;
import com.haoxie.note.modules.mobile.entity.DmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 移动端的各种检验
 */
@Service
public class ValidateUtils {

    @Autowired
    private DmUserService dmUserService;

    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public String entryptPassword(String plainPassword) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    public boolean validatePassword(String plainPassword, String password) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }

    /**
     * 验证用户是否存在
     *
     * @param mobile 手机号
     */
    public boolean validateMobile(String mobile) {
        DmUser dmUser = dmUserService.getUserByPhoneNumber(mobile);
        return dmUser == null;
    }

    /**
     * 验证验证码是否过期
     *
     * @param time 过期时间
     */
    public boolean validateCodeExportTime(Long time) {
        Date data = new Date();
        return data.getTime() < time;
    }
}
