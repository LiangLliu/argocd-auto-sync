package com.edwin.gitops;

import com.edwin.gitops.client.BranchClient;
import com.edwin.gitops.domain.ParaObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GitOpsApplication {

    private static final String baseUrl = "https://api.github.com/repos/";

    public static void main(String[] args) {

        printLogo();

        ParaObject paraObject = parsePara(args);

        BranchClient branchClient = new BranchClient(paraObject);

        try {
            branchClient.updateDeploymentTag();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("------------------finish------------------------\n");

    }

    private static ParaObject parsePara(String[] paras) {

        ParaObject paraObject = new ParaObject();
        paraObject.setUrl(baseUrl + paras[0]);
        paraObject.setToken(paras[1]);
        paraObject.setFilePath(paras[2]);
        paraObject.setReplaceMap(parseToMap(paras[3]));
        return paraObject;
    }

    private static Map<String, String> parseToMap(String paras) {
        String[] split = paras.trim()
                .replace("[", "")
                .replace("]", "")
                .split(",");
        Map<String, String> map = new HashMap<>();

        Arrays.stream(split).forEach(
                it -> {
                    String[] split1 = it.split("=");
                    map.put(split1[0], split1[1]);
                }
        );

        return map;
    }


    private static void printLogo() {
        System.out.println(getLogo());
    }

    /**
     * http://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20
     */
    private static String getLogo() {
        return "\n" +
                "        .__  __  .__         ___.                                  \n" +
                "   ____ |__|/  |_|  |__  __ _\\_ |__             ____ ______  ______\n" +
                "  / ___\\|  \\   __\\  |  \\|  |  \\ __ \\   ______  /  _ \\\\____ \\/  ___/\n" +
                " / /_/  >  ||  | |   Y  \\  |  / \\_\\ \\ /_____/ (  <_> )  |_> >___ \\ \n" +
                " \\___  /|__||__| |___|  /____/|___  /          \\____/|   __/____  >\n" +
                "/_____/               \\/          \\/                 |__|       \\/ \n";
    }

}
