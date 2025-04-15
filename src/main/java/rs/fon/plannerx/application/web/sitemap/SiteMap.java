package rs.fon.plannerx.application.web.sitemap;

import org.springframework.stereotype.Component;

@Component(value = "SiteMap")
public class SiteMap {
    // COMMON
    public final static String
            LOGIN = "/account/login",
            LOGOUT = "/logout",
            LOGIN_FAILED = "/account/login?error",
            LOGIN_FAILED_ACCOUNT_NOT_ACTIVE = "/account/login?error=not-active",
            LOGOUT_SUCCESS = "/account/login?logout",
            REGISTER = "/account/register",
            MAIL_VERIFY = "/account/verify-email";

    // REGULAR USER
    public final static String
            HOMEPAGE = "/account/homepage",
            ACCOUNT_PAGE = "/account/my-account",
            INVITE_USER = "/account/invite-user";

    // ADMIN
    public final static String
            ADMIN_HOMEPAGE = "/admin/homepage",
            ADMIN_USERS = "/admin/users",
            ADMIN_USERS_DATATABLES = "/admin/users/datatables",
            ADMIN_USERS_CHANGE_ACTIVE_STATUS = "/admin/users/change-active-status",
            ADMIN_USERS_USER_INFO = "/admin/user-info";

    // TASK LIST related routes
    public static final String
            MY_TASK_LISTS = "/task/my-task-lists",
            CREATE_TASK_LIST = "/task/create-task-list",
            UPDATE_TASK_LIST = "/task/update-task-list",
            DELETE_TASK_LIST = "/task/delete-task-list",
            SHARE_TASKS = "/task/share-tasks",
            CREATE_SHARED_USER_TASK_LIST = "/task/create-shared-user-task-list",
            UPDATE_SHARED_USER_TASK_LIST = "/task/update-shared-user-task-list",
            REMOVE_SHARED_USER_TASK_LIST = "/task/remove-shared-user-task-list";

    // TASK related routes
    public static final String
            CREATE_TASK = "/task/create-task",
            UPDATE_TASK = "/task/update-task",
            DELETE_TASK = "/task/delete-task",
            CHECK_TASK = "/task/check-task";
}
