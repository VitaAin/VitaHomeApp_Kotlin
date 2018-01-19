package com.vita.home.utils;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static final String TAG = "FileUtils";

    private FileUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 文件是否存在
     *
     * @param path
     * @return
     */
    public static boolean isExist(String path) {
        return !TextUtils.isEmpty(path) && new File(path).exists();
    }

    /**
     * 文件夹是否为空
     *
     * @param dirPath
     * @return
     */
    public static boolean isDirEmpty(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) return true;

        File dir = new File(dirPath);
        if (!dir.isDirectory()) return true;

        File[] files = dir.listFiles();
        return files == null || files.length == 0;
    }

    /**
     * 取得所有文件
     *
     * @param dirPath
     * @return
     */
    public static File[] getAllFiles(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) return null;

        File dir = new File(dirPath);
        if (!dir.isDirectory()) return null;

        return dir.listFiles();
    }

    /**
     * 取得当前文件夹下所有文件的路径
     *
     * @param dirPath
     * @return
     */
    public static List<String> getAllFilesAbsolutePath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) return null;

        File dir = new File(dirPath);
        if (!dir.isDirectory()) return null;

        File[] files = dir.listFiles();
        if (files == null || files.length == 0) return null;

        List<String> filesPath = new ArrayList<>(0);
        for (File file : files) {
            filesPath.add(file.getAbsolutePath());
        }

        return filesPath;
    }

    /**
     * 取得当前文件夹下所有一级文件/文件夹的文件/文件夹名
     *
     * @param dirPath
     * @return
     */
    public static List<String> getAllFilesName(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) return null;

        File dir = new File(dirPath);
        if (!dir.isDirectory()) return null;

        File[] files = dir.listFiles();
        if (files == null || files.length == 0) return null;

        List<String> filesName = new ArrayList<>(0);
        for (File file : files) {
            filesName.add(file.getName());
        }

        return filesName;
    }

    /**
     * 如果文件不存在则创建文件
     *
     * @param path
     * @return
     */
    public static boolean createFileIfNotExist(String path) {
        if (TextUtils.isEmpty(path)) return false;

        Log.d(TAG, ">>>>>>>>>>> path:: " + path);
        File file = new File(path);
        if (file.isDirectory()) {
            Log.e(TAG, "File is directory.");
//            System.out.println("File is directory.");
            return false;
        }

        if (file.isFile() && file.exists()) {
            Log.e(TAG, "File is existent.");
//            System.out.println("File is existent.");
            return false;
        }

        boolean newFile = false;
        try {
            Log.d(TAG, ">>>>>>>>>>> create newFile");
            newFile = file.createNewFile();
            Log.d(TAG, ">>>>>>>>>>> newFile:: " + newFile);
        } catch (Exception e) {
            Log.e(TAG, "createFileIfNotExist:: " + e.getMessage());
//            System.out.println(e.getMessage());
        }
        return newFile;
    }

    /**
     * 删除文件
     *
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {
        if (TextUtils.isEmpty(path)) return false;

        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File not exist.");
            return false;
        }
        return file.delete();
    }

    /**
     * 删除文件夹
     */
    public static void deleteDir(String path) {
        if (TextUtils.isEmpty(path)) return;

        File file = new File(path);
        if (!file.exists()) {
            Log.e(TAG, "Dir not exist.");
            return;
        }
        if (file.isDirectory()) {
            clearDirExceptDir(path);
        }
        deleteFile(path);
    }

    /**
     * 清除文件夹下所有文件，不包括文件夹
     *
     * @param dirPath
     * @return
     */
    public static boolean clearDirExceptDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.isDirectory()) return false;

        File[] files = dir.listFiles();

        if (files == null || files.length == 0) return false;
        for (File file : files) {
            if (file.isDirectory())
                clearDirExceptDir(file.getAbsolutePath());
            else
                deleteFile(file.getAbsolutePath());
        }
        return true;
    }

    /**
     * 清除文件夹下所有文件，包括文件夹
     *
     * @param dirPath
     * @return
     */
    public static boolean clearDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.isDirectory()) return false;

        File[] files = dir.listFiles();

        if (files == null || files.length == 0) return false;
        for (File file : files) {
            if (file.isDirectory())
                clearDir(file.getAbsolutePath());
            deleteFile(file.getAbsolutePath());
        }
        return true;
    }

    /**
     * 取得文件大小
     *
     * @param path
     * @return
     */
    public static long getFileSize(String path) {
        if (TextUtils.isEmpty(path)) return 0;

        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File not exist.");
            return 0;
        }

        return file.length();
    }

    /**
     * 读取文件为字符串
     *
     * @param path
     * @return
     */
    public static String readFile(String path) {
        if (TextUtils.isEmpty(path)) return null;

        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(path);
            baos = new ByteArrayOutputStream();

            int n = 0;
            byte[] buf = new byte[1024 * 8];
            while ((n = fis.read(buf)) != -1) {
                baos.write(buf, 0, n);
            }

            return baos.toString("UTF-8");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String readFile(InputStream is) {
        if (is == null) return null;

        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();

            int n = 0;
            byte[] buf = new byte[1024 * 8];
            while ((n = is.read(buf)) != -1) {
                baos.write(buf, 0, n);
            }

            return baos.toString("UTF-8");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 读取一行数据
     *
     * @param path
     * @return
     */
    public static byte[] readFileByLine(String path) {
        if (TextUtils.isEmpty(path)) return null;

        File file = new File(path);
        BufferedReader reader = null;
        ByteArrayOutputStream baos = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            baos = new ByteArrayOutputStream();

            byte[] buf = new byte[1024 * 8];
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                baos.write(buf, 0, tempString.length());
            }

            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 写文件
     *
     * @param path
     * @param data
     * @param isAppend
     * @return
     */
    public static boolean writeFile(String path, String data, boolean isAppend) {
        if (TextUtils.isEmpty(data)) return false;

        boolean fileIfNotExist = createFileIfNotExist(path);
        Log.e(TAG, "writeFile: fileIfNotExist:: " + fileIfNotExist);

        FileWriter fw = null;
        try {
            fw = new FileWriter(path, isAppend);
            fw.write(data, 0, data.length());
            fw.flush();

            return true;
        } catch (Exception e) {
            Log.e(TAG, "error:: " + e.getMessage());
//            System.out.println(e.getMessage());
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean writeFile(File file, InputStream is, boolean isAppend) {
        OutputStream os = null;
        try {
            makeDirs(file.getAbsolutePath());
            os = new FileOutputStream(file, isAppend);
            byte data[] = new byte[1024];
            int len = -1;
            while ((len = is.read(data)) != -1) {
                os.write(data, 0, len);
            }
            os.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean writeFile(File file, InputStream is) {
        return writeFile(file, is, false);
    }

    public static boolean writeFile(String path, InputStream is, boolean isAppend) {
        return writeFile(path != null ? new File(path) : null, is, isAppend);
    }

    public static boolean writeFile(String path, InputStream is) {
        return writeFile(path, is, false);
    }

    public static boolean writeFile(String path, String data) {
        return writeFile(path, data, false);
    }

    /**
     * 移动文件
     *
     * @param srcFile
     * @param destFile
     * @return
     */
    public static boolean moveFile(File srcFile, File destFile) {
        if (srcFile == null || destFile == null) return false;

//        boolean rename = srcFile.renameTo(destFile);
//        if (!rename) {
        boolean copyRes = copyFile(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
        boolean delRes = deleteFile(srcFile.getAbsolutePath());
        return copyRes && delRes;
//        }

//        return false;
    }

    public static boolean moveFile(String srcFilePath, String destFilePath) {
        return moveFile(new File(srcFilePath), new File(destFilePath));
    }

    /**
     * 复制文件
     *
     * @param sourceFilePath
     * @param destFilePath
     * @return
     */
    public static boolean copyFile(String sourceFilePath, String destFilePath) {
        if (TextUtils.isEmpty(destFilePath) || TextUtils.isEmpty(destFilePath)) return false;

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourceFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return writeFile(destFilePath, inputStream);
    }

    /**
     * 取得文件名
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePos = filePath.lastIndexOf(File.separator);
        return (filePos == -1) ? filePath : filePath.substring(filePos + 1);
    }

    /**
     * 取得文件夹名称
     *
     * @param filePath
     * @return
     */
    public static String getFolderName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePos = filePath.lastIndexOf(File.separator);
        return (filePos == -1) ? "" : filePath.substring(0, filePos + 1);
    }

    /**
     * 创建文件路径
     *
     * @param filePath
     * @return
     */
    public static boolean makeDirs(String filePath) {
//        Log.d(TAG, "filePath:: " + filePath);
        String folderName = getFolderName(filePath);
        if (TextUtils.isEmpty(folderName)) {
            return false;
        }

//        Log.d(TAG, "folder:: " + folderName);
        File folder = new File(folderName);
//        Log.d(TAG, "folder:: " + folder.getAbsolutePath());
        boolean isExists = folder.exists();
        boolean isDir = folder.isDirectory();
        boolean isMkdirs = folder.mkdirs();
        Log.d(TAG, isExists + ", " + isDir + ", " + isMkdirs);
        return (isExists && isDir) || isMkdirs;
    }

    /**
     * 创建文件路径
     *
     * @param filePath
     * @return
     */
    public static boolean makeFolders(String filePath) {
        return makeDirs(filePath);
    }

    private static DecimalFormat fileIntegerFormat = new DecimalFormat("#0");
    private static DecimalFormat fileDecimalFormat = new DecimalFormat("#0.#");

    /**
     * 单位换算
     *
     * @param size      单位为byte
     * @param isInteger 是否返回取整的单位
     * @return 转换后的大小+单位
     */
    public static String formatFileSize(long size, boolean isInteger) {
        DecimalFormat df = isInteger ? fileIntegerFormat : fileDecimalFormat;
        String fileSizeString = "0M";
        if (size < 1024 && size > 0) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1024 * 1024) {
            fileSizeString = df.format((double) size / 1024) + "K";
        } else if (size < 1024 * 1024 * 1024) {
            fileSizeString = df.format((double) size / (1024 * 1024)) + "M";
        } else {
            fileSizeString = df.format((double) size / (1024 * 1024 * 1024)) + "G";
        }
        return fileSizeString;
    }
}
