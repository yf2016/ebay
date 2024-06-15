package controller;

import java.util.List;

/**
 *
 */
public class UserBo {

    private String userId;
    private String accountName;
    private String role;
    private List<String> endpoint;

    public UserBo() {
    }

    public UserBo(String userId, String accountName, String role, List<String> endpoint) {
        this.userId = userId;
        this.accountName = accountName;
        this.role = role;
        this.endpoint = endpoint;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(List endpoint) {
        this.endpoint = endpoint;
    }


}
