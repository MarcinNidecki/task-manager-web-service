package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private TaskRepository taskRepository;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");
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
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality",functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String onceADayEmail(String message) {
        List<Task> tasks = taskRepository.findAll().stream()
                .sorted(Comparator.comparingLong(Task::getId).reversed())
                .limit(3)
                .collect(Collectors.toList());
        Context context = new Context();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        context.setVariable("message", message);
        context.setVariable("short_message", message.substring(0,10)+ "...");
        context.setVariable("tasks_url", "https://marcinnidecki.github.io");
        context.setVariable("button", "Check Tasks");
        context.setVariable("adminConfig", adminConfig);
        context.setVariable("current_day", new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
        context.setVariable("show_button", false);
        context.setVariable("tasks", tasks);
        return templateEngine.process("mail/once-a-day-mail", context);
    }
}
