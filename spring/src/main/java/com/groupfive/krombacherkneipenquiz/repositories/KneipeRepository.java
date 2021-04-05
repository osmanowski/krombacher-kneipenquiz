package com.groupfive.krombacherkneipenquiz.repositories;

import com.groupfive.krombacherkneipenquiz.models.Kneipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface KneipeRepository extends JpaRepository<Kneipe, Long> {
}
