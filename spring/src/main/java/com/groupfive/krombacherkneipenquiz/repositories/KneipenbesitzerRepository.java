package com.groupfive.krombacherkneipenquiz.repositories;

import com.groupfive.krombacherkneipenquiz.models.Kneipenbesitzer;

public interface KneipenbesitzerRepository extends UserBaseRepository<Kneipenbesitzer> {
    //Kneipenbesitzer findKneipenbesitzerByBenutzernameAndPw(String benutzername, String password);
    Kneipenbesitzer findKneipenbesitzerByBenutzername(String benutzername);
}
