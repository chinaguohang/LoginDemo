package cn.xjtu.service.impl;

import cn.xjtu.dao.IAdminDao;
import cn.xjtu.dao.impl.AdminDao;
import cn.xjtu.entity.Admin;
import cn.xjtu.exception.UserExistsException;
import cn.xjtu.service.IAdminService;

/**
 * 业务逻辑层实现
 */
public class AdminService implements IAdminService{
    /**
     * 调用DAO
     * @param admin
     */
    private IAdminDao adminDao = new AdminDao();
    @Override
    public void register(Admin admin) throws UserExistsException{
        try {
            boolean flag = adminDao.userExists(admin.getUserName());
            if (flag){
                //不允许注册,给调用者提示
                throw new UserExistsException("用户名已经存在，注册失败");
            }
            adminDao.save(admin);
        }catch (UserExistsException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Admin login(Admin admin) {
        //1.先根据用户名查询用户是否存在
        //如果用户存在，不允许注册

        try {
            return adminDao.findByNameAndPwd(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
