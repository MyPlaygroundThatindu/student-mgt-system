import java.util.Arrays;
import java.util.Scanner;

public class StudentManagementSystem {

    //Arrays to store Student ID, Name, PRF Marks, DBMS marks, total Marks, Average Marks, Rank
    static String[] studentIds = new String[]{};
    static String[] studentNames = new String[]{};
    static int[] PRFMarks = new int[]{};
    static int[] DBMSMarks = new int[]{};
    static String[] subjects = new String[]{"PRF", "DBMS"};
    static int[] totalMarks = new int[]{};
    static int[] ranks = new int[]{};
    static double[] avgMarks = new double[]{};

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean exit = false;

        //Main menu
        while (!exit) {
            menuTitle("WELCOME TO GDSE MARKS MANAGEMENT SYSTEM");

            System.out.print("[1] Add New Student" + "\t"+ "\t"+ "\t");
            System.out.println("[2] Add New Student With Marks");
            System.out.print("[3] Add Marks"+ "\t"+ "\t"+ "\t"+ "\t");
            System.out.println("[4] Update Student Details");
            System.out.print("[5] Update Marks"+ "\t"+ "\t"+ "\t");
            System.out.println("[6] Delete Student");
            System.out.print("[7] Print Student Details"+ "\t"+ "\t");
            System.out.println("[8] Print Student Ranks");
            System.out.print("[9] Best in Programming Fundamentals"+ "\t");
            System.out.println("[10] Best in Database Management System"+ "\n");
            System.out.print("Enter an option to continue > ");

            int choice = input.nextInt();
            clearConsole();

            //Selecting an option from the main menu
            switch (choice) {
                case 1:
                    addNewStudent(input);
                    break;
                case 2:
                    addNewStudentWithMarks(input);
                    break;
                case 3:
                    addMarks(input);
                    break;
                case 4:
                    updateStudentDetails(input);
                    break;
                case 5:
                    updateMarks(input);
                    break;
                case 6:
                    deleteStudent(input);
                    break;
                case 7:
                    printStudentDetails(input);
                    break;
                case 8:
                    printStudentRanks(input);
                    break;
                case 9:
                    bestInProgrammingFundamentals(input);
                    break;
                case 10:
                    bestInDatabaseManagementSystem(input);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option number.");
                    break;
            }
        }
        System.out.println("Thank you for using the Student Management System.");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //SORTING ALGORITHMS AND OTHER METHODS

    //method to add menu title for each option
    public static void menuTitle(String title){
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------");
        int titleWidth = 80;
        int numSpaces = (titleWidth - title.length()) / 2;
        System.out.printf("|" + "%" + numSpaces + "s%s%" + numSpaces + "s", "", title, ""+ "|" + "\n");
        System.out.println("-------------------------------------------------------------------------------" + "\n");
    }

    //search student by id and return its index
    public static int searchStudentById (String id){
        for (int i=0; i<studentIds.length;i++){
            if(studentIds[i] != null && studentIds[i].equals(id)){
                return i;
            }
        }
        return -1;
    }

    //search student by id and return its index, display a message if a match is found
    public static int searchStudentById (String id, String message){
        for (int i=0; i<studentIds.length;i++){
            if(studentIds[i] != null && studentIds[i].equals(id)){
                System.out.println(message);
                return i;
            }
        }
        return -1;
    }

    //Dynamically add new student ID and name to the end of the studentIds and studentNames arrays
    public static void addToStudentArr(String id, String name){
        String[] temp1 = new String[studentIds.length+1];
        String[] temp2 = new String[studentNames.length+1];
        for(int i=0; i<studentIds.length;i++){
            temp1[i] = studentIds[i];
            temp2[i] = studentNames[i];
        }
        temp1[temp1.length-1] = id;
        temp2[temp2.length-1] = name;
        studentIds = temp1;
        studentNames = temp2;
    }

    //Validate student ID before asking for name
    public static void addNewIdAndName(Scanner input){
        String id = "";
        String message = "The Student ID already exists"+"\n";
        do{
            System.out.print("\n" + "Enter Student ID : ");
            id = input.next();
        }
        while (searchStudentById(id,message ) > -1);

        System.out.print("Enter Student Name: ");
        String name = input.next();

        addToStudentArr(id,name);
    }

