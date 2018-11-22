package com.ournews.domain.entity;

import com.ournews.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NewsType extends BaseEntity {

    private String name;
}
