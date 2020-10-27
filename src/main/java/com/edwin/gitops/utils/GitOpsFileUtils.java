package com.edwin.gitops.utils;


import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class GitOpsFileUtils {

    private static final String DIRECTORY_PATH = "temp-files/";

    /**
     * 替换文本文件中的 非法字符串
     */
    public static void replaceTextContent(String path, String target, String newContent) {

        String filePath = DIRECTORY_PATH + path;

        try {
            File file = new File(filePath);
            List<String> listByFileLine = FileUtils.readLines(file, StandardCharsets.UTF_8);

            List<String> newLines = listByFileLine.stream()
                    .map(it -> {
                                if (it.contains(target)) {
                                    int targetIndex = it.indexOf(target);
                                    it = it.substring(0, targetIndex + target.length()) + newContent + "\n";
                                }
                                return it;
                            }
                    ).collect(Collectors.toList());
            FileUtils.writeLines(file, "UTF-8", newLines, false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过content创建文件
     */
    public static void createTempFileByContent(String contentName, String data) {
        String fileName = DIRECTORY_PATH + contentName;
        try {
            FileUtils.writeStringToFile(new File(fileName), data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileContentBase64(String path) throws IOException {
        String filePath = DIRECTORY_PATH + path;
        String resourceFile = getFileToString(filePath);
        return Base64.getEncoder().encodeToString(resourceFile.getBytes());
    }

    public static void deleteFile(String path) {
        String fileName = DIRECTORY_PATH + path;
        FileUtils.deleteQuietly(new File(fileName));
    }

    private static String getFileToString(String filePath) throws IOException {
        return FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
    }


}
