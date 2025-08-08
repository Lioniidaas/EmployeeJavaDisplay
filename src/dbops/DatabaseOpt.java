package dbops;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DatabaseOpt {

	static Scanner sc;
	Connection con;
	public DatabaseOpt() throws Exception{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/masstech","root","");
		
		sc= new Scanner(System.in);
		
	}
	
	public void insertdata() throws Exception {
		String ename,email;
		double salary;
		
		System.out.println("Enter name :: ");
		ename = sc.nextLine();sc.nextLine();
		System.out.println("Enter email :: ");
		email = sc.nextLine();
		System.out.println("Enter salary :: ");
		salary = sc.nextDouble();
		
		String q = "call insertdata('"+ename+"','"+email+"','"+salary+"')";
		PreparedStatement pstm = con.prepareStatement(q);
		
		pstm.executeUpdate();
		System.out.println("Data Inserted!!");
		
		
	}
	
	public void fetchdata() throws Exception {
		
		int eid;
		System.out.println("Enter eid :: ");
		eid = sc.nextInt();
		
		String q = "call fetchdata('"+eid+"')";
		PreparedStatement pstm = con.prepareStatement(q);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			System.out.println("EmpId is :: "+rs.getInt("eid"));
			System.out.println("Ename is :: "+rs.getString("ename"));
			System.out.println("Email is :: "+rs.getString("email"));
			System.out.println("Esal is :: "+rs.getString("esal"));
		}
	}
	
	public void viewdata()throws Exception {
		
		String q = "call viewdata()";
		PreparedStatement pstm = con.prepareStatement(q);
		ResultSet rs = pstm.executeQuery();
			System.out.printf("%-10s %-20s %-25s %-10s%n", "EmpId", "Ename", "Email", "Esal");
			System.out.println("----------------------------------------------------------------------");

			while(rs.next()) {
			    System.out.printf("%-10d %-20s %-25s %-10s%n",
			        rs.getInt("eid"),
			        rs.getString("ename"),
			        rs.getString("email"),
			        rs.getDouble("esal"));
			}

		}
	
	public void updatedata()throws Exception{
		int id;
		String name,email;
		double sal;
		System.out.println("Enter eid :: ");
		id = sc.nextInt();sc.nextLine();
		System.out.println("Enter Name :: ");
		name = sc.nextLine();
		System.out.println("Enter email :: ");
		email = sc.nextLine();
		System.out.println("Enter sal ::");
		sal = sc.nextDouble();
		
		String q = "call updatedata('"+id+"','"+name+"','"+email+"','"+sal+"')";
		PreparedStatement pstm = con.prepareStatement(q);
		pstm.executeUpdate();
		System.out.println("Data Updated!!");
	}
	
	public void deletedata()throws Exception {
		
		int id;
		System.out.println("Enter id which u want to delete :: ");
		id = sc.nextInt();
		String q = "call deletedata('"+id+"')";
		PreparedStatement pstm = con.prepareStatement(q);
		pstm.executeUpdate();
		System.out.println("Data Deleted");
	}
	
	public static void main(String[] args) throws Exception{
		
		DatabaseOpt db = new DatabaseOpt();
		int choice;
		
		while(true) {
			System.out.println("-----------------------------------------");
			System.out.println("choose any one operation");
			System.out.println("1. Insert Data");
			System.out.println("2. Fetch Single Data");
			System.out.println("3. Fetch Table Data");
			System.out.println("4. Update Data");
			System.out.println("5. Delete Data");
			System.out.println("6. Exit");
			System.out.println("-----------------------------------------");
			
			choice = sc.nextInt();
			
			switch(choice) {
			
			case 1 : db.insertdata();
				break;
			case 2 : db.fetchdata();
				break;
			case 3 : db.viewdata();
				break;
			case 4 : db.updatedata();
				break;
			case 5 : db.deletedata();
				break;
			case 6 : System.out.println("Exiting Program !!!!");
				sc.close();
				System.exit(0);
			default : System.out.println("Invalid Choice");
			} //switch end
		} //while end
		
	}//main method

}// class
