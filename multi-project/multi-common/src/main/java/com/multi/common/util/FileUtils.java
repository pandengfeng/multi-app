package com.multi.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * 文件操作类
 * 2017-12-21
 * @author pandengfeng
 */
public class FileUtils {

    public static final String LINE_SEPARATOR = "\r\n";

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    private static final String UTF8 = "utf-8";

    private FileUtils() {

    }
    /**
     * 读取文件
     * @param filePath 文件路径
     * @return	StringBuilder类型
     * @throws IOException IOException
     */
    public static StringBuilder readFile(String filePath) throws IOException {
        File file = new File(filePath);
        return readFile(file, UTF8);
    }
    
    /**
     * 读取文件
     * @param file 文件对象
     * @param encoding	编码
     * @return	StringBuilder对象
     * @throws IOException	IOException
     */
    public static StringBuilder readFile(File file, String encoding) throws IOException {
        StringBuilder builder = null;
        if (!file.exists() || file.isDirectory()) {
            throw new IllegalArgumentException("The file is not exists or is a directory. File:" + file.getAbsolutePath());
        }
        BufferedReader reader = null;
        try {
        	FileInputStream inputStream = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(inputStream,encoding));
            builder = new StringBuilder();
            String readLine = null;
            while ((readLine = reader.readLine()) != null) {
                builder.append(readLine + LINE_SEPARATOR);
            }
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
        return builder;
    }
    
    /**
	 * 向指定文件写入内容
	 * @param	b	需要写入的内容byte[]
	 * @param	fileName	包含文件全路径的文件名
	 * @throws IOException	IOException
	 */
	public static void output(byte[] b,String fileName)throws IOException {
		File f = new File(fileName);
		OutputStream out = new FileOutputStream(f);
		out.write(b);
		out.close();
	}
	
	/**
	 * 读取指定文件里的内容
	 * @param path	路径
	 * @return	byte[]字节对象
	 * @throws IOException	IOException
	 */
	public static byte[] input(String path) throws IOException {
		File f = new File(path);
		InputStream is = new FileInputStream(f);
		byte[] b = new byte[is.available()];
		int islen = is.available();
		// 每次读取的偏移量
		int offset = 1024;
		int count = islen / offset;
		for (int i = 0; i < count; i++) {
			while ((is.read(b)) != -1) {
				is.read(b, offset * i, offset * (i + 1));
			}
		}
		is.read(b, offset * count, is.available());
		is.close();
		return b;
	}

	/**
	 * 将f1复制到path2路径,文件名为fileName
	 * @param f1 要复制的文件对象
	 * @param path2	指定路径
	 * @param fileName	生成的文件名
	 * @throws IOException IOException
	 */
	public static void copy(File f1, String path2, String fileName) throws IOException {
		File f2 = new File(path2);
		if (!f2.exists()) {
			f2.mkdir();
		}

		InputStream input = new FileInputStream(f1);
		OutputStream output = new FileOutputStream(path2 + "/" + fileName);
		int t = 0;
		byte[] buf = new byte[4096];
		while ((t = input.read(buf)) != (-1)) {
			output.write(buf,0,t);
		}
		input.close();
		output.close();
	}


	/**
	 * 删除指定文件
	 * @param fileName 文件路径
	 */
	public static void delete(String fileName) {
		File f = new File(fileName);
		if (f.exists()) {
			f.delete();
		} else {
			System.out.println("文件不存在");
		}
	}
	
	/**
	 * 删除文件夹	递归
	 * @param dir	要删除的文件对象
	 */
	public static void deletedir(File dir) {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				deletedir(files[i]);
			} else {
				files[i].delete();
			}
		}
		dir.delete();
	}
	
	/**
	 * 创建文件夹
	 * @param path 路径
	 */
	public static void createdir(String path){
		File dir = null;
		dir = new File(path);
		if (!dir.exists()){
			dir.mkdir();
		}
	}
	
	/**
	 * 创建文件夹
	 * @param path 路径
	 */
	public static void createdirs(String path){
		File dir = null;
		dir = new File(path);
		if (!dir.exists()){
			dir.mkdirs();
		}
	}
	

	
}
