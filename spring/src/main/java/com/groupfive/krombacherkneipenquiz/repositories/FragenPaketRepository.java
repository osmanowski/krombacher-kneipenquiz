package com.groupfive.krombacherkneipenquiz.repositories;

import com.groupfive.krombacherkneipenquiz.models.FragenPaket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FragenPaketRepository extends JpaRepository<FragenPaket, Long> {
}