    //Update student name at a specific index of the studentNames array
    public static void addToIndexOfStudentsArr(Scanner input){
        System.out.print("\n" + "Enter Student ID : ");
        String id = input.next();

        if (searchStudentById(id) == -1){
            System.out.print("Invalid Student ID. Do you want to search again? (Y/N) ");
            String response = input.next();
            if (!response.equalsIgnoreCase("Y")) {
                return;
            }
            addToIndexOfStudentsArr(input);
        } else {
            int index = searchStudentById(id);
            updateStudentName(index, input);
        }
    }

    //Update student name based on the index
    public static void updateStudentName (int index, Scanner input){
        System.out.println("Student Name : " + studentNames[index]);
        System.out.print("\n" + "Enter the new student name: ");

        String newName = input.next();

        studentNames[index] = newName;
    }

    //Dynamically add PRF and DBMS marks to the end of the PRFMarks and DBMSMarks arrays
    public static void addToEndOfMarksArr (int marksPRF, int marksDBMS){
        int[] temp1 = new int [PRFMarks.length+1];
        int[] temp2 = new int [DBMSMarks.length+1];
        for(int i=0; i<PRFMarks.length;i++){
            temp1[i] = PRFMarks[i];
            temp2[i] = DBMSMarks[i];
        }
        temp1[temp1.length-1] = marksPRF;
        temp2[temp2.length-1] = marksDBMS;
        PRFMarks = temp1;
        DBMSMarks = temp2;
    }

    //Add PRF and DBMS marks to a specific index of the PRFMarks and DBMSMarks arrays
    public static void addToIndexOfMarksArr(Scanner input){
        System.out.print("\n" + "Enter Student ID : ");
        String id = input.next();

        if (searchStudentById(id) == -1){
            System.out.print("Invalid Student ID. Do you want to search again? (Y/N) ");
            String response = input.next();
            if (!response.equalsIgnoreCase("Y")) {
                return;
            }
            addToIndexOfMarksArr(input);
        } else {
            int index = searchStudentById(id);
            instructToEnterMarks(index, input);
        }
    }

    //Update PRF and DBMS marks at a specific index of the PRFMarks and DBMSMarks arrays
    public static void updateToIndexOfMarksArr(Scanner input){
        System.out.print("\n" + "Enter Student ID : ");
        String id = input.next();

        if (searchStudentById(id) == -1){
            System.out.print("Invalid Student ID. Do you want to search again? (Y/N) ");
            String response = input.next();
            if (!response.equalsIgnoreCase("Y")) {
                return;
            }
            updateToIndexOfMarksArr(input);
        } else {
            int index = searchStudentById(id);
            instructToUpdateMarks(index, input);
        }
    }

    //Provide instructions to update existing marks of a student based on the index
    public static void instructToUpdateMarks (int index, Scanner input){
        System.out.println("Student Name : " + studentNames[index]);

        //Check whether marks are already added
        if (PRFMarks[index] > -1){
            System.out.println("\n"+"Programming Fundamentals Marks : " + PRFMarks[index]);
            System.out.println("Database Management System Marks : " + DBMSMarks[index] + "\n");
            updateStudentMarks(index, input, "Enter new ");
        } else {
            System.out.print("\n"+"This student's marks yet to be added." + "\n"
                    + "Do you want to update the marks of another student? (Y/N) ");
            String response = input.next();
            if (!response.equalsIgnoreCase("Y")) {
                return;
            } else {
                updateToIndexOfMarksArr(input);
            }
        }
    }

    //Update PRF and DBMS marks of a given index
    public static void updateStudentMarks (int index, Scanner input, String prefix){
        int marksPRF = 0;
        int marksDBM = 0;
        do{
            System.out.print("\n" + prefix + "Programming Fundamentals Marks : ");
            marksPRF = input.nextInt();
        }
        while (validateMarks(marksPRF));

        do{
            System.out.print(prefix + "Database Management System Marks : ");
            marksDBM = input.nextInt();
        }
        while (validateMarks(marksDBM));
        PRFMarks[index] = marksPRF;
        DBMSMarks[index] = marksDBM;
    }

