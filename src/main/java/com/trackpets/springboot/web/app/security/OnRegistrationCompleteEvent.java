package com.trackpets.springboot.web.app.security;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.trackpets.springboot.web.app.models.entity.Usuario;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final Usuario user;

    public OnRegistrationCompleteEvent(final Usuario user, final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    //

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public Usuario getUser() {
        return user;
    }

}
