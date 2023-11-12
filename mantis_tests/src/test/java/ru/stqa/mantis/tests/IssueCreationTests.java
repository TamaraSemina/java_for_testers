package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunction;
import ru.stqa.mantis.model.IssueData;

public class IssueCreationTests extends TestBase {

    @Test
    void canCreateIssue() {
        app.rest().createIssue(new IssueData()
                .withSummary(CommonFunction.randomString(10))
                .withDescription(CommonFunction.randomString(10))
                .withProject(1L));
    }
}
