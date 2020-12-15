package com.emredemir.calculator.model;

import com.emredemir.calculator.constant.UserTypes;

import java.time.LocalDateTime;
import java.util.UUID;

public final class CustomerDetails {
    private final String uid;
    private final String name;
    private final UserTypes userType;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final LocalDateTime createdAt;

    public CustomerDetails(String name, UserTypes userType, LocalDateTime createdAt, String email, String phoneNumber, String address) {
        this.uid = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userType = userType;
        this.createdAt = createdAt;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public UserTypes getUserType() {
        return userType;
    }

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
