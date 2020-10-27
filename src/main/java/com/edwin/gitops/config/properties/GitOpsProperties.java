package com.edwin.gitops.config.properties;

import com.edwin.gitops.utils.RandomUtil;

public class GitOpsProperties {

    private final String accessTokenPara = "?access_token=";

    private String newBranchName = "branch-" + RandomUtil.generate();

    private String tempDirectoryPath = "temp-files/";

    private String propertiesFilePath = "values-dev.yaml";

    private String defaultBranch = "main";

    private ContentProperties content = new ContentProperties();
    private PullsProperties pulls = new PullsProperties();
    private RefsProperties refs = new RefsProperties();

    public String getAccessTokenPara() {
        return accessTokenPara;
    }

    public String getNewBranchName() {
        return newBranchName;
    }

    public void setNewBranchName(String newBranchName) {
        this.newBranchName = newBranchName;
    }

    public String getTempDirectoryPath() {
        return tempDirectoryPath;
    }

    public void setTempDirectoryPath(String tempDirectoryPath) {
        this.tempDirectoryPath = tempDirectoryPath;
    }

    public String getPropertiesFilePath() {
        return propertiesFilePath;
    }

    public void setPropertiesFilePath(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public ContentProperties getContent() {
        return content;
    }

    public void setContent(ContentProperties content) {
        this.content = content;
    }

    public PullsProperties getPulls() {
        return pulls;
    }

    public void setPulls(PullsProperties pulls) {
        this.pulls = pulls;
    }

    public RefsProperties getRefs() {
        return refs;
    }

    public void setRefs(RefsProperties refs) {
        this.refs = refs;
    }
}
