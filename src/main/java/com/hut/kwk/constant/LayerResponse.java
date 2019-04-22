package com.hut.kwk.constant;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Create by Wang Heng on 2019-04-21
 *
 * @author Wang Heng
 */
@Data
@AllArgsConstructor
public class LayerResponse<T> implements Serializable {
    //"code":0,"msg":"","count":1000,"data"
    private Integer code;
    private String msg;
    private Long count;
    private T data;

}
