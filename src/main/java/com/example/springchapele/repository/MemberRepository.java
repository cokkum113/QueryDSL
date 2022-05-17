package com.example.springchapele.repository;

import com.example.springchapele.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    @Query("select m from Member m where m.address like CONCAT('%',:address, '%') ")
    List<Member> findByAddressContainAnnotation(@Param("address") String address);

    @Query("select distinct m from Member m join Order o on m.id = o.member.id " +
            "join Brand b on b.id = o.product.brand.id where b.name like concat ('%',:brandNameContains, '%') order by m.id asc")
    List<Member> findByOrderedWithBrandNameAnnotation(@Param("brandNameContains") String brandNameContains);

}
