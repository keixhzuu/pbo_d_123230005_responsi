/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsi.controller;
import java.util.*;
import responsi.helper.dbhelper;
import responsi.model.pabrik;
import responsi.view.pabrikView;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Lenovo
 */
public class pabrikcontroller {
    pabrikView v;
    private final  DefaultTableModel modelpabrik;
    private List<pabrik>dataList = new ArrayList<>();
    
    
    public pabrikcontroller(){
    String [] header ={"Nama Produk", "Biaya Tenaga Kerja", "Efisiensi Produksi", "Total Biaya Produksi"};
    modelpabrik= new DefaultTableModel(header, 0);
    refreshTable();
    
    v= new pabrikView(this);
    v.getjTablePabrik().setModel(modelpabrik);
    v.setVisible(true);
    }
    
    public void tambah(String produk, int unit, int jamKerja, int tenagaKerja, int biayaBahan){
    dbhelper helper= new dbhelper();
        if(helper.addnew(produk, unit, jamKerja, tenagaKerja, biayaBahan)){
            refreshTable();
        }else{
            javax.swing.JOptionPane.showMessageDialog(v, "Gagal menambahkan", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void hapus(int id){
    dbhelper helper= new dbhelper();
        if(helper.remove(id)){
            refreshTable();
        }else{
            javax.swing.JOptionPane.showMessageDialog(v, "Gagal menghapus", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void edit(int id, String produkBaru, int unitBaru, int jamKerjaBaru, int tenagaKerjaBaru, int biayaBahanBaru){
    dbhelper helper= new dbhelper();
        if(helper.updatenew(unitBaru, produkBaru, unitBaru, jamKerjaBaru, tenagaKerjaBaru, biayaBahanBaru)){
            refreshTable();
        }else{
            javax.swing.JOptionPane.showMessageDialog(v, "Gagal mengedit", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshTable() {
        modelpabrik.setRowCount(0);
        dbhelper helper= new dbhelper();
        dataList= helper.getAllData();
        dataList.forEach((m)->{
        modelpabrik.addRow(new Object[]{m.getProduk(),m.getBiayaTenaga(), m.getEfisiensi(), m.getTotalBiaya(), m.getBiayaBahan(), m.getJamKerja(),
        m.getUnit(), m.getId(),m.getTenagaKerja()});
        
        });
        
    }
    
    public List<pabrik>getDataList(){
    return dataList;
    }
}
