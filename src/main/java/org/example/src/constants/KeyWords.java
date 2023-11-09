package org.example.src.constants;

import java.util.HashMap;
import java.util.Map;

public class KeyWords {

    public static Map<String, String> generateKeywordToPathMapping() {
        Map<String, String> keywordToPath = new HashMap<>();
        keywordToPath.put("TfP", "05-test-for-program");
        keywordToPath.put("pwdX", "01-personal-work-documents");
        keywordToPath.put("pA", "02-work-documents/01-Onboarding/01-Permissions");
        keywordToPath.put("tvB", "2-work-documents/02-Terms-and-Vocab");
        keywordToPath.put("coC", "02-work-documents/03-Chicago-Office");
        keywordToPath.put("kF", "02-work-documents/04-screenshots/01-for-learning/01-Kibana");
        keywordToPath.put("ddG", "02-work-documents/04-screenshots/01-for-learning/02-Data-dog");
        keywordToPath.put("vddK", "04-videos-and-screenshots/01-Knowledge-Transfer/01-external-software-tools/01-VideoDataDog");
        keywordToPath.put("vtmL", "04-videos-and-screenshots/01-Knowledge-Transfer/01-external-software-tools/02-VideoTestmo");
        keywordToPath.put("scN", "04-videos-and-screenshots/01-Knowledge-Transfer/02-vivid-seats-code/01-skybox-client");
        keywordToPath.put("ssO", "04-videos-and-screenshots/01-Knowledge-Transfer/02-vivid-seats-code/02-skybox-services");
        keywordToPath.put("kttP", "04-videos-and-screenshots/01-Knowledge-Transfer/03-knowledgeTransferTesting");
        keywordToPath.put("gkQ", "04-videos-and-screenshots/01-Knowledge-Transfer/04-general-knowledge");
        keywordToPath.put("p00", "04-videos-and-screenshots/01-Knowledge-Transfer/05-processes");
        keywordToPath.put("lkdn", "04-videos-and-screenshots/01-Knowledge-Transfer/04-general-knowledge/linkedIn");
        keywordToPath.put("trash", "trash");

        // test keyword and path
        keywordToPath.put("testMoveKeyWord", "/../../Desktop/integration-test-directory/move-test-directory");
        return keywordToPath;
    }
}