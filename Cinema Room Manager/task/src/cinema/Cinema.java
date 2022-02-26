package cinema;

import java.util.Scanner;

public class Cinema {

  static  Scanner scanner = new Scanner(System.in);

    static int row;
    static int seats;
    static String[][] arr;
    static int amountSeats;
    static int ticketsValue;
    static float ticketsPercent;
    static int currentProfit;
    static int totalProfit;

    public static void main(String[] args) {
        boolean exit = false;

        System.out.println("Enter the number of rows:");
        row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        arr = createArray(row, seats);
        amountSeats = row*seats;

        int fRow = (int)row/2;
        if(row*seats <= 60){
            totalProfit = 10*row*seats;
        }else{
            totalProfit = 10*fRow*seats+8*(row-fRow)*seats;
        }

        do {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
        int i = scanner.nextInt();

        switch (i) {
            case 1: {
                printArray(arr);
            }break;
            case 2: {
                choiceTicket();
            }break;
            case 3: {
                statistics();
            }break;
            case 0: {
                exit=true;
            }break;
        }
        }while (!exit);
    }



    static String[][] createArray(int a, int b){
        String[][] array = new String[a+1][b+1];
        for (int i = 0; i <= a; i++) {
            for (int j = 0; j <= b; j++) {
                if(i==0){
                    array[i][j] = String.valueOf(j+" ");
                }else {
                    array[i][j] = "S ";
                }
                array[i][0] = String.valueOf(i+" ");
            }
        }
        array[0][0] = "  ";
        return array;
    }

    static String[][] updateArray(String[][] arr, int a, int b){
        String[][] updArr=arr;




        updArr[a][b] = "B ";
        ticketsValue++;
        ticketsPercent = ((float) ticketsValue/(float) amountSeats)*100;
        return updArr;
    }

    static int costTicket(int rowCinema, int seatCinema,int row, int seats){
        int firstRow = (int)rowCinema/2;

        if(rowCinema*seatCinema <= 60){
            currentProfit +=10;
            return 10;
        }else if(row <= firstRow) {
            currentProfit +=10;
            return 10;

        }else {
            currentProfit +=8;
            return 8;
        }
    }

    static void printArray(String[][] arr){
        System.out.println("Cinema:");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    static void choiceTicket(){

        System.out.println();
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatsNumber = scanner.nextInt();

        if( rowNumber >= arr.length || seatsNumber >= arr[0].length){
            System.out.println();
            System.out.println("Wrong input!");
            choiceTicket();
            return;
        }else if ( arr[rowNumber][seatsNumber].equals("B ") ){
            System.out.println();
            System.out.println("That ticket has already been purchased!");
            choiceTicket();
            return;
        }

        System.out.println("Ticket price: $" + costTicket(row,seats,rowNumber,seatsNumber));
        System.out.println();
        updateArray(arr,rowNumber,seatsNumber);

    }

    static void statistics(){
        System.out.println();
        System.out.printf("Number of purchased tickets: %d \n", ticketsValue);
        System.out.printf("Percentage: %.2f%% \n", ticketsPercent);
        System.out.printf("Current income: $%d \n", currentProfit);
        System.out.printf("Total income: $%d \n", totalProfit);
    }

}