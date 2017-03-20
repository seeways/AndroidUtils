package com.jty.myutils.utils;

import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author TaoYuan
 * @time 2017/3/17 0017
 * @desc 加密工具类
 */

public class EncryptUtils {

    /*********   MD5系列   **************/


        /**
         * String To MD5
         *
         * @param data string data
         * @return hex data
         */
        public static String String2Md5(String data) {
            return byte2Md5(data.getBytes());
        }

        /**
         * String To MD5
         *
         * @param data string data
         * @param salt salt
         * @return hex data
         */
        public static String String2Md5(String data, String salt) {
            return bytes2HexString(encryptMD5((data + salt).getBytes()));
        }

        /**
         * byte To MD5
         *
         * @param data byte[] data
         * @return hex data
         */
        public static String byte2Md5(byte[] data) {
            return bytes2HexString(encryptMD5(data));
        }

        /**
         * byte To MD5
         *
         * @param data byte[] data
         * @param salt salt
         * @return hex data with salt
         */
        public static String byte2Md5(byte[] data, byte[] salt) {
            if (data == null || salt == null) return null;
            byte[] dataSalt = new byte[data.length + salt.length];
            System.arraycopy(data, 0, dataSalt, 0, data.length);
            System.arraycopy(salt, 0, dataSalt, data.length, salt.length);
            return bytes2HexString(encryptMD5(dataSalt));
        }

        /**
         * 对MD5 进行hash加密
         *
         * @param data 明文字节数组
         * @return 密文字节数组
         */
        public static byte[] encryptMD5(byte[] data) {
            return hashTemplate(data, "MD5");
        }

    /** MD5文件处理 **/

        /**
         * MD5加密文件
         *
         * @param filePath 文件路径
         * @return 文件的16进制密文
         */
        public static String encryptMD5File2String(String filePath) {
            File file = StringUtils.isEmpty(filePath) ? null : new File(filePath);
            return encryptMD5File2String(file);
        }

        /**
         * MD5加密文件
         *
         * @param filePath 文件路径
         * @return 文件的MD5校验码
         */
        public static byte[] encryptMD5File(String filePath) {
            File file = StringUtils.isEmpty(filePath) ? null : new File(filePath);
            return encryptMD5File(file);
        }

        /**
         * MD5加密文件
         *
         * @param file 文件
         * @return 文件的16进制密文
         */
        public static String encryptMD5File2String(File file) {
            return bytes2HexString(encryptMD5File(file));
        }

