/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsi.helper;
import java.util.*;
import java.sql.*;
import responsi.model.pabrik;
/**
 *
 * @author Lenovo
 */
public class dbhelper {
    private final String dbUrl= "jdbc:mysql://localhost/responsi";
    private final String user= "root";
    private final String pass ="";
    
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String query;
    
    public dbhelper(){
        try {
            conn= DriverManager.getConnection(dbUrl, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<pabrik>getAllData(){
    List<pabrik> data= new ArrayList<>();
    query= "SELECT * FROM pabrik";
        try {
            stmt= conn.createStatement();
            rs= stmt.executeQuery(query);
            while(rs.next()){
            pabrik pabrik= new pabrik();
            pabrik.setId(rs.getInt("id"));
            pabrik.setProduk(rs.getString("produk"));
            pabrik.setUnit(rs.getInt("unit"));
            pabrik.setJamKerja(rs.getInt("jam_kerja"));
            pabrik.setTenagaKerja(rs.getInt("tenaga_kerja"));
            pabrik.setBiayaBahan(rs.getInt("biaya_bahan"));
            pabrik.setBiayaTenaga(rs.getInt("biaya_tenaga"));
            pabrik.setTotalBiaya(rs.getInt("total_biaya"));
            pabrik.setEfisiensi(rs.getFloat("efisiensi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public boolean addnew (String produk, int unit, int jamKerja, int tenagaKerja, int biayaBahan){
        boolean value= false;
        int biayaTenaga = jamKerja*tenagaKerja*15000;  
        int totalBiaya = biayaBahan + biayaTenaga;
        float efisiensi = (unit/ (jamKerja*tenagaKerja))*100;
        
        query= "INSERT INTO pabrik (produk, unit, jam_kerja, tenaga_kerja, biaya_bahan, biaya_tenaga, efisiensi, total_biaya)VALUES "
                + "('"+ produk+"','"+unit+"', '"+jamKerja+"','"+tenagaKerja+"','"+biayaBahan+"','"+biayaTenaga+"','"+efisiensi+"','"+totalBiaya+"')";
        try {
            stmt=conn.createStatement();
            if(stmt.executeUpdate(query)>0){
            value= true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }
    
    public boolean updatenew (int id,String produk, int unit, int jamKerja, int tenagaKerja, int biayaBahan){
        boolean value= false;
        int biayaTenaga = jamKerja*tenagaKerja*15000;  
        int totalBiaya = biayaBahan + biayaTenaga;
        float efisiensi = (unit/ (jamKerja*tenagaKerja))*100;
        
        query= "INSERT INTO pabrik(produk, unit, jam_kerja, tenaga_kerja, biaya_bahan, biaya_tenaga, efisiensi, total_biaya)VALUES "
                + "('"+ produk+"','"+unit+"', '"+jamKerja+"','"+tenagaKerja+"','"+biayaBahan+"','"+biayaTenaga+"','"+efisiensi+"','"+totalBiaya+"') WHERE id= " +id;
        try {
            stmt=conn.createStatement();
            if(stmt.executeUpdate(query)>0){
            value= true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }
    
    public boolean remove(int id){
    boolean value= false;
    query= "DELETE FROM pabrik WHERE id = '"+id+"'";
    try {
            stmt=conn.createStatement();
            if(stmt.executeUpdate(query)>0){
            value= true;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return value;
        
    }
    
}
