package com.handtours.service.commontest;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/14 0014 17:48
 */
public class JDBCTest {
    @Test
    public void func() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://172.16.0.42:3306/handtour?user=root&password=Rvi315");
            System.out.println(connection);
            System.out.println(BeanUtils.describe(connection));
            PreparedStatement preparedStatement = connection.prepareStatement("select * from stu;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("--------------");
                String name = resultSet.getString("name");
                String classS = resultSet.getString("class");
                System.out.println(name);
                System.out.println(classS);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
