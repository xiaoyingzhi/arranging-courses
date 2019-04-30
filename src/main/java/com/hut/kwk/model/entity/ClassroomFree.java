package com.hut.kwk.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by Wang Heng on 2019-04-29
 *
 * @author Wang Heng
 */
@Data
public class ClassroomFree implements Serializable {

    private Integer week;
    private Integer classroomId;
    private String classroomName;
    private String day;
    private Integer number;
}
