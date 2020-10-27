package com.edwin.gitops.utils;

import com.edwin.gitops.utils.yaml2props.YamlToProps;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;


public class GitOpsYamlUtil {

    public static String toProperties(String content) {
        return YamlToProps.fromContent(content).convert();
    }

    public static String toProperties(File file, Charset charset) throws IOException {
        String s = FileUtils.readFileToString(file, charset);
        return toProperties(s);
    }


}
