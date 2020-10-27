package com.edwin.gitops.utils;

import com.edwin.gitops.domain.content.Content;

import java.util.Map;

public class ContentUtil {
    public static String replaceData(Content content, Map<String, String> replaceMap) {

        String contentName = content.getName();

        String contentByString = content.getContentByString();
        String propsString = GitOpsYamlUtil.toProperties(contentByString);
        String strOfReplace = GitOpsPropertiesUtil.replaceValue(propsString, replaceMap);

        return  GitOpsPropertiesUtil.toYaml(strOfReplace);
    }

}
