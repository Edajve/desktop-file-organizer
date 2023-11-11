package org.example.src.constants;

import java.util.HashMap;
import java.util.Map;

public class KeyWords {

    public static Map<String, String> generateKeywordToPathMapping() {
        Map<String, String> keywordToPath = new HashMap<>();
        keywordToPath.put("tfp", "05-test-for-program");
        keywordToPath.put("pwdx", "01-personal-work-documents");
        keywordToPath.put("pa", "02-work-documents/01-Onboarding/01-Permissions");
        keywordToPath.put("tvb", "2-work-documents/02-Terms-and-Vocab");
        keywordToPath.put("coc", "02-work-documents/03-Chicago-Office");
        keywordToPath.put("kf", "02-work-documents/04-screenshots/01-for-learning/01-Kibana");
        keywordToPath.put("ddg", "02-work-documents/04-screenshots/01-for-learning/02-Data-dog");
        keywordToPath.put("vddk", "04-videos-and-screenshots/01-Knowledge-Transfer/01-external-software-tools/01-VideoDataDog");
        keywordToPath.put("vtml", "04-videos-and-screenshots/01-Knowledge-Transfer/01-external-software-tools/02-VideoTestmo");
        keywordToPath.put("scn", "04-videos-and-screenshots/01-Knowledge-Transfer/02-vivid-seats-code/01-skybox-client");
        keywordToPath.put("sso", "04-videos-and-screenshots/01-Knowledge-Transfer/02-vivid-seats-code/02-skybox-services");
        keywordToPath.put("kttp", "04-videos-and-screenshots/01-Knowledge-Transfer/03-knowledgeTransferTesting");
        keywordToPath.put("gkq", "04-videos-and-screenshots/01-Knowledge-Transfer/04-general-knowledge");
        keywordToPath.put("p00", "04-videos-and-screenshots/01-Knowledge-Transfer/05-processes");
        keywordToPath.put("lkdn", "04-videos-and-screenshots/01-Knowledge-Transfer/04-general-knowledge/linkedIn");
        keywordToPath.put("trash", "trash");

        // test keyword and path
        keywordToPath.put("testMoveKeyWord", "/../../Desktop/integration-test-directory/move-test-directory");
        keywordToPath.put("aaa", "/Users/dajve.echols/Desktop/integration-test-directory/mover-test/");
        return keywordToPath;
    }
}