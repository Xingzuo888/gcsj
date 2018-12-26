package com.example.gcsj3.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by tangy on 2018/12/26.
 */

public class StreamTools {
    public static String readString(InputStream in){
        String content = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 1;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) != -1){
                baos.write(buffer, 0,len);
            }
            in.close();
            content = new String(baos.toByteArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }
}
