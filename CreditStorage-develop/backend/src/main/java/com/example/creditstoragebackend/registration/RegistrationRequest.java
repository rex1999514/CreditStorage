package com.example.creditstoragebackend.registration;


public class RegistrationRequest {
    private final String username;
    private final String password;


    public RegistrationRequest(String username,String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistrationRequest that = (RegistrationRequest) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return password.equals(that.password);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + password.hashCode();
        return result;
    }
}


