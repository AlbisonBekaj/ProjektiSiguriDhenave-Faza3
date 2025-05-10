package models.dto;

public class UserUpdateDTO {

        private int id;




        private String newUsername;
        private String newPassword;

    public UserUpdateDTO(int id, String newUsername, String newPassword) {
        this.id = id;
        this.newUsername = newUsername;
        this.newPassword = newPassword;
    }

        public int getId() { return id;
    }
        public String getNewUsername() {
        return newUsername; }
        public String getNewPassword() {
        return newPassword; }

        public void setId(int id) { this.id = id; }
        public void setNewUsername(String newUsername) {
        this.newUsername = newUsername; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    }