    //Enter marks for a student which was added without marks, based on the index
    public static void instructToEnterMarks (int index, Scanner input){
        System.out.println("Student Name : " + studentNames[index]);

        //Check whether marks are already added
        if (PRFMarks[index] > -1){
            System.out.println("This student's marks have been already added." + "\n"
                    + "If you want to update the marks, please use [4] Update Marks option."+ "\n");
        } else {
            updateStudentMarks(index, input, "");
        }
    }

    //Ask for valid range of marks before adding to the marks arrays
    public static void addNewMarks(Scanner input){
        int marksPRF = 0;
        int marksDBM = 0;

        do{
            System.out.print("Programming Fundamentals Marks : ");
            marksPRF = input.nextInt();
        }
        while (validateMarks(marksPRF));

        do{
            System.out.print("Database Management System Marks : ");
            marksDBM = input.nextInt();
        }
        while (validateMarks(marksDBM));

        addToEndOfMarksArr(marksPRF, marksDBM);
    }

    //Validate marks
    public static boolean validateMarks(int marks){
        if(marks > 100 || marks < 0){
            System.out.println("Invalid marks, please enter correct marks." + "\n");
            return true;
        }
        return false;
    }

    //Dynamically add placeholders to other arrays until Total Marks, Ranks, Average Marks are calculated
    public static void addToEndOfOtherArrays (int placeholder){
        int[] temp1 = new int [totalMarks.length+1];
        int[] temp2 = new int [ranks.length+1];
        double[] temp3 = new double[avgMarks.length+1];
        for(int i=0; i<totalMarks.length;i++){
            temp1[i] = totalMarks[i];
            temp2[i] = ranks[i];
            temp3[i] = avgMarks[i];
        }
        temp1[temp1.length-1] = placeholder;
        temp2[temp2.length-1] = placeholder;
        temp3[temp3.length-1] = placeholder;
        totalMarks = temp1;
        ranks = temp2;
        avgMarks = temp3;
    }

    //Delete student ID, Name, PRFMarks, DBMSMarks based on the index
    public static void deleteStudentByIndex(Scanner input){
        System.out.print("\n" + "Enter Student ID : ");
        String id = input.next();

        if (searchStudentById(id) == -1){
            System.out.print("Invalid Student ID. Do you want to search again? (Y/N) ");
            String response = input.next();
            if (!response.equalsIgnoreCase("Y")) {
                return;
            }
            deleteStudentByIndex(input);
        } else {
            int index = searchStudentById(id);

            for (int i= index; i<studentIds.length-1;i++){
                studentIds[i] = studentIds[i+1];
                studentNames[i] = studentNames[i+1];
                PRFMarks[i] = PRFMarks[i+1];
                DBMSMarks[i] = DBMSMarks[i+1];
                totalMarks[i] = totalMarks[i+1];
                avgMarks[i] = avgMarks[i+1];
                ranks[i] = ranks[i+1];
            }
            studentIds = Arrays.copyOf(studentIds, studentIds.length-1);
            studentNames = Arrays.copyOf(studentNames, studentNames.length-1);
            PRFMarks = Arrays.copyOf(PRFMarks, PRFMarks.length-1);
            DBMSMarks = Arrays.copyOf(DBMSMarks, DBMSMarks.length-1);
            totalMarks = Arrays.copyOf(totalMarks, totalMarks.length-1);
            avgMarks = Arrays.copyOf(avgMarks, avgMarks.length-1);
            ranks = Arrays.copyOf(ranks, ranks.length-1);
        }
    }

    //Calculate Total, Average
    public static void calculateTotalAndAvg (){
        for(int i=0; i<totalMarks.length;i++){
            totalMarks[i] = PRFMarks[i] + DBMSMarks[i];
            avgMarks[i] = (double) totalMarks[i] / subjects.length;
        }
    }

