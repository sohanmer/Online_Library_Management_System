package com.eLib.dao;

import java.sql.Connection;
import java.sql.*;

public class DB {
public static Connection getCon(){
	Connection con=null;
	try{
		Class.forName("org.postgresql.Driver");
		con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","18111811");
		System.out.println("successfull");
	}catch(Exception e){System.out.println(e);}
	return con;
}
}
