package com.bootcamp.demo.demo_sb_bc_mtr_station.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.demo_sb_bc_mtr_station.entity.LineEntity;

@Repository
public interface LineRepository extends JpaRepository<LineEntity, Long> {
  // ! Native Query (3rd approach - product specific)
  // ! JPQL (2nd approach, for generation JoinTable)

  // ! JPA Methods (1st approach)
  // Hibernate is able to auto generate the SQL for "findByCode"
  // select * from mtr_lines where code = ?
  Optional<LineEntity> findByCode(String code);
  // List<LineEntity> findByCode(String code);      // 一樣可行, 但 Optional 較好


}
