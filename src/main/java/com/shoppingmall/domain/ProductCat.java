package com.shoppingmall.domain;

import com.shoppingmall.common.BaseTimeEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class ProductCat extends BaseTimeEntity implements Serializable {

    @Id     // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String catCd;

    @Column(length = 50)
    private String catNm;

    @Column(length = 10)
    private String upprCatCd;

    @Column(length = 11)
    private Integer catLv;

    @Column
    private Character useYn;

    @Column
    private String cnntUrl;

}
