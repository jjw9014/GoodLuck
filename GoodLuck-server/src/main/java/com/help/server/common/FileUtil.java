package com.help.server.common;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.UUID;

@Slf4j
public class FileUtil {
    public static final String BASE_URL = "/app/picture/";

    public static String temp(byte[] file) {
        String tempFileName = UUID.randomUUID().toString();
        File targetFile = new File(BASE_URL);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        try {
            FileOutputStream out = new FileOutputStream(BASE_URL + tempFileName);
            out.write(file);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File src = new File(BASE_URL + tempFileName);

        String md5 = getStringMd5(src);

        File dest = new File(BASE_URL + md5);
        src.renameTo(dest);

        return md5;
    }

    public static boolean clean(String tempFileName) {
        File targetFile = new File(BASE_URL + tempFileName);
        if (targetFile.exists()){
            return targetFile.delete();
        }

        return false;
    }

    public static String getStringMd5(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error("file not found", e);
            return null;
        }

        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}
