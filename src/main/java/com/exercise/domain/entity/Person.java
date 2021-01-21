package com.exercise.domain.entity;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author jianglong.li@hand-china.com
 * @date 2021-01-20 16:52
 **/
@Data
public class Person {

    private String name;

    private Integer age;

    private Date birthday;

    private DateTime toSchoolTime;

}
