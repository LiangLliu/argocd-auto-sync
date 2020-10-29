package com.edwin.gitops.utils;

import com.edwin.gitops.utils.yaml2props.YamlToProps;

public class GitOpsYamlUtil {

    public static String toProperties(String content) {
        return YamlToProps.fromContent(content).convert();
    }


}