        /**
         * MD5加密文件
         *
         * @param file 文件
         * @return 文件的MD5校验码
         */
        public static byte[] encryptMD5File(File file) {
            if (file == null) return null;
            FileInputStream fis = null;
            DigestInputStream digestInputStream;
            try {
                fis = new FileInputStream(file);
                MessageDigest md = MessageDigest.getInstance("MD5");
                digestInputStream = new DigestInputStream(fis, md);
                byte[] buffer = new byte[256 * 1024];
                while (digestInputStream.read(buffer) > 0) ;
                md = digestInputStream.getMessageDigest();
                return md.digest();
            } catch (NoSuchAlgorithmException | IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        /**
         * SHA1加密
         *
         * @param data 明文字符串
         * @return 16进制密文
         */
        public static String encryptSHA1ToString(String data) {
            return encryptSHA1ToString(data.getBytes());
        }

        /**
         * SHA1加密
         *
         * @param data 明文字节数组
         * @return 16进制密文
         */
        public static String encryptSHA1ToString(byte[] data) {
            return bytes2HexString(encryptSHA1(data));
        }

        /**
         * SHA1加密
         *
         * @param data 明文字节数组
         * @return 密文字节数组
         */
        public static byte[] encryptSHA1(byte[] data) {
            return hashTemplate(data, "SHA1");
        }

        /**
         * SHA224加密
         *
         * @param data 明文字符串
         * @return 16进制密文
         */
        public static String encryptSHA224ToString(String data) {
            return encryptSHA224ToString(data.getBytes());
        }

        /**
         * SHA224加密
         *
         * @param data 明文字节数组
         * @return 16进制密文
         */
        public static String encryptSHA224ToString(byte[] data) {
            return bytes2HexString(encryptSHA224(data));
        }

        /**
         * SHA224加密
         *
         * @param data 明文字节数组
         * @return 密文字节数组
         */
        public static byte[] encryptSHA224(byte[] data) {
            return hashTemplate(data, "SHA224");
        }

        /**
         * SHA256加密
         *
         * @param data 明文字符串
         * @return 16进制密文
         */
        public static String encryptSHA256ToString(String data) {
            return encryptSHA256ToString(data.getBytes());
        }

        /**
         * SHA256加密
         *
         * @param data 明文字节数组
         * @return 16进制密文
         */
        public static String encryptSHA256ToString(byte[] data) {
            return bytes2HexString(encryptSHA256(data));
        }

        /**
         * SHA256加密
         *
         * @param data 明文字节数组
         * @return 密文字节数组
         */
        public static byte[] encryptSHA256(byte[] data) {
            return hashTemplate(data, "SHA256");
        }

        /**
         * SHA384加密
         *
         * @param data 明文字符串
         * @return 16进制密文
         */
        public static String encryptSHA384ToString(String data) {
            return encryptSHA384ToString(data.getBytes());
        }

        /**
         * SHA384加密
         *
         * @param data 明文字节数组
         * @return 16进制密文
         */
        public static String encryptSHA384ToString(byte[] data) {
            return bytes2HexString(encryptSHA384(data));
        }

        /**
         * SHA384加密
         *
         * @param data 明文字节数组
         * @return 密文字节数组
         */
        public static byte[] encryptSHA384(byte[] data) {
            return hashTemplate(data, "SHA384");
        }

        /**
         * SHA512加密
         *
         * @param data 明文字符串
         * @return 16进制密文
         */
        public static String encryptSHA512ToString(String data) {
            return encryptSHA512ToString(data.getBytes());
        }

        /**
         * SHA512加密
         *
         * @param data 明文字节数组
         * @return 16进制密文
         */
        public static String encryptSHA512ToString(byte[] data) {
            return bytes2HexString(encryptSHA512(data));
        }

        /**
         * SHA512加密
         *
         * @param data 明文字节数组
         * @return 密文字节数组
         */
        public static byte[] encryptSHA512(byte[] data) {
            return hashTemplate(data, "SHA512");
        }

        /**
         * hash加密模板
         *
         * @param data      数据
         * @param algorithm 加密算法
         * @return 密文字节数组
         */
        private static byte[] hashTemplate(byte[] data, String algorithm) {
            if (data == null || data.length <= 0) return null;
            try {
                MessageDigest md = MessageDigest.getInstance(algorithm);
                md.update(data);
                return md.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }



        /** AES系列 **/
        /**
         * AES转变
         * <p>法算法名称/加密模式/填充方式</p>
         * <p>加密模式有：电子密码本模式ECB、加密块链模式CBC、加密反馈模式CFB、输出反馈模式OFB</p>
         * <p>填充方式有：NoPadding、ZerosPadding、PKCS5Padding</p>
         * 因为相比DES，目前AES更好一些，所以DES可用AES替代
         */
        public static        String AES_Transformation = "AES/ECB/NoPadding";
        private static final String AES_Algorithm      = "AES";


        /**
         * AES加密后转为Base64编码
         *
         * @param data 明文
         * @param key  16、24、32字节秘钥
         * @return Base64密文
         */
        public static byte[] encryptAES2Base64(byte[] data, byte[] key) {
            return base64Encode(encryptAES(data, key));
        }

        /**
         * AES加密后转为16进制
         *
         * @param data 明文
         * @param key  16、24、32字节秘钥
         * @return 16进制密文
         */
        public static String encryptAES2HexString(byte[] data, byte[] key) {
            return bytes2HexString(encryptAES(data, key));
        }

        /**
         * AES加密
         *
         * @param data 明文
         * @param key  16、24、32字节秘钥
         * @return 密文
         */
        public static byte[] encryptAES(byte[] data, byte[] key) {
            return desTemplate(data, key, AES_Algorithm, AES_Transformation, true);
        }

        /**
         * AES解密Base64编码密文
         *
         * @param data Base64编码密文
         * @param key  16、24、32字节秘钥
         * @return 明文
         */
        public static byte[] decryptBase64AES(byte[] data, byte[] key) {
            return decryptAES(base64Decode(data), key);
        }

        /**
         * AES解密16进制密文
         *
         * @param data 16进制密文
         * @param key  16、24、32字节秘钥
         * @return 明文
         */
        public static byte[] decryptHexStringAES(String data, byte[] key) {
            return decryptAES(hexString2Bytes(data), key);
        }

        /**
         * AES解密
         *
         * @param data 密文
         * @param key  16、24、32字节秘钥
         * @return 明文
         */
        public static byte[] decryptAES(byte[] data, byte[] key) {
            return desTemplate(data, key, AES_Algorithm, AES_Transformation, false);
        }

        /**
         * DES加密模板
         *
         * @param data           数据
         * @param key            秘钥
         * @param algorithm      加密算法
         * @param transformation 转变
         * @param isEncrypt      {@code true}: 加密 {@code false}: 解密
         * @return 密文或者明文，适用于DES，3DES，AES
         */
        public static byte[] desTemplate(byte[] data, byte[] key, String algorithm, String transformation, boolean isEncrypt) {
            if (data == null || data.length == 0 || key == null || key.length == 0) return null;
            try {
                SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
                Cipher cipher = Cipher.getInstance(transformation);
                SecureRandom random = new SecureRandom();
                cipher.init(isEncrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, random);
                return cipher.doFinal(data);
            } catch (Throwable e) {
                e.printStackTrace();
                return null;
            }
        }

        private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        /**
         * byteArr转hexString
         * <p>例如：</p>
         * bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns 00A8
         *
         * @param bytes 字节数组
         * @return 16进制大写字符串
         */
        private static String bytes2HexString(byte[] bytes) {
            if (bytes == null) return null;
            int len = bytes.length;
            if (len <= 0) return null;
            char[] ret = new char[len << 1];
            for (int i = 0, j = 0; i < len; i++) {
                ret[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
                ret[j++] = hexDigits[bytes[i] & 0x0f];
            }
            return new String(ret);
        }


        /**
         * hexString转byteArr
         * <p>例如：</p>
         * hexString2Bytes("00A8") returns { 0, (byte) 0xA8 }
         *
         * @param hexString 十六进制字符串
         * @return 字节数组
         */
        private static byte[] hexString2Bytes(String hexString) {
            if (StringUtils.isEmpty(hexString)) return null;
            int len = hexString.length();
            if (len % 2 != 0) {
                hexString = "0" + hexString;
                len = len + 1;
            }
            char[] hexBytes = hexString.toUpperCase().toCharArray();
            byte[] ret = new byte[len >> 1];
            for (int i = 0; i < len; i += 2) {
                ret[i >> 1] = (byte) (hex2Dec(hexBytes[i]) << 4 | hex2Dec(hexBytes[i + 1]));
            }
            return ret;
        }

        /**
         * hexChar转int
         *
         * @param hexChar hex单个字节
         * @return 0..15
         */
        private static int hex2Dec(char hexChar) {
            if (hexChar >= '0' && hexChar <= '9') {
                return hexChar - '0';
            } else if (hexChar >= 'A' && hexChar <= 'F') {
                return hexChar - 'A' + 10;
            } else {
                throw new IllegalArgumentException();
            }
        }

        /** Base64系列 **/

        /**
         * Base64编码
         */
        private static byte[] base64Encode(byte[] input) {
            return Base64.encode(input, Base64.NO_WRAP);
        }

        /**
         * Base64解码
         */
        private static byte[] base64Decode(byte[] input) {
            return Base64.decode(input, Base64.NO_WRAP);
        }


}
