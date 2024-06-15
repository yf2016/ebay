package controller;

import java.util.List;

public class AddUserBo {

   private String userId;
   private List<String> endpoint;

   public AddUserBo(String userId, List<String> endpoint) {
      this.userId = userId;
      this.endpoint = endpoint;
   }

   public List<String> getEndpoint() {
      return endpoint;
   }

   public void setEndpoint(List<String> endpoint) {
      this.endpoint = endpoint;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }
}
