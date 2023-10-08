package com.atguigu.spzx.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Schema(description = "请求参数实体类")
public class AssignMenuDto {

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "选中的菜单id的集合")
    private List<Map<String,Integer>> menuIdList;

}