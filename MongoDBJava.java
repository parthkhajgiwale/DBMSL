import com.mongodb.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.util.Scanner;

public class MongoJava {
private static Scanner sc2;
public static void main( String args[] )
{
	DBCollection coll=null;
	try{
		// To connect to mongodb server
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		// Now connect to your database
		DB db = mongoClient.getDB("Institute");
		// Selecting the Collection
		coll = db.getCollection("Students");
		System.out.println("Connected to database successfully");
		sc2 = new Scanner(System.in);
		int choice;
		do {
			System.out.println("Enter your choice of operation \n1. Display All \n2. Insert Document \n3. Delete Document \n4. Update \n5. Conditional Display \n6.Exit \n");
			choice = sc2.nextInt();
			switch (choice) {
			case 1:displayAll(coll);
			break;
			case 2: insertDoc(coll);
			break;
			case 3: deleteDoc(coll);
			break;
			case 4: updateDoc(coll);
			break;
			case 5: conditionalDisplay(coll);
			break;
			case 6: System.out.println("Exiting Program...");
			System.exit(0);
			break;
			default:
				System.out.println(choice + " is not a valid Menu Option! Please Select Another.");
			}
		}
		while(choice != 6);
		}
	catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void insertDoc(DBCollection coll)
	{
		System.out.println("Inserting document");
		BasicDBObject document = new BasicDBObject();
		System.out.println("Enter Student rollno");
		Scanner sc=new Scanner(System.in);
		int sroll = sc.nextInt();
		System.out.println("Enter Student Name");
		String sname = sc.next();
		System.out.println("Enter Student Class");
		String sclass = sc.next();
		System.out.println("Enter Student Marks");
		int smarks = sc.nextInt();
		System.out.println("Enter Student Technical Interest");
		String sti = sc.next();
		document.put("stu_rollno",sroll);
		document.put("stu_name",sname);
		document.put("class",sclass);
		document.put("marks",smarks);
		document.put("technical_interest",sti);
		coll.insert(document);
		System.out.println("Document inserted successfully");
	}
	public static void deleteDoc(DBCollection coll)
	{
		System.out.println("Deleting document");
		BasicDBObject document = new BasicDBObject();
		System.out.println("Enter Student rollno");
		Scanner sc=new Scanner(System.in);
		int sroll = sc.nextInt();
		document.put("stu_rollno",sroll);
		coll.remove(document);
		System.out.println("Document deleted successfully");
	}
	public static void updateDoc(DBCollection coll)
	{
		System.out.println("Updating document");
		System.out.println("Enter Student rollno");
		Scanner sc1=new Scanner(System.in);
		int sroll = sc1.nextInt();
		BasicDBObject searchQuery = new	BasicDBObject().append("stu_rollno", sroll);
		BasicDBObject newDocument = new BasicDBObject();
		System.out.println("Enter New marks");
		Scanner sc=new Scanner(System.in);
		int smarks = sc.nextInt();
		newDocument.append("$set", new BasicDBObject().append("marks",smarks));
		coll.update(searchQuery,newDocument);
		System.out.println("Document updated successfully");
	}
	public static void displayAll(DBCollection coll)
	{
		System.out.println("Displaying all documents in collection");
		DBCursor cursor = coll.find();
		while(cursor.hasNext()) 
		{
			System.out.println(cursor.next());
		}
	}
	public static void conditionalDisplay(DBCollection coll)
	{
		System.out.println("Enter Min marks");
		Scanner sc=new Scanner(System.in);
		int smarks=sc.nextInt();
		DBCursor cursor = coll.find();
		while(cursor.hasNext()) 
		{
		int marks=(int) cursor.next().get("marks");
		if(marks > smarks )
		{
			System.out.println(cursor.curr());
		}
		else
			System.out.println("low marks");
		}
		}
	}
