package io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by fubin on 2019-11-17.
 */
public class FastFileCopyUtils {
    public static void fileCopy(File in, File out )
            throws IOException {
        FileChannel inChannel = new FileInputStream( in ).getChannel();
        FileChannel outChannel = new FileOutputStream( out ).getChannel();
        try{
            //windows下加上这一句会复制两次
            //inChannel.transferTo(0, inChannel.size(), outChannel);      // original -- apparently has trouble copying large files on Windows
            // magic number for Windows, 64Mb - 32Kb)
            int maxCount = (64 * 1024 * 1024) - (32 * 1024);
            long size = inChannel.size();
            long position = 0;
            while ( position < size ){
                position += inChannel.transferTo( position, maxCount, outChannel );
            }
        }
        finally{
            if ( inChannel != null ){
                inChannel.close();
            }
            if ( outChannel != null ){
                outChannel.close();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        FastFileCopyUtils.fileCopy(new File(System.getProperty("user.dir")+"/copy-1.txt"),new File("copy-2.txt"));
    }
}
