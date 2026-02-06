package fun.tools;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESTools {
    /** 
     * 加密函数 
     * @param content   加密的内容 
     * @param strKey    密钥 
     * @return          返回二进制字符数组
     */
    static String enCrypt(String content, String strKey) throws Exception
    {
        KeyGenerator keygen;
        SecretKey desKey;
        Cipher c;
        byte[] cByte;

        keygen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(strKey.getBytes());
        keygen.init(128, random);
        
        desKey = keygen.generateKey();
        c = Cipher.getInstance("AES");
        
        c.init(Cipher.ENCRYPT_MODE, desKey);
        
        cByte = c.doFinal(content.getBytes(StandardCharsets.UTF_8));
        
        return parseByte2HexStr(cByte);
    }
      
    /** 解密函数 
     * @param content  加密过的二进制字符数组
     * @param strKey  密钥 
     * @return String
     */
    static String deCrypt(String content, String strKey) throws Exception
    {
    	byte[] src = parseHexStr2Byte(content);
        KeyGenerator keygen;
        SecretKey desKey;
        Cipher c;
        byte[] cByte;
        keygen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(strKey.getBytes());
        keygen.init(128, random);
        desKey = keygen.generateKey();
        c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, desKey);
        assert src != null;
        cByte = c.doFinal(src);
        return new String(cByte, StandardCharsets.UTF_8);
    }
    
    /**2进制转化成16进制 
     * @param buf 二进制码
     * @return String
     */
    private static String parseByte2HexStr(byte[] buf)
    {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    
    /**将16进制转换为二进制 
     * @param hexStr 16进制
     * @return static
     */
    private static byte[] parseHexStr2Byte(String hexStr)
    {
        if (hexStr.length() < 1)
        	return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

   public static void main(String[] args) throws Exception
   {
       String Key = "mw@f#kle^wf3j*fm3w2De3Lr4&UJM^YHN";
       String str = "974959217146466304";
       String encrytStr;

       //加密过的二进制数组转化成16进制的字符串
       encrytStr = enCrypt(str, Key);
       System.out.println("加密后："+encrytStr);

        //加密过的16进制的字符串转化成二进制数组
        System.out.println("解密后："+deCrypt(encrytStr, Key));

       System.out.println("原文是："+str);
   }
}