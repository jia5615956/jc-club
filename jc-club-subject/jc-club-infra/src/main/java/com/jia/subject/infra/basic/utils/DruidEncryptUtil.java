package com.jia.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 *
 * 数据库加密utils
 *
 */
public class DruidEncryptUtil {

    private static String publicKey;
    private static String privateKey;

    static {
        try {
            String[] strings = ConfigTools.genKeyPair(521);
            privateKey = strings[0];
            System.out.println("privateKey:"+ privateKey);
            publicKey = strings[1];
            System.out.println("publicKey:"+ publicKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String plainText) throws Exception {
        String encrypt = ConfigTools.encrypt(privateKey, plainText);
        //System.out.println("encrypt:"+ encrypt);
        return encrypt;
    }
    public static String decrypt(String encryptText) throws Exception {
        String decrypt = ConfigTools.decrypt(publicKey, encryptText);
        //System.out.println("decrypt:"+ decrypt);
        return decrypt;
    }


    public static void main(String[] args) throws Exception {
        String passwordEncrypt = encrypt("5615956");
        System.out.println("passwordEncrypt:"+ passwordEncrypt);
        String usernameEncrypt = encrypt("jiadw.91y.xyz");
        System.out.println("usernameEncrypt:"+ usernameEncrypt);

    }


}
