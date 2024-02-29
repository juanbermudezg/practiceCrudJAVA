package com.juanbermudez.java_crud_mysql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author juanbermudezg
 */
public class CAlumnos {
    
    private int id;
    private String nombreAlumno;
    private String apellidoAlumno;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }
    
    public void insetarAlumno(JTextField paramNombres, JTextField paramApellidos){
        
        setNombreAlumno(paramNombres.getText());
        setApellidoAlumno(paramApellidos.getText());
        
        CConexion objectoConexion = new CConexion();
        
        String consulta = "insert into Alumnos(nombres, apellidos) values (?, ?);";
        
        try{
            CallableStatement cs = objectoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el alumno");
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el alumno, error: " + e.toString());
        }
        
    }
    public void MostrarAlumnos(JTable paramTablaTotalAlumnos){
        CConexion objectoConexion = new CConexion();
        DefaultTableModel model = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(model);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
        
        String sql = "";
        
        model.addColumn("Id");
        model.addColumn("Nombres");
        model.addColumn("Apellidos");
        paramTablaTotalAlumnos.setModel(model);
        sql = "select * from Alumnos;";
        
        String[] datos = new String[3];
        Statement st;
        try {
            st = objectoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                model.addRow(datos);
            }
            paramTablaTotalAlumnos.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
        }
    }
    
}
