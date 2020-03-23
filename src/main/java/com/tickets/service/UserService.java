package com.tickets.service;

import com.tickets.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 保存一个用户
     * <p>
     * 已测试
     *
     * @param user
     * @return
     */
    boolean save(User user);

    /**
     * 批量保存用户
     *
     * @param users
     * @return
     */
    boolean saveAll(List<User> users);

    /**
     * 逻辑删除
     *
     * @param uId
     * @return
     */
    boolean remove(String uId);

    /**
     * 真实删除
     *
     * @param uId
     * @return
     */
    boolean removeReal(String uId);
}
