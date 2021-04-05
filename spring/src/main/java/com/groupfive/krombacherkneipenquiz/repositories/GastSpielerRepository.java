package com.groupfive.krombacherkneipenquiz.repositories;

import com.groupfive.krombacherkneipenquiz.models.GastSpieler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GastSpielerRepository extends JpaRepository<GastSpieler, Long> {
}
