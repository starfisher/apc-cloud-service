package cn.conchit.common.entity;

import lombok.Data;

import java.util.List;
/**
 * @author： hetao
 */
@Data
public class APage<T> {
    private Long total;
    private Long pageNo;
    private Long pageSize;
    private List<T> list;
}