    //Sort arrays in descending order
    public static void sortDescending(int[] baseAr){

        for(int i=1; i<baseAr.length; i++){
            for(int j=0; j<i; j++){
                if(baseAr[i]>baseAr[j]){
                    //Sort total marks array
                    int t1 = totalMarks[i];
                    totalMarks[i] = totalMarks[j];
                    totalMarks[j] = t1;

                    //Sort average marks array
                    double t2 = avgMarks[i];
                    avgMarks[i] = avgMarks[j];
                    avgMarks[j] = t2;

                    //Sort PRFMarks array
                    int t3 = PRFMarks[i];
                    PRFMarks[i] = PRFMarks[j];
                    PRFMarks[j] = t3;

                    //Sort DBMSMarks array
                    int t4 = DBMSMarks[i];
                    DBMSMarks[i] = DBMSMarks[j];
                    DBMSMarks[j] = t4;

                    //Sort Student ID array
                    String t5 = studentIds[i];
                    studentIds[i] = studentIds[j];
                    studentIds[j] = t5;

                    //Sort Student Name array
                    String t6 = studentNames[i];
                    studentNames[i] = studentNames[j];
                    studentNames[j] = t6;
                }
            }
        }
    }

    //Rank students based on an array
    public static void rankStudents(int[] baseAr){
        int rank = 1;
        for(int i=0; i<baseAr.length;i++){
            ranks[i] = rank;
            rank++;
        }
    }

    //Display student details
    public static void enterStudentToDisplay(Scanner input){
        System.out.print("\n" + "Enter Student ID : ");
        String id = input.next();

        if (searchStudentById(id) == -1){
            System.out.print("Invalid Student ID. Do you want to search again? (Y/N) ");
            String response = input.next();
            if (!response.equalsIgnoreCase("Y")) {
                return;
            }
            enterStudentToDisplay(input);
        } else {
            int index = searchStudentById(id);
            if(PRFMarks[index] > -1){
                System.out.println("Student Name  : " + studentNames[index]);
                displayStudentDetails(index);
            }else {
                System.out.println("Marks yet to be added" +"\n");
            }

        }
    }

    public static String  displayPlace(int index){
        if(ranks[index] == 1) {
             return "(First)";
        } else if (ranks[index] == 2){
            return "(Second)";
        } else if (ranks[index] == 3){
            return "(Third)";
        } else if(ranks[index] == ranks.length){
            return "(Last)";
        }
        return "";
    }

    public static void displayStudentDetails(int index){
        int widthCol1 = 40;
        int widthCol2 = 13;
        
        String col1Row1 = String.format("%-" + widthCol1 + "s", "Programming Fundamentals Marks");
        String col1Row2 = String.format("%-" + widthCol1 + "s", "Database Management System Marks");
        String col1Row3 = String.format("%-" + widthCol1 + "s", "Total Marks");
        String col1Row4 = String.format("%-" + widthCol1 + "s", "Avg. Marks");
        String col1Row5 = String.format("%-" + widthCol1 + "s", "Rank");
        String col2Row1 = String.format("%" + widthCol2 + "s", PRFMarks[index]);
        String col2Row2 = String.format("%" + widthCol2 + "s", DBMSMarks[index]);
        String col2Row3 = String.format("%" + widthCol2 + "s", totalMarks[index]);
        String col2Row4 = String.format("%" + widthCol2 + "s", avgMarks[index]);
        String col2Row5 = String.format("%" + widthCol2 + "s", ranks[index] + displayPlace(index));
        
        System.out.println("+----------------------------------------+-------------+");
        System.out.print("|" + col1Row1 + "|");
        System.out.println(col2Row1+ "|");
        System.out.print("|" + col1Row2 + "|");
        System.out.println(col2Row2 + "|");
        System.out.print("|"  + col1Row3 + "|");
        System.out.println(col2Row3 + "|");
        System.out.print("|" + col1Row4 + "|");
        System.out.println( col2Row4 + "|");
        System.out.print("|"  + col1Row5 + "|");
        System.out.println( col2Row5 + "|");
        System.out.println("+----------------------------------------+-------------+");

    }

