package com.edwin.gitops;

import com.edwin.gitops.client.ContentClient;
import com.edwin.gitops.client.PullRequestClient;
import com.edwin.gitops.client.RefsClient;
import com.edwin.gitops.config.properties.GitOpsProperties;
import com.edwin.gitops.service.GitHubOpsService;
import com.edwin.gitops.service.UpdateTagRequest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;


public class GitOpsApplication {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final GitOpsProperties gitOpsProperties = new GitOpsProperties();

    private static final RefsClient refsClient = new RefsClient(gitOpsProperties, restTemplate);
    private static final ContentClient contentClient = new ContentClient(gitOpsProperties, restTemplate);
    private static final PullRequestClient pullRequestClient = new PullRequestClient(gitOpsProperties, restTemplate);

    public static void main(String[] args) throws IOException {


        System.out.println(getImage());

        System.out.println("----------------args-----------------");
        System.out.println(Arrays.toString(args));
        System.out.println("-------------------------------------");

        UpdateTagRequest request = new UpdateTagRequest();

        String masterBranchSHA = refsClient.getMasterBranchSHA(request.getDeploymentPath(), request.getToken());
        System.out.println(masterBranchSHA);

        GitHubOpsService gitHubOpsService = new GitHubOpsService(pullRequestClient, contentClient, refsClient, gitOpsProperties);

        Map<String, String> replaceMap = Map.of(
                "image.tag", "aaa",
                "replicaCount", "5"
        );

        gitHubOpsService.updateDeploymentTag(request, replaceMap);


    }

    /**
     * http://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20
     */
    public static String getImage() {
        return "\n" +
                "        .__  __  .__         ___.                                  \n" +
                "   ____ |__|/  |_|  |__  __ _\\_ |__             ____ ______  ______\n" +
                "  / ___\\|  \\   __\\  |  \\|  |  \\ __ \\   ______  /  _ \\\\____ \\/  ___/\n" +
                " / /_/  >  ||  | |   Y  \\  |  / \\_\\ \\ /_____/ (  <_> )  |_> >___ \\ \n" +
                " \\___  /|__||__| |___|  /____/|___  /          \\____/|   __/____  >\n" +
                "/_____/               \\/          \\/                 |__|       \\/ ";
    }

}
