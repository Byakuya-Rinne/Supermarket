package com.zhongbei.servlet.utils;
import org.apache.commons.codec.digest.DigestUtils;


public class MD5Utils {
    public static String md5Digest(String source, Integer salt) {

        // 将密码字符串进行转换 转换为字符数组
        char[] chars = source.toCharArray();
        // 将每个字符都加上一个盐值
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + salt);
        }
        // 将加了盐中的字符数组转换为字符串
        String newPwd = new String(chars);
        System.out.println("newPwd:" + newPwd);

        // 使用md5工具将其加密 加密后可以返回例如32位的16进制小写的字符
        String md5Str = DigestUtils.md5Hex(newPwd);
        System.out.println(md5Str);

        return md5Str;
    }

}
