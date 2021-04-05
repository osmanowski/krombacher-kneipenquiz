package com.groupfive.krombacherkneipenquiz.repositories;

import com.groupfive.krombacherkneipenquiz.models.Spieler;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface SpielerRepository extends UserBaseRepository<Spieler> {

    Spieler findSpielerByBenutzername(String benutzername);
    //Spieler findSpielerById(long id);
    Spieler findByBenutzerId(long id);
}
