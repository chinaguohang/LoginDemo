package cn.xjtu.dao.impl;

import cn.xjtu.dao.IAdminDao;
import cn.xjtu.entity.Admin;
import cn.xjtu.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 2.数据访问层接口的实现类
 */
public class AdminDao implements IAdminDao{
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public void save(Admin admin) {
        String sql = "INSERT INTO admin(userName,pwd) VALUES(?,?)";
        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,admin.getUserName());
            pstmt.setString(2,admin.getPassword());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtil.close(conn,pstmt,null);
        }

    }

    @Override
    public Admin findByNameAndPwd(Admin admin) {
        String sql= "SELECT * FROM admin WHERE userName=? AND pwd=?";
        Admin ad = null;
        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,admin.getUserName());
            pstmt.setString(2,admin.getPassword());
            rs = pstmt.executeQuery();
            //遍历
            if (rs.next()){
                ad = new Admin();
                ad.setId(rs.getInt("id"));
                ad.setUserName(rs.getString("userName"));
                ad.setPassword(rs.getString("password"));
            }
            return ad;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtil.close(conn,pstmt,rs);
        }
    }

    @Override
    public boolean userExists(String name) {
        String sql = "SELECT id FROM admin WHERE userName=?";
        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            rs = pstmt.executeQuery();
            if (rs.next()){
                int id = rs.getInt("id");
                if (id>0){
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtil.close(conn,pstmt,rs);
        }
    }
}
