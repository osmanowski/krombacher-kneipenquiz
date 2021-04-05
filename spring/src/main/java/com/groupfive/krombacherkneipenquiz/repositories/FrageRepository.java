package com.groupfive.krombacherkneipenquiz.repositories;

import com.groupfive.krombacherkneipenquiz.models.Frage;
import com.groupfive.krombacherkneipenquiz.models.FragenPaket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FrageRepository extends JpaRepository<Frage, Long> {
    List<Frage> getFrageByFragenPakete(FragenPaket fragenpaket);
}