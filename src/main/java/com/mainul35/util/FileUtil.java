package com.mainul35.util;

import com.mainul35.entity.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;

@Component
public class FileUtil {
    @Autowired
    private ImageUtil imageUtil;

    public static File makeDirectory (String path) {
        File dir = Paths.get(path + "//").toFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        System.out.println("Making directory: "+dir.getAbsolutePath());
        return dir;
    }

    public byte[] readAttachmentAsByteArray (Attachment attachment, int size){
        byte[] bs = null;
        try {
            bs = imageUtil.readImage(attachment, size);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return bs;
    }
}