    //Display students ranked by total
    public static void displayRanksByTotal(){
        int widthCol1 = 5;
        int widthCol2 = 5;
        int widthCol3 = 20;
        int widthCol4 = 12;
        int widthCol5 = 10;
        
        String col1Row1 = String.format("%-" + widthCol1 + "s", "Programming Fundamentals Marks");

        System.out.println("+-----+-----+--------------------+------------+----------+");
        System.out.println("|" + String.format("%-" + widthCol1 + "s", "Rank") 
        + "|" + String.format("%-" + widthCol2 + "s", "ID") 
        + "|" + String.format("%-" + widthCol3 + "s", "Name")
        + "|" + String.format("%" + widthCol4 + "s",  "Total Marks") 
        + "|" + String.format("%" + widthCol5 + "s",  "Avg. Marks" + "|"));
        System.out.println("+-----+-----+--------------------+------------+----------+");

        for (int i=0; i<ranks.length;i++){
            if(PRFMarks[i] > -1){
                System.out.println("|" + String.format("%-" + widthCol1 + "s", ranks[i]) 
                + "|" + String.format("%-" + widthCol2 + "s", studentIds[i]) 
                + "|" + String.format("%-" + widthCol3 + "s", studentNames[i])
                + "|" + String.format("%" + widthCol4 + "s",  totalMarks[i]) 
                + "|" + String.format("%" + widthCol5 + "s",  String.format("%.2f",avgMarks[i])) + "|");
            }

        }

        System.out.println("+-----+-----+--------------------+------------+----------+");
    }

    //Display students ranked by subject marks
    public static void displayRanksBySubject(int[] baseAr, int[] alternateAr, int col1, int col2){
        int widthCol1 = 5;
        int widthCol2 = 20;
        int widthCol3 = 10;
        int widthCol4 = 10;
        
        String col1Row1 = String.format("%-" + widthCol1 + "s", "Programming Fundamentals Marks");

        System.out.println("+-----+--------------------+----------+----------+");
        System.out.println("|" + String.format("%-" + widthCol1 + "s", "ID") 
        + "|" + String.format("%-" + widthCol2 + "s", "Name") 
        + "|" + String.format("%-" + widthCol3 + "s", subjects[col1] + " Marks")
        + "|" + String.format("%-" + widthCol4 + "s", subjects[col2] + " Marks")+"|"); 
        System.out.println("+-----+--------------------+----------+----------+");

        for (int i=0; i<ranks.length;i++){
            if(PRFMarks[i] > -1){
                System.out.println("|" + String.format("%-" + widthCol1 + "s", studentIds[i]) 
                + "|" + String.format("%-" + widthCol2 + "s", studentNames[i]) 
                + "|" + String.format("%-" + widthCol3 + "s", baseAr[i])
                + "|" + String.format("%-" + widthCol4 + "s",  alternateAr[i]) + "|");
            }

        }
       System.out.println("+-----+--------------------+----------+----------+");
    }

