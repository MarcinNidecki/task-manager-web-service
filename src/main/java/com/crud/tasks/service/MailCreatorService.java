package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    private AdminConfig adminConfig;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        context.setVariable("message", message);
        context.setVariable("short_message", message.substring(0,20)+ "...");
        context.setVariable("tasks_url", "https://marcinnidecki.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_goal", adminConfig.getCompanyGoal());
        context.setVariable("company_mail", adminConfig.getCompanyMail());
        context.setVariable("company_phone", adminConfig.getCompanyPhone());
        context.setVariable("current_day", new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
