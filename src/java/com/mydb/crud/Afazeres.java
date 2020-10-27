package com.mydb.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@RequestScoped
public class Afazeres {

    private String afazeres_nome;
    private String afazeres_id;
            
    public String getAfazeres_id() {
        return afazeres_id;
    }

    public void setAfazeres_id(String afazeres_id) {
        this.afazeres_id = afazeres_id;
    }

    public void setAfazeres_nome(String afazeres_nome) {
        this.afazeres_nome = afazeres_nome;
    }

    public String getAfazeres_nome() {
        return afazeres_nome;
    }
    
    public ArrayList<Afazeres> getGet_todos_afazeres() throws SQLException, Exception{
        
        ArrayList<Afazeres> list_todos_afazeres = new ArrayList<Afazeres>();
        
        try{
            Connection connection=null;
            DB_connection obj_DB_connection = new DB_connection();
            connection=obj_DB_connection.get_connection();
            
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from afazeres order by afazeres_id");
            
            while(rs.next()){
                Afazeres obj_Afazeres = new Afazeres();
                    
                    obj_Afazeres.setAfazeres_id(rs.getString("afazeres_id"));
                    obj_Afazeres.setAfazeres_nome(rs.getString("afazeres_nome"));
                    
                    list_todos_afazeres.add(obj_Afazeres);
                }
            connection.close();

        }catch (Exception e){
            System.out.println(e);
        }
        return list_todos_afazeres;
    }
    
    public void add_Afazeres() throws SQLException, Exception{
                
        try{
            Connection connection=null;
            DB_connection obj_DB_connection = new DB_connection();
            connection=obj_DB_connection.get_connection();
            
            PreparedStatement ps = connection.prepareStatement("insert into afazeres ("
                    + "afazeres_id,"
                    + "afazeres_nome)"
                    + "values (' " +
                    afazeres_id + " ', ' " +
                    afazeres_nome + "')");
            
            ps.executeUpdate();
            connection.close();

                      
        }catch (Exception e){
            System.out.println(e);
        }
    }
    
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    
    public String editar_afazeres(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String field_afazeres_id= params.get("action");
        
     try {
          DB_connection obj_DB_connection=new DB_connection();
          Connection connection=obj_DB_connection.get_connection();
          Statement st=connection.createStatement();
          ResultSet rs=st.executeQuery("select * from afazeres where afazeres_id="+field_afazeres_id);
          Afazeres obj_Afazeres=new Afazeres();
          rs.next();
          
          obj_Afazeres.setAfazeres_nome(rs.getString("afazeres_nome"));
          obj_Afazeres.setAfazeres_id(rs.getString("afazeres_id"));
          
          sessionMap.put("editarafazeres", obj_Afazeres);
          connection.close();
          
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/editar.xhtml?faces-redirect=true";
    }
    
    
    public String update_afazeres(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String update_afazeres_id = params.get("udpate_afazeres_id");
    
        try{
           DB_connection obj_DB_connection = new DB_connection();
           Connection connection=obj_DB_connection.get_connection();
             
           PreparedStatement ps = connection.prepareStatement("update afazeres set afazeres_nome=? where afazeres_id=?");
           ps.setString(1, afazeres_nome);
           ps.setString(2, afazeres_id);
           System.out.println(ps); 
           ps.executeUpdate();
           
           connection.close();
                      
        }catch (Exception e){
            System.out.println(e);
        }
        
        return "/index.xhtml?faces-redirect=true";
        
    }
    
    public String apagar_afazeres(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String field_afazeres_id= params.get("action");
        
     try {
          DB_connection obj_DB_connection=new DB_connection();
          Connection connection=obj_DB_connection.get_connection();
          PreparedStatement ps = connection.prepareStatement("delete from afazeres where afazeres_id=?");
          ps.setString(1, field_afazeres_id);
          System.out.println(ps); 
          ps.executeUpdate();
          
          connection.close();
          
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/index.xhtml?faces-redirect=true";
    }
    

    public Afazeres() {
    }
  
}
