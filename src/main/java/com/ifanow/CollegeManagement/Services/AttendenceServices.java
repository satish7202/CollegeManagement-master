package com.ifanow.CollegeManagement.Services;

import com.ifanow.CollegeManagement.Connection.DbConnection;
import com.ifanow.CollegeManagement.Models.AttendenceInsertModel;
import com.ifanow.CollegeManagement.Models.AttendenceModel;
import com.ifanow.CollegeManagement.Models.AttendenceUpdateModel;
import com.ifanow.CollegeManagement.Query.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class AttendenceServices {
    @Autowired
    DbConnection dbconnection;
    Connection connection;
    PreparedStatement ps;
    int[] batchCount;
    int count=0;
    public int insertAttendence(AttendenceInsertModel attendenceInsertModel,float attendencePercentage)
    {
        try {
            //SelectStudentDetails selectStudentDetails = re


            connection = dbconnection.getconnect();
            ps = connection.prepareStatement(Queries.insertAttendence);
            ps.setInt(1,attendenceInsertModel.getStudentId());
            ps.setString(2,attendenceInsertModel.getStudentName());
            ps.setString(3,attendenceInsertModel.getDepartment());
            ps.setString(4,attendenceInsertModel.getLoginTime());
            ps.setString(5,attendenceInsertModel.getLogoutTime());
            ps.setFloat(6,attendencePercentage);
            count = ps.executeUpdate ();
            System.out.println("Inserted Rows "+count);

            connection.close();
        }
        catch (Exception e)
        {
            System.out.println("Errorr...in Crud Attendence..."+e);
        }
        return count;
    }
    public List selectAttendence()
    {
        AttendenceModel[] model=new AttendenceModel[100];
        List<AttendenceModel> listModel = new ArrayList<>();
        int length=0;
        try {

            connection = dbconnection.getconnect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.selectAttendence);

            while (rs.next()) {
                model[length] = new AttendenceModel(rs.getInt(1),rs.getInt(2),
                        rs.getString(3),rs.getString(4),rs.getString(5),
                        rs.getString(6),rs.getInt(7));
                listModel.add(model[length]);
                length++;


            }

            connection.close();
        }
        catch (Exception e)
        {
            System.out.println("Error..."+e);
        }
        return listModel;
    }
    public int Delete(int srNo)
    {
        int deleted_row=0;
        try {
            connection= dbconnection.getconnect();
            ps = connection.prepareStatement(Queries.deleteAttendence);
            ps.setInt(1,srNo);
            deleted_row = ps.executeUpdate();

        }
        catch (Exception e)
        {
            System.out.println("Errorr."+e);
        }
        return deleted_row;
    }
    public int attendenceUpdate(AttendenceUpdateModel attendenceUpdateModel, int attdencePercentage)
    {
        int updated_row=0;
        try {
            connection= dbconnection.getconnect();
            Statement stmt = connection.createStatement();
            ps = connection.prepareStatement(Queries.updateAttendence);
            ps.setString(1,attendenceUpdateModel.getStudentName());
            ps.setString(2,attendenceUpdateModel.getDepartment());
            ps.setString(3,attendenceUpdateModel.getLoginTime());
            ps.setString(4,attendenceUpdateModel.getLogoutTime());
            ps.setInt(5,attdencePercentage);
            ps.setInt(6,attendenceUpdateModel.getSrNo());
            updated_row = ps.executeUpdate();

        }
        catch (Exception e)
        {
            System.out.println("Errorr."+e);
        }
        return updated_row;
    }
    public int attendencePercentage(int sId)
    {

        float length=0,noDays=31;
        float attendencePercentage=0;
        PreparedStatement psmt;
        try {

            connection= dbconnection.getconnect();
            psmt = connection.prepareStatement(Queries.percentageAttendenceQuery);
            psmt.setInt(1,sId);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {

                length++;


            }
            attendencePercentage=length/noDays*100;
        }
        catch (Exception e)
        {
            System.out.println("Error..."+e);
        }
        return (int)attendencePercentage;
    }
    public int attdendenceInsertBatch(AttendenceInsertModel[] attendenceInsertModels) {
        try {

            connection = dbconnection.getconnect();
            ps = connection.prepareStatement(Queries.insertBatchAttendence);
            for (AttendenceInsertModel a : attendenceInsertModels) {
                int percentage=(int) attendencePercentage(a.getStudentId());
                ps.setInt(1,a.getStudentId());
                ps.setString(2,a.getStudentName());
                ps.setString(3,a.getDepartment());
                ps.setString(4,a.getLoginTime());
                ps.setString(5,a.getLogoutTime());
                ps.setInt(6,percentage);
                ps.addBatch();
            }

             batchCount = ps.executeBatch();
            count=0;
            for (int i=0;i<batchCount.length;i++)
            {
                if (batchCount[i]==1)
                {
                    count++;
                }


            }

                }
        catch(Exception e)
                {
                    System.out.println("Errorr...in Crud Attendence..." + e);
                }
                 return count;
    }
    public String Encode(String input)
    {
        Base64.Encoder encoder = Base64.getEncoder();
        String output = encoder.encodeToString(input.getBytes(StandardCharsets.UTF_8));
        return output;
    }
    public String Decode(String input)
    {
        Base64.Decoder decoder = Base64.getDecoder();
        String output = new String(decoder.decode(input));
        return output;
    }
    public int deleteBatch(int[] attendenceDeleteModel)
    {
        int[] deleted_row = new int[0];
        try {
            connection= dbconnection.getconnect();
            //Statement stmt = con.createStatement();
            ps = connection.prepareStatement(Queries.deleteBatchAttendence);
            for (int a:attendenceDeleteModel) {
                ps.setInt(1, a);
                ps.addBatch();
            }
            deleted_row = ps.executeBatch();
            count=0;
            for (int i=0;i<deleted_row.length;i++)
            {
                if ((deleted_row[i])==1)
                {
                    count++;
                }
            }


        }
        catch (Exception e)
        {
            System.out.println("Errorr."+e);
        }

        return count;
    }
    public int count()
    {
        int length=0;
        try {

            connection = dbconnection.getconnect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.selectAttendence);

            while (rs.next()) {

                length++;


            }

            connection.close();
        }
        catch (Exception e)
        {
            System.out.println("Error..."+e);
        }
        return length;
    }
    public String callOtherApi(String url) throws IOException, InterruptedException {
        HttpRequest request =  HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client=HttpClient.newBuilder().build();
        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        return String.valueOf(response);
    }
}
