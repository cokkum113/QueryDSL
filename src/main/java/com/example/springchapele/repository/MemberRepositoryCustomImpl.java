package com.example.springchapele.repository;

import com.example.springchapele.entity.Member;
import com.example.springchapele.request.MemberOrderRequest;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.example.springchapele.entity.QMember.member;
import static com.example.springchapele.entity.QOrder.order;
import static com.example.springchapele.entity.QProduct.product;


public class MemberRepositoryCustomImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {

    public MemberRepositoryCustomImpl() {
        super(Member.class);
    }

    @Override
    public List<Member> findByAddressContain(String address) {
        return from(member)
                .where(member.address.contains(address))
                .fetch();
    }

    @Override
    public List<Member> findByRequest(MemberOrderRequest request) {
        BooleanBuilder builder = new BooleanBuilder();

        if (request.getPriceLow() != null) {
            builder.and(order.product.price.lt(request.getPriceLow()));
        }
        if (request.getCategory() != null) {
            builder.and(order.product.category.eq(request.getCategory()));
        }
        if (request.getNameContains() != null) {
            builder.and(order.product.name.contains(request.getNameContains()));
        }
        if (request.getStatuses() != null) {
            builder.and(order.product.status.in(request.getStatuses()));
        }
        return from(member)
                .join(order)
                .on(order.member.eq(member))
                .where(builder)
                .distinct()
                .orderBy(member.id.asc())
                .fetch();
    }

    @Override
    public List<Member> findByOrderedWithBrandName(String brandNameContains) {
        return from(member)
                .join(order).on(order.member.eq(member))
                .join(product).on(product.eq(order.product))
                .where(product.brand.name.contains(brandNameContains))
                .distinct()
                .orderBy(member.id.asc())
                .fetch();
    }
}
