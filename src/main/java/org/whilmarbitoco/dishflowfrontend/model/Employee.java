package org.whilmarbitoco.dishflowfrontend.model;

public class Employee {

    private String role;
    private String photo;
    private String firstname;
    private String lastname;
    private Long id;
    private String email;
    private boolean verified;

    public Employee() {}

    public Employee(String role, String photo, String firstname, String lastname, Long id, String email, boolean verified) {
        this.role = role;
        this.photo = photo;
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
        this.email = email;
        this.verified = verified;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
