package cn.xjtu.service;

import cn.xjtu.entity.Admin;
import cn.xjtu.exception.UserExistsException;

public interface IAdminService {
    /**
     * 注册
     */
    void register(Admin admin) throws UserExistsException;
    /**
     * 登录
     */
    Admin login(Admin admin);
}
