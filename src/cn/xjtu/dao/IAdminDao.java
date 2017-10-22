package cn.xjtu.dao;

import cn.xjtu.entity.Admin;

/**
 * 2.数据访问层，接口设计
 */
public interface IAdminDao {
    /**
     * 保存
     */
    void save(Admin admin);
    /**
     * 根据用户名密码查询
     */
    Admin findByNameAndPwd(Admin admin);

    /**
     * 检查用户名是否存在
     * @param name 要检查的用户名
     * @return  true表示用户名已存在，否则用户名不存在即可以使用
     */
    boolean userExists(String name);
}
