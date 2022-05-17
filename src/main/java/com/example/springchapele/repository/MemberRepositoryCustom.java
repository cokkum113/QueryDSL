package com.example.springchapele.repository;

import com.example.springchapele.entity.Member;
import com.example.springchapele.request.MemberOrderRequest;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findByAddressContain(String address);
    List<Member> findByRequest(MemberOrderRequest request);
    List<Member> findByOrderedWithBrandName(String brandNameContains);

}
