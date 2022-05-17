package com.example.springchapele;

import com.example.springchapele.entity.Category;
import com.example.springchapele.entity.Member;
import com.example.springchapele.entity.ProductStatus;
import com.example.springchapele.request.MemberOrderRequest;
import com.example.springchapele.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringchapeleApplicationTests {
    @Autowired
    private MemberService memberService;

    @Test
    public void test_exampleOne() {
        List<Member> members = memberService.findByAddressContain("busan");
        assertThat(members.stream().map(Member::getId)).contains(3L);
        assertThat(members.size()).isEqualTo(1L);
    }

    @Test
    public void test_findMemberByRequest() {
        List<Member> membersOne = memberService.findByOrderedProductInformation(MemberOrderRequest.builder()
                .priceLow(BigDecimal.valueOf(1000L))
                .build());

        assertThat(membersOne.stream().map(Member::getId)).containsExactly(1L, 2L, 3L);
        assertThat(membersOne.size()).isEqualTo(3);

        List<Member> membersTwo = memberService.findByOrderedProductInformation(MemberOrderRequest.builder()
                .priceLow(BigDecimal.valueOf(250L))
                .category(Category.FOOD)
                .build());

        assertThat(membersTwo.stream().map(Member::getId)).containsExactly(2L);
        assertThat(membersTwo.size()).isEqualTo(1);

        List<Member> membersThree = memberService.findByOrderedProductInformation(MemberOrderRequest.builder()
                .statuses(List.of(ProductStatus.ON_SALE))
                .nameContains("IPHONE")
                .priceLow(BigDecimal.valueOf(1501))
                .build());

        assertThat(membersThree.stream().map(Member::getId)).containsExactly(2L);
        assertThat(membersThree.size()).isEqualTo(1);

        List<Member> membersFour = memberService.findByOrderedProductInformation(MemberOrderRequest.builder()
                .category(Category.ELECTRONICS)
                .statuses(List.of(ProductStatus.ON_SALE))
                .build());

        assertThat(membersFour.stream().map(Member::getId)).containsExactly(1L, 2L, 3L, 4L);
        assertThat(membersFour.size()).isEqualTo(4);
    }


    @Test
    public void test_findMemberByBrandName() {
        List<Member> membersOne = memberService.findByProductBrand("galaxy");

        assertThat(membersOne.stream().map(Member::getId)).containsExactly(1L, 3L, 4L);
        assertThat(membersOne.size()).isEqualTo(3);

        List<Member> membersTwo = memberService.findByProductBrand("e");

        assertThat(membersTwo.stream().map(Member::getId)).containsExactly(2L, 3L, 4L);
        assertThat(membersTwo.size()).isEqualTo(3);
    }

    @Test
    public void test_exampleOne_queryAnnotation() {
        List<Member> members = memberService.findByAddressContain_ByQueryAnnotation("busan");
        assertThat(members.stream().map(Member::getId)).contains(3L);
        assertThat(members.size()).isEqualTo(1L);
    }

    @Test
    public void test_findMemberByBrandName_queryAnnotation() {
        List<Member> membersOne = memberService.findByProductBrand_ByQueryAnnotation("galaxy");

        assertThat(membersOne.stream().map(Member::getId)).containsExactly(1L, 3L, 4L);
        assertThat(membersOne.size()).isEqualTo(3);

        List<Member> membersTwo = memberService.findByProductBrand_ByQueryAnnotation("e");

        assertThat(membersTwo.stream().map(Member::getId)).containsExactly(2L, 3L, 4L);
        assertThat(membersTwo.size()).isEqualTo(3);
    }
}

