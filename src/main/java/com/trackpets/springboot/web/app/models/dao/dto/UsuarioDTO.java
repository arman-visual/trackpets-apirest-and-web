package com.trackpets.springboot.web.app.models.dao.dto;

import com.sun.istack.NotNull;

public class UsuarioDTO {

	@NotNull
    private String username;

	
    private String lastName;

    @NotNull
    private String password;
    private String matchingPassword;

    @NotNull
    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    private Integer role;

    public Integer getRole() {
        return role;
    }

    public void setRole(final Integer role) {
        this.role = role;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(final String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [firstName=")
                .append(username)
                .append(", email=")
                .append(email)
                .append(", isUsing2FA=")
                .append(", role=")
                .append(role).append("]");
        return builder.toString();
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
