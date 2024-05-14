package by.yudzin.testingsystem.config;

import org.springframework.stereotype.Component;

@Component
public class Constants {
    public final String ERROR_RECORD_NOT_FOUND_CONSTANT = "запись не найдена";
    public final String ERROR_ADD_RECORD_CONSTANT = "ошибка добавления записи";
    public final String ERROR_UPDATE_RECORD_CONSTANT = "ошибка изменения записи";
    public final String ERROR_INTERNAL_SERVER_CONSTANT = "при выполнении запроса произошла ошибка на сервере";
    public final String ERROR_DUBLICAT_EMAIL_CONSTANT = "пользователь существует";
    public final String ERROR_OLD_PASSWORD_CONSTANT = "неверный старый пароль";
}
