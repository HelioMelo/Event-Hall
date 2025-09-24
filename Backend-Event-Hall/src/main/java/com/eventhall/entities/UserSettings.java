package com.eventhall.entities;

import jakarta.persistence.Table;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSettings extends EntityBase {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false)
    private Boolean emailNotifications = false;

    @Column(nullable = false)
    private Boolean pushNotifications = false;

    @Column(nullable = false)
    private Boolean smsNotifications = false;

    @Column(nullable = false)
    private Boolean eventAlerts = false;

    @Column(nullable = false)
    private Boolean paymentAlerts = false;

    @Column(nullable = false)
    private Boolean systemAlerts = false;

    @Column(nullable = false)
    private Boolean twoFactorAuth = false;

    @Column(nullable = false)
    private Integer sessionTimeout = 30; 

    @Column(nullable = false)
    private Integer passwordExpiration = 90; 
}
