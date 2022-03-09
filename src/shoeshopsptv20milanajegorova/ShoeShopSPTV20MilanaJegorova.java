package shoeshopsptv20milanajegorova;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ShoeShopSPTV20MilanaJegorova {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner (System.in);
        
        boolean working = true;
        
        createFile("shoes.txt");
        Shoe[] shoes = getShoes();
        
        createFile("customers.txt");
        Customer[] customers = getCustomers();
        
        createFile("gain.txt");
        int gain = getAllTimeGain();
        
        while(working){
            System.out.println("\n-- Список функций --");
            System.out.println("0. Выход из программы");
            System.out.println("1. Добавить модель");
            System.out.println("2. Список продоваемых моделей");
            System.out.println("3. Добавить покупателя");
            System.out.println("4. Список зарегистрированных покупателей");
            System.out.println("5. Покупка покупателем обуви");
            System.out.println("6. Доход магазина за всё время работы");
            System.out.println("7. Добавить денег покупателю\n");
            
            System.out.print("Выберите функцию: ");          
            String choice = scanner.nextLine();

            String str = "";
            
            switch(choice){
                case "0":
                    updateCustomers(customers);              
                    updateShoes(shoes);
                    updateGain(gain);
                    working = false;
                    break;
                case "1":
                    System.out.print("\nВведите название обуви: ");
                    String shoeName = scanner.nextLine();
                    System.out.print("Введите цену: ");
                    int shoePrice = scanner.nextInt();
                    Shoe shoe = new Shoe(shoeName, shoePrice);
                    str = shoe.getName()+ "*"+ shoe.getPrice()+"\n";
                    File file = createFile("shoes.txt");
                    writeToFile(file, str);
                    break;
                case "2":        
                    shoes = getShoes();
                    System.out.println("");
                    for (int i = 0; i < shoes.length; i++){
                        System.out.println("#" + i + ". " + shoes[i].getName() + "(" + shoes[i].getPrice() + "$)");
                    }
                    break;
                case "3":
                    System.out.print("\nВведите имя: ");
                    String custName = scanner.nextLine();
                    System.out.print("Введите фамилию: ");
                    String custSurname = scanner.nextLine();
                    System.out.print("Введите базовое количество денег: ");
                    int custMoney = scanner.nextInt();
                    Customer cust = new Customer(custName, custSurname, custMoney);
                    str = cust.getName() + "*" + cust.getSurname() + "*" + cust.getMoney() + "\n";
                    file = createFile("customers.txt");
                    writeToFile(file, str);
                    break;
                case "4":        
                    customers = getCustomers();
                    System.out.println("");
                    for (int i = 0; i < customers.length; i++){
                        System.out.println("#" + i + ". " + customers[i].getName() + " " + customers[i].getSurname() + " (" + customers[i].getMoney() + "$)");
                    }   
                    break;
                case "5":
                    System.out.print("\nВведите ID покупателя: ");
                    int custId = scanner.nextInt();
                    System.out.print("Введите ID обуви: ");
                    int shoeId = scanner.nextInt();    
                    if (shoes[shoeId].getPrice() > customers[custId].getMoney()) {
                        System.out.println("\nУ покупателя недостаточно денег!");
                    } else {
                        customers[custId].setMoney(customers[custId].getMoney() - shoes[shoeId].getPrice());
                        gain += shoes[shoeId].getPrice();
                        shoes[shoeId] = null;
                        System.out.println("\nОбувь успешно куплена!");
                    }
                    updateCustomers(customers);              
                    updateShoes(shoes);
                    updateGain(gain);
                    break;
                case "6":
                    gain = getAllTimeGain();
                    System.out.println("\nДоход магазина за все время: " + gain + "$");
                    break;
                case "7":        
                    System.out.print("\nВведите ID покупателя: ");
                    int id = scanner.nextInt();
                    System.out.print("Введите количество денег: ");
                    int money = scanner.nextInt();
                    customers[id].setMoney(customers[id].getMoney() + money);
                    updateCustomers(customers);
                    break;               
            }
                     
        }
    }
    
    public static File createFile(String fileName){
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
            //System.out.println("File created: " + myObj.getName());
            } else {
            //System.out.println("File already exists.");
            }
            return myObj;
        } catch (IOException e) {
            //System.out.println("An error occurred.");
            e.printStackTrace();
            return new File(fileName);
        }
    }
    
    public static void writeToFile(File file, String str){
        try {
          FileWriter myWriter = new FileWriter(file, true);
          myWriter.write(str);
          myWriter.flush();
          myWriter.close();
          //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
          //System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }
    
    public static void clearFile(File file){
        try {
          FileWriter myWriter = new FileWriter(file);
          myWriter.write("");
          myWriter.flush();
          myWriter.close();
          //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
          //System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }
    
    public static void updateCustomers(Customer[] customers) {
        File file = createFile("customers.txt");
        clearFile(file);
        for (int i = 0; i < customers.length; i++) {
            String str = customers[i].getName() + "*" + customers[i].getSurname() + "*" + customers[i].getMoney() + "\n";  
            writeToFile(file, str);
        }
    }
    
    public static void updateShoes(Shoe[] shoes) {
        File file = createFile("shoes.txt");
        clearFile(file);
        for (int i = 0; i < shoes.length; i++) {
            if (shoes[i] == null) {
                continue;
            }
            String str = shoes[i].getName()+ "*" + shoes[i].getPrice()+"\n";
            writeToFile(file, str);
        }
    }
    
    public static void updateGain(int gain) {
        File file = createFile("gain.txt");
        clearFile(file);
        String str = Integer.toString(gain);
        writeToFile(file, str);  
    }
    
    public static Shoe[] getShoes(){
        try {
          File myObj = new File("shoes.txt");
          Scanner myReader = new Scanner(myObj);
          int iterator = 0;
          while (myReader.hasNextLine()) {
              myReader.nextLine();
              iterator ++;
          }
            Shoe[] shoes;
            shoes = new Shoe[iterator];
          iterator = 0;
          myReader = new Scanner(myObj);
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String sName = data.substring(0, data.indexOf("*"));
            int sPrice = Integer.parseInt(data.substring(data.indexOf("*")+1));

            shoes[iterator] = new Shoe(sName, sPrice);
            iterator ++; 
          }
          myReader.close();
          return shoes;
        } catch (FileNotFoundException e) {
          //System.out.println("An error occurred.");
          e.printStackTrace();
          return new Shoe[0];
        }
    
    }
    
    public static Customer[] getCustomers(){
        try {
          File myObj = new File("customers.txt");
          Scanner myReader = new Scanner(myObj);
          int iterator = 0;
          while (myReader.hasNextLine()) {
              myReader.nextLine();
              iterator ++;
          }
            Customer[] customers;
            customers = new Customer[iterator];
          iterator = 0;
          myReader = new Scanner(myObj);
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String cName = data.substring(0, data.indexOf("*"));
            String cSurname = data.substring(data.indexOf("*")+1, data.indexOf("*", data.indexOf("*")+1));
            int cMoney = Integer.parseInt(data.substring(data.indexOf("*", data.indexOf("*")+1)+1));
                    
            customers[iterator] = new Customer(cName, cSurname, cMoney);
            iterator ++; 
          }
          myReader.close();
          return customers;
        } catch (FileNotFoundException e) {
          //System.out.println("An error occurred.");
          e.printStackTrace();
          return new Customer[0];
        }
    
    }
    
    public static int getAllTimeGain(){
        try {
            File myObj = new File("gain.txt");
            Scanner myReader = new Scanner(myObj);
            int gain = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                gain = Integer.parseInt(data);
            }
            myReader.close();
            return gain;
        } catch (FileNotFoundException e) {
            //System.out.println("An error occurred.");
            e.printStackTrace();
            return -1;
        }
    
    }
}
