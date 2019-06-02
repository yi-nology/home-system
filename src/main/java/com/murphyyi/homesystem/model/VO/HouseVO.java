package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: HouseInfoNumberVO
 * @description: 房屋空闲数量
 * @author: zhangyi
 * @since: 2019-05-09 02:16
 */

@AllArgsConstructor
@NoArgsConstructor
public class HouseVO {
    String house_address;
    String house_name;
    Integer home_number;
    Integer home_empty;
    String house_id;

    public String getHouse_address() {
        return house_address;
    }

    public void setHouse_address(String house_address) {
        this.house_address = house_address;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

    public Integer getHome_number() {
        return home_number;
    }

    public void setHome_number(Integer home_number) {
        this.home_number = home_number;
    }

    public Integer getHome_empty() {
        return home_empty;
    }

    public void setHome_empty(Integer home_empty) {
        this.home_empty = home_empty;
    }

    public String getHouse_id() {
        return house_id;
    }

    public void setHouse_id(Long house_id) {
        this.house_id = house_id+"";
    }
}
