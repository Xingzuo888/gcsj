package com.example.gcsj3.File;

import android.content.Context;

/**
 * Created by tangy on 2018/12/24.
 */

public class FileProxy implements FileInter{
    WriteFileWithStream write = new WriteFileWithStream();
    ReadFileWithStream read = new ReadFileWithStream();
    @Override
    public String getFile(Context context) {
        return read.getFile(context);
    }

    @Override
    public void setFile(String page, Context context) {
        write.WriteFile(page, context);
    }
}