    //Clear console
    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }

    //SORTING ALGORITHMS AND OTHER METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //MENU OPTIONS

    //Menu option 1 : Add a new student ID and name
    static void addNewStudent(Scanner input) {
        menuTitle("ADD NEW STUDENT");

        addNewIdAndName(input);

        //add -1 as default marks for the new student
        addToEndOfMarksArr(-1,-1);

        //add -1 as a placeholder for rest of the arrays
        addToEndOfOtherArrays(-1);

        System.out.print("\n"+"Student has been added successfully. Do you want to add a new student? (Y/N) ");
        String response = input.next();
        if (!response.equalsIgnoreCase("Y")) {

            return;
        }
        addNewStudent(input);
    }

    //Menu option 2 : Add a new student ID, name, PRFMarks and DBMSMarks
    private static void addNewStudentWithMarks(Scanner input) {
        menuTitle("ADD NEW STUDENT WITH MARKS");

        addNewIdAndName(input);

        addNewMarks(input);

        //add -1 as a placeholder for rest of the arrays
        addToEndOfOtherArrays(-1);

        System.out.print("Student has been added successfully. Do you want to add a new student? (Y/N) ");
        String response = input.next();
        if (!response.equalsIgnoreCase("Y")) {

            return;
        }
        addNewStudentWithMarks(input);
    }

    //Menu option 3 : Add PRFMarks and DBMSMarks to a Student which is added without marks
    private static void addMarks (Scanner input){
        menuTitle("ADD MARKS");
       
        addToIndexOfMarksArr(input);

        System.out.print("Marks have been added. Do you want to add marks for another student? (Y/N) ");
        String response = input.next();
        if (!response.equalsIgnoreCase("Y")) {

            return;
        }
        addMarks(input);
    }

    //Menu option 4 : Update student name
    private static void updateStudentDetails (Scanner input){
        menuTitle("UPDATE STUDENT DETAILS");

        addToIndexOfStudentsArr(input);

        System.out.print("Student details have been updated successfully."+ "\n" + "Do you want to update details for another student? (Y/N) ");
        String response = input.next();
        if (!response.equalsIgnoreCase("Y")) {

            return;
        }
        updateStudentDetails(input);
    }

    //Menu option 5 : Update already entered student marks
    private static void updateMarks (Scanner input){
        menuTitle("UPDATE MARKS");

        updateToIndexOfMarksArr(input);

        System.out.print("Marks have been updated successfully."+ "\n" + "Do you want to update marks for another student? (Y/N) ");
        String response = input.next();
        if (!response.equalsIgnoreCase("Y")) {
            return;
        }
        updateMarks(input);
    }

    //Menu option 6 : Delete all details of a student
    private static void deleteStudent (Scanner input){
        menuTitle("DELETE STUDENT");

        deleteStudentByIndex(input);

        System.out.print("Student has been deleted successfully."+ "\n" + "Do you want to delete another student? (Y/N) ");
        String response = input.next();
        if (!response.equalsIgnoreCase("Y")) {
            return;
        }
        deleteStudent(input);
    }

    //Menu option 7 : Print Subject marks, Total marks, Avg. marks, rank of a Student
    private static void printStudentDetails (Scanner input){
        menuTitle("PRINT STUDENT DETAILS");       

        calculateTotalAndAvg();
        sortDescending(totalMarks);
        rankStudents(totalMarks);
        enterStudentToDisplay(input);

        System.out.print("Do you want to search another student details? (Y/N) ");
        String response = input.next();
        if (!response.equalsIgnoreCase("Y")) {
            return;
        }
        printStudentDetails(input);

    }

    //Menu option 8 : Print Student rank, ID, name, total marks, Avg marks sorted by to total marks
    private static void printStudentRanks (Scanner input){
        menuTitle("PRINT STUDENTS' RANKS");

        calculateTotalAndAvg();
        sortDescending(totalMarks);
        rankStudents(totalMarks);
        displayRanksByTotal();

        System.out.print("Do you want to go back to main menu? (Y/N) ");
        String response = input.next();
        if (response.equalsIgnoreCase("Y")) {
            return;
        }
        printStudentRanks(input);
    }

    //Menu option 9 : Print Student ID, name, PRF marks, DBMS marks sorted by to PRF marks
    private static void bestInProgrammingFundamentals (Scanner input){
        menuTitle("BEST IN PROGRAMMING FUNDAMENTALS");

        calculateTotalAndAvg();
        sortDescending(PRFMarks);
        rankStudents(PRFMarks);
        displayRanksBySubject(PRFMarks, DBMSMarks, 0,1);

        System.out.print("Do you want to go back to main menu? (Y/N) ");
        String response = input.next();
        if (response.equalsIgnoreCase("Y")) {
            return;
        }
        bestInProgrammingFundamentals(input);
    }

    //Menu option 10 : Print Student ID, name, DBMS marks, PRF marks sorted by to DBMS marks
    private static void bestInDatabaseManagementSystem (Scanner input){
        menuTitle("BEST IN DATABASE MANAGEMENT SYSTEM");

        calculateTotalAndAvg();
        sortDescending(DBMSMarks);
        rankStudents(DBMSMarks);
        displayRanksBySubject(DBMSMarks, PRFMarks, 1, 0);

        System.out.print("Do you want to go back to main menu? (Y/N) ");
        String response = input.next();
        if (response.equalsIgnoreCase("Y")) {
            return;
        }
        bestInDatabaseManagementSystem(input);
    }
}



