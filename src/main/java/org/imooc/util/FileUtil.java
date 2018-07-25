package org.imooc.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * Created by RookieWangZhiWei on 2018/7/25.
 */
public class FileUtil {

    public static String save(MultipartFile file,String savePath) throws IOException {
        if (file!= null && file.getSize()>0){
            File fileFolder  = new File(savePath);
            if (!fileFolder.exists()){
                fileFolder.mkdirs();
            }
            File saveFile = getFile(savePath,file.getOriginalFilename());
            file.transferTo(saveFile);
            return saveFile.getName();
        }
        return null;
    }

    public static boolean delete(String filePath){
        File file = new File(filePath);

        if (file.isFile()){
            file.delete();
            return true;
        }
        return false;
    }


    private static File getFile(String savePath,String originalFilename){
        String fileName = System.currentTimeMillis() + "_" + originalFilename;
        File file = new File(savePath + fileName);
        if (file.exists()){
            return getFile(savePath,originalFilename);
        }
        return file;
    }
}
