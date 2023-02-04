package cn.conchit.common.entity;

import lombok.Data;

import java.io.Serializable;
/**
 * @author： hetao
 */
@Data
public class PageReq implements Serializable {
    private Long pageNo;
    private Long pageSize;
}
