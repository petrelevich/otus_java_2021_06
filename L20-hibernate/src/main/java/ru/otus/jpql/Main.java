package ru.otus.jpql;

import ru.otus.core.HibernateUtils;
import ru.otus.jpql.model.*;

public class Main {
    public static void main(String[] args) {
        HibernateUtils.buildSessionFactory(OtusStudent.class, Avatar.class, EMail.class, Course.class);
    }
}
