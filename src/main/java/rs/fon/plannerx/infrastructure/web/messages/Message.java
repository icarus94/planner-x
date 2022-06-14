package rs.fon.plannerx.infrastructure.web.messages;

import org.springframework.stereotype.Component;

@Component
public class Message {
    public final static String
            PLACEHOLDER = "flashMessage";

    //TASK LIST OPERATIONS
    public final static String
            TASK_LIST_ADDED = "Task list created",
            TASK_LIST_UPDATED = "Task list updated",
            TASK_LIST_DELETED = "Task list deleted",
            SHARE_TASK_LIST_SUCCESS = "Successfully shared",
            UPDATE_SHARED_USER_TASK_LIST = "Successfully updated shared task list",
            REMOVE_USER_FROM_TASK_LIST = "User has been removed from task list";

    //TASK OPERATIONS
    public final static String
            TASK_ADDED = "event.task.added",
            TASK_UPDATED = "Task updated",
            TASK_DELETED = "Task deleted",
            TASK_CHECK_CHANGE = "Task check change done";

    //USER OPERATIONS
    public final static String
            USER_INVITED = "User has been invited",
            USER_EDITED = "Account has been modified",
            USER_REGISTERED = "Successfully registered",
            USER_ACTIVE_STATUS_CHANGED = "User account status changed";


}
