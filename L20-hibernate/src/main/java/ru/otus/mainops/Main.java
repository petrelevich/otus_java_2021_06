package ru.otus.mainops;

import ru.otus.core.HibernateUtils;
import ru.otus.mainops.model.*;

public class Main {
    public static void main(String[] args) {
        HibernateUtils.buildSessionFactory(OtusStudent.class, Avatar.class, EMail.class, Course.class);
    }
}
