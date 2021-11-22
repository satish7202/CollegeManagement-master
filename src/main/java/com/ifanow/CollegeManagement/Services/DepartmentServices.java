package com.ifanow.CollegeManagement.Services;

import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Connection.DbConnection;
import com.ifanow.CollegeManagement.Models.DepartmentModel;
import com.ifanow.CollegeManagement.Query.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Base64;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class DepartmentServices {








    PreparedStatement ps,ps1;
    int[] batchdepartment;
    int countbatch=0;
    @Autowired
    DbConnection dbConnection = new DbConnection();
    Connection connection;
    //  @Autowired
    DepartmentModel departmentModel;
    @Autowired
    Queries queries;
    public List getDepartment() {

        int length=0;
        DepartmentModel[] DepartmentModel = new DepartmentModel[100];
        List<DepartmentModel> DepartModelList = new ArrayList<>();
        try {
            connection = dbConnection.getconnect();

            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(queries.selectQuery);

            while (result.next()) {

                DepartmentModel[length] = new DepartmentModel(result.getInt(1), result.getString(2),  result.getString(3), new String[]{result.getString(4)});
                DepartModelList.add(DepartmentModel[length]);

            }

            connection.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DepartModelList;

/*
        List<DepartmentModel> Listifa = new ArrayList<>();
        Connection  connection = dbConnection.getconnect();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.selectQuery);
            ResultSetMetaData rsmd=rs.getMetaData();
            int i=rsmd.getColumnCount();
            DepartmentModel[] department=new DepartmentModel[i];
            for(int i1=0;i1<i;i1++)
                while (rs.next()) {
                    //System.out.println(rs.getString("person_id") +" " +rs.getString("name"));
                   // department[i1] = new DepartmentModel(rs.getInt("departmentId"),rs.getString("departmentName"),rs.getString("departmentHead"), (ArrayList<String>) rs.getArray(("teachersAll")));
                    department[i1] = new DepartmentModel(rs.getInt("departmentId"),rs.getString("departmentName"),rs.getString("departmentHead"), (String[])rs.getArray("teachersAll").getArray());
                    Listifa.add(department[i1]);
                }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listifa;*/
        //  return null;
    }
    public int Insert(String deptname,String deptHead,String[] teachersAll )
    {
        int count=0;
        int count1=0;
        // int length=0;
        try {
            Connection con = dbConnection.getconnect();
            Statement stmt = con.createStatement();
            ps = con.prepareStatement(Queries.insertQueryDepartment,Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,deptname);
            ps.setString(2,deptHead);
            count = ps.executeUpdate ();
            int deptId=0;
            ResultSet resultdeptId = ps.getGeneratedKeys();
            if (resultdeptId != null && resultdeptId.next()) {
                deptId= resultdeptId.getInt(1);

            }

            ps1 = con.prepareStatement(Queries.insertteachers);
            for(int i=0;i<teachersAll.length;i++) {
                ps1.setInt(1, deptId);
                ps1.setString(2, teachersAll[i]);
                count1=ps1.executeUpdate();

            }
            con.close();
        }
        catch (Exception e)
        {
            System.out.println("Error..."+e);
        }
        return count;
    }


    public int update(int deptid, String deptname, String deptHead)
    {
        int count=0;
        // int length=0;
        try {
            Connection con = dbConnection.getconnect();


            Statement stmt = con.createStatement();
            ps = con.prepareStatement(Queries.updateQueryDepartment);

            ps.setString(1,deptname);
            ps.setString(2,deptHead);
            //  ps.setString(3, String.valueOf((String[]) teachersall));
            ps.setInt(3,deptid);
            count = ps.executeUpdate ();


            con.close();
        }
        catch (Exception e)
        {
            System.out.println("Error..."+e);
        }
        return count;
    }

    public int delete(int deptid)
    {
        int count=0;
        int count1=0;
        // int length=0;
        try {
            Connection con = dbConnection.getconnect();


            Statement stmt = con.createStatement();
            ps = con.prepareStatement(Queries.deleteQueryDepartment);
            ps1=con.prepareStatement(Queries.deleteQueryteachers);


            ps.setInt(1,deptid);
            ps1.setInt(1,deptid);
            count = ps.executeUpdate ();
            count1=ps.executeUpdate();

            con.close();
        }
        catch (Exception e)
        {
            System.out.println("Error..."+e);
        }
        return count;
    }

    public String convertListtoJson(List list)
    {
        Gson gsdepartment=new Gson();
        String St_ObjTojson11 =gsdepartment.toJson(list);
        return  St_ObjTojson11;
    }
    public int totaldepartments() {
        Connection connection = dbConnection.getconnect();
        int i=0;
        try {
            ResultSet rs;
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(Queries.totalcountdepartment);
            if(rs.next()){
                i = rs.getInt("total");
            }
            return i;

        }
        catch (SQLException ex) {
            ex.printStackTrace();

        }
        return i;
    }

    public int[] insertbatchdepartment( DepartmentModel[] departmentmodel)
    {
        int count=0;
        int show[] = new int[0];

        try {

            connection = dbConnection.getconnect();
            PreparedStatement stmt = connection.prepareStatement(Queries.insertQueryDepartment,Statement.RETURN_GENERATED_KEYS);

            ArrayList<Integer> deptId = new ArrayList<Integer>();
            // int deptId[]; ;
            //int deptId=0;
            for(int i=0;i<departmentmodel.length;i++) {
                stmt.setString(1, departmentmodel[i].getDepartmentName());
                stmt.setString(2, departmentmodel[i].getDepartmentHead());
                stmt.addBatch();
            }
            stmt.executeBatch();
            ResultSet rs = stmt.getGeneratedKeys();
            for(int i=0;i<departmentmodel.length;i++)
            {
                while(rs.next()) {
                    deptId.add( rs.getInt(1));

                }// This should contain the id of the inserts in order.
            }

            ps1 = connection.prepareStatement(Queries.insertteachers);
            // for(int i=0;i<departmentmodel[i].length;i++) {
            // for(int i=0;i<departmentmodel.length;i++) {
            for(int i=0;i<deptId.size();i++) {

                for(int i1=0;i1<departmentmodel[i].getTeachersAll().length;i1++) {
                    ps1.setInt(1, deptId.get(i));
                    ps1.setString(2, departmentmodel[i].getTeachersAll()[i1]);

                }
                ps1.addBatch();
                // count1=ps1.executeUpdate();

            }
            // show= stmt.executeBatch();
            ps1.executeBatch();

            // show1 = ps1.executeBatch();
            System.out.println("Records inserted successfully");

            connection.close();
            System.out.println("Connection closed");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return show;

    }
    public int[] DeleteMultipaldepartment(int[] deptId) throws SQLException {
        int deletedeptdelete[]=new int[0];
        try{
            connection=dbConnection.getconnect();
            ps=connection.prepareStatement(Queries.deleteQueryDepartment);
            for(int i=0;i<deptId.length;i++){
                ps.setInt(1,deptId[i]);
                ps.addBatch();
            }
            deletedeptdelete=ps.executeBatch();
            System.out.println("data Deleted successfully...");
            connection.close();
            System.out.println("Connection closed");
        }catch(Exception e){
            System.out.println(e);
        }
        return deletedeptdelete;
    }

    public String encodefunc(String e){
        String encode = Base64.getEncoder().encodeToString(e.getBytes());
        return encode;
    }
    public String decodefunc(String d) {
        byte[] decodedBytes = Base64.getDecoder().decode(d);
        String decodedString = new String(decodedBytes);
        System.out.println(decodedString);
        return decodedString;
    }
}

