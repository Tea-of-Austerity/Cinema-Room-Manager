package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        System.out.println("Enter the number of rows:");
        int[] row = new int[numRows()];
        System.out.println("Enter the number of seats in each row");
        int[] col = new int[numCols()];
        System.out.println();
        for (int i=0; i<row.length;i++){
            row[i]=i+1;
        }
        for (int j=0; j<col.length;j++){
            col[j]=j+1;
        }
        char[][] seatMap = initializeSeatMap(row,col);

        int ticketPurchased = 0;
        int currentIncome =0;
        do {
            int choice = userSelection();
            switch (choice) {
                case 1:
                    System.out.println();
                    showSiteMap(seatMap);
                    break;
                case 2:
                    while(true) {
                        System.out.println();
                        System.out.println("Enter a row number:");
                        int rowNum = numRows();
                        System.out.println("Enter a seat number in that row:");
                        int colNum = numCols();
                        System.out.println();
                        if(rowNum > row.length+1 || colNum>col.length+1){
                            System.out.println("Wrong input!");
                            break;
                        }
                        if (seatMap[rowNum - 1][colNum - 1] != 'B') {
                            seatMap[rowNum - 1][colNum - 1] = 'B';
                        } else {
                            System.out.println(String.format("That ticket has already been purchased!\n"));
                            break;
                        }

                        int singlePrice = spotPrice(row, col, rowNum, colNum);
                        System.out.println(String.format("Ticket Price: $" + singlePrice));
                        ticketPurchased++;
                        currentIncome += singlePrice;
                        System.out.println();
                        break;
                    }
                    break;
                case 3:
                    System.out.println("Number of purchased tickets: "+ticketPurchased);
                    System.out.printf("Percentage: %.2f%%%n", (double) ticketPurchased/(row.length*col.length)*100);
                    System.out.println("Current income: $"+ currentIncome);
                    System.out.println("Total income: $"+ totalPrice(row.length,col.length));
                    System.out.println();
                    break;
                case 0:
                    System.exit(0);
                default:
                    break;
            }
        }while(true);


    }
    public static int numRows(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int numCols(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int totalPrice(int rows, int cols){
        int total = 0;
        if(rows*cols<60){
            total = rows*cols*10;
        }
        else{
            return (rows/2)*cols*10+(rows-rows/2)*cols*8;
        }
        return total;
    }
    public static int spotPrice(int[] row, int[] col,int rowNum, int colNum){
        int price = 0;
        if(row.length*col.length<60){
            return price = 10;
        }
        else {
            if (rowNum > (row.length / 2)){
                return price = 8;
            }
            else{
                return price =10;
            }
        }
    }
    public static char[][] initializeSeatMap(int[] row, int[] col){
        char[][] seatMap = new char[row.length][col.length];
        for (int i = 0; i<row.length;i++){
            for (int j =0; j<col.length;j++){
                seatMap[i][j] = 'S';
            }
        }
        return seatMap;
    }
    public static void showSiteMap(char[][] seatMap){

        StringBuilder builder = new StringBuilder();
        builder.append("  ");
        for (int j = 0; j<seatMap[0].length;j++){
            builder.append(j+1);
            builder.append(" ");
        }
        builder.charAt(builder.lastIndexOf(" "));
        builder.append("\n");

        for (int i =0; i<seatMap.length;i++) {
            builder.append(i+1);
            builder.append(" ");
            for (int j =0; j< seatMap[i].length;j++) {
                builder.append(seatMap[i][j]);
                builder.append(" ");
            }
            builder.deleteCharAt(builder.lastIndexOf(" "));
            builder.append("\n");
        }
        System.out.println("Cinema:");
        System.out.println(builder.toString());
    }

    public static int userSelection() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        return userChoice;
    }
}