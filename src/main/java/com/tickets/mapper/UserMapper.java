package com.tickets.mapper;

import com.tickets.entity.User;

public interface UserMapper {

    /**
     * 插入数据
     * <p>
     * 已测试
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 真实删除
     *
     * @param uId
     * @return
     */
    int deleteReal(String uId);

    /**
     * 逻辑删除
     *
     * @param uId
     * @return
     */
    int delete(String uId);

}
