import model.GroupData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        openGroupsPage();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        createGroup(groupWithName);
    }
}
