package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import io.swagger.client.model.User;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.MantisUserData;

public class RestApiHelper extends HelperBase {
    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
    }


    public void createIssue(IssueData issueData) {
        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);
        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);

        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void mantisAddUser(MantisUserData mantisUserData) {
        User newUser = new User();
        newUser.setUsername(mantisUserData.username());
        newUser.setPassword(mantisUserData.password());
        newUser.setRealName(mantisUserData.realname());
        newUser.setEmail(mantisUserData.email());

        UserApi apiInstance = new UserApi();
        try {
            apiInstance.userAdd(newUser);
//            UserAddResponse result = apiInstance.userAdd(newUser);
//            System.out.println(result);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
