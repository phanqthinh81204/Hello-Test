/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Cart;
import dto.Category;
import dto.Product;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author phanq
 */
public class DAO {

    PreparedStatement ps = null;
    Connection conn = null;
    ResultSet rs = null;

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * from product";
        Product product = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    product = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("image"),
                            rs.getDouble("price"), rs.getString("title"), rs.getString("description"),
                            rs.getInt("cateID"), rs.getInt("sell_ID"));
                    list.add(product);
                }
            }
        } catch (Exception e) {
        }
        return list;

    }

   
        public User checkLogin(String user, String pass){
        try {
            String query = "select * from Account where username= ? and pass = ?";
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            
            while(rs.next()){
                User a = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                return a;
            }
            
        } catch (Exception e) {
        }
        return null;
    }
    public List<Product> getProductBySellID(int id) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where sell_ID = ?";
        try {
            conn = DBUtils.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getString("image"),
                            rs.getDouble("price"), rs.getString("title"), rs.getString("description"),
                            rs.getInt("cateID"), rs.getInt("sell_ID")));
            }
        } catch (Exception e) {
        }
        return list;
    }
        public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            conn = DBUtils.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }
         public List<Product> searchProduct(String name){
        List<Product> list = new ArrayList<>();
        String query = "SELECT * from product where name LIKE ?";
        Product product = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(query);
                ps.setString(1, "%" + name + "%");
                rs = ps.executeQuery();
                while(rs.next()){
                    product = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("image"), 
                            rs.getDouble("price"), rs.getString("title"), rs.getString("description"), 
                            rs.getInt("cateID"), rs.getInt("sell_ID"));
                    list.add(product);
                }
            }
        } catch (Exception e) {
        }
        return list;
    }
         public Cart addToCart(int productID, String name, int amount, double price){
        String query = "INSERT into Cart (ProductID,name,Amount,Price) VALUES(?,?,?,?)";
        Cart cart = new Cart();
        
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(query);
                
                ps.setInt(1, productID);
                ps.setString(2, name);
                ps.setInt(3, amount);            
                ps.setDouble(4, price);
                
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    cart = new Cart(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
                }
            }
        } catch (Exception e) {
        }
        return cart;
 
    }
         
         public List<Cart> ViewCart(){
             String query= "SELECT * from Cart";
             List<Cart> list = new ArrayList();
             Cart cart = null;
             try {
                 conn = DBUtils.getConnection();
                
                 if(conn != null){
                      ps = conn.prepareStatement(query);
                      rs = ps.executeQuery();
                      while(rs.next()){
                     cart = new Cart(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
                     list.add(cart);
                      }
                 }
             } catch (Exception e) {
             }
             return list;
         }

}



