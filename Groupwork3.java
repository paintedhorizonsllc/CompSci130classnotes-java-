package myPackage;

import java.util.Scanner;

public class MainClass {

	static String [ ] itemNames = new String[25]; 
	static double [ ][ ] itemInfo = new double[25][5]; 
	static int itemCount = 0;

	static Scanner input = new Scanner (System.in); 
	public static void main(String[] args) {
        //
       
        ProcessCommands();
        
      } // end of main




//The Find function takes a name as the parameter. It determines 
// whether an item with that name is in the itemNames list. If so, 
// it returns the index of where the item is stored. If there is no // item in the list with that name, it returns -1.
//params: (String) 
//----------------------------------------------------------------- 

	public static int FindIndexOfItem(String lookUpItem)
	{//start FinIndexOfItem method
		 for (int i = 0; i < itemCount; i++) {
	         if (itemNames[i].equals(lookUpItem)) {
	             return i;
	         }
	     }
	     return -1;
	}//end FinIndexOfItem method
	
	// The function reads and processes user’s commands. After reading a
	// command, the next step is to call the corresponding method for each
	// command. The method uses switch statements for that.
	// params: (none)
	//-----------------------------------------------------------------
	public static void ProcessCommands( )
	{//start of ProcessCommands method
	 
		String command;
		command = input.next();
		while (! (command.equals("Quit")))
		{ // starts of while
			switch ( command )
			{
				case "Add":
					ProcessAddCommand( ); 
					break;
				case "Output":
					ProcessOutputCommand( ); 
					break;
				case "Sell": 
					ProcessSellCommand( ); 
					break;
				case "Display": 
					ProcessDisplayCommand( ); 
					break;
				default:
					System.out.println("Bad command.");
					input.nextLine(); //////// skip the rest of the line
					break;
			} // end switch
			
			command = input.next(); // read the next command
		} // end while loop
		//display a message that tells the user about the termination of the run
			System.out.println("Program Termination Successfully!");
	} // end of method ProcessCommands
	
	//The method processes add commands. It adds a new item into the
	//store’s database. The method reads in the itemName, pOrdered,
	//manufPrice, and sellingPrice. The new item must be added to the end 
	// of the list if an item with that name doesn't already exist. The 
	// name will be a string, while other values are of type double. The 
	// method doesn't need to check this. Note that the method should set 
	// the number of pieces (of an item) in the store as the same as the 
	// number of pieces ordered, and it should set the number of pieces of 
	// the item sold is zero. If the item is already in the list, the
	//method should print an appropriate message.
	//params: (none) 
	//----------------------------------------------------------------- 
	public static void ProcessAddCommand() {
		String itemName;
		double  pOrdered;
		double manufPrice;
		double sellingPrice;

	//System.out.print("\nEnter name of item to add: ");										//this is my testing to check my input - lra
		itemName = input.next();
		pOrdered = input.nextDouble();
		manufPrice = input.nextDouble();
		sellingPrice = input.nextDouble();
		
		int index = FindIndexOfItem(itemName);
		if (index != -1) {
			System.out.printf("\ncannot add, %s already in list!\n", itemName);
			return;
			
		}
		
		//removed itemList and check the max length instead to see if list is full, else it will add into itemName based on current count
		if (itemCount == itemNames.length) {
			System.out.println("Cannot add, the list is full.");
			return;
		}
		itemNames[itemCount] = itemName; //storing the item name into thje current array
		  
		  
		itemInfo[itemCount][0] = pOrdered;       // pOrdered
	    itemInfo[itemCount][1] = pOrdered;       // pInStore starts same as ordered
	    itemInfo[itemCount][2] = 0;              // pSold starts at 0
	    itemInfo[itemCount][3] = manufPrice;     // manufPrice
	    itemInfo[itemCount][4] = sellingPrice;   // sellingPrice
	    
	    itemCount++;

		System.out.printf("\n%s has been added successfully!", itemName);
		
    }
	//The method processes output commands. The method reads in the
	//itemName and then output the item with that name. The method should 
	// display the item name along with the number of pieces ordered,
	//number of pieces currently in the store, number of pieces sold,
	//manufacturer’s price, and the store’s selling price for the item. 
	// If the item doesn't exist, the method should print an appropriate 
	// message.
	//params: (none) 
	//----------------------------------------------------------------- 
	public static void ProcessOutputCommand()
	{//start of ProcessOutputCommand method
		String itemName = input.next();
        int index = FindIndexOfItem(itemName);

         if (index == -1) {
        	 System.out.printf("Cannot finish this transaction. %s is not in the list!", itemName);
             return;
         }
         else {
            System.out.println("\n\t\tFriendly Hardware Store, Green Bay, WI, 54311");
            System.out.printf("%14s %10s %10s %8s %12s %12s\n", "itemName", "pOrdered", "pInStore", "pSold", "manufPrice", "sellingPrice");
            System.out.printf("%14s %10s %10s %8s %12s %12s\n", "--------", "--------", "--------", "-----", "----------", "------------");
         
            System.out.printf("%14s %10.2f %10.2f %8.2f %12.2f %12.2f\n",
            		itemNames[index],
 		            itemInfo[index][0],  // pOrdered
 		            itemInfo[index][1],  // pInStore
 		            itemInfo[index][2],  // pSold
 		            itemInfo[index][3],  // manufPrice
 		            itemInfo[index][4]   // sellingPrice
 		            		
 		        );
 		  }
 		
 	}//end of ProcessOutputCommand method
	
	//The method processes sell commands. The method reads in the
	//itemName and the number of pieces sold in this command (Transaction) 
	// If the item does exist in the list, the method updates the
	//appropriate counts of the sold item. If the item doesn't exist, the 
	// method should print an appropriate message.
	//params: (none) 
	//-----------------------------------------------------------------
	
	public static void ProcessSellCommand( )
	{//start of ProcessSellCommand method
		  String itemName = input.next();
          double soldPieces = input.nextDouble();

          int index = FindIndexOfItem(itemName);
          if (index == -1) {
              System.out.printf("Cannot finish this transaction. %s is not in the list!", itemName);
              return;
          }

          // Update pInStore and pSold
          itemInfo[index][1] -= soldPieces;  // pInStore
          itemInfo[index][2] += soldPieces;  // pSold

          // IMPORTANT: manufPrice and sellingPrice are NEVER changed here,
          // which keeps CircularSaw’s prices fixed, matching your correction.
          System.out.printf("%.0f pieces of %s have been sold successfully!\n", soldPieces, itemName);
      

	}//end of ProcessSellCommand method
	
	//The method processes display commands. The method should display a 
	// report of all items in the store and their information in a tabular 
	// format. Also, you should display the total inventory (the total
	//selling value of all of the items currently in the store) and the
	
	//total number of items (the sum of the number of pieces of all of the 
	// items in the store). For instance, total inventory equals the sum of 
	// (sellingPrice*pInStore) of all of the items currently in the store 
	// params: (none) 
	//----------------------------------------------------------------- 
	public static void ProcessDisplayCommand( )
	{//start of ProcessDisplayCommand method
		 
		System.out.println("\n\t\tFriendly Hardware Store, Green Bay, WI, 54311");
        System.out.printf("%14s %10s %10s %8s %12s %12s\n", "itemName", "pOrdered", "pInStore", "pSold", "manufPrice", "sellingPrice");
        System.out.printf("%14s %10s %10s %8s %12s %12s\n", "--------", "--------", "--------", "-----", "----------", "------------");
        double totalInventory = 0.0;
        double totalItems = 0.0;
         
        for (int i = 0; i < itemCount; i++) {
            System.out.printf("%14s %10.2f %10.2f %8.2f %12.2f %12.2f\n",
                    itemNames[i],
                    itemInfo[i][0],
                    itemInfo[i][1],
                    itemInfo[i][2],
                    itemInfo[i][3],
                    itemInfo[i][4]
                    );
  	        // 统计总数
	    	totalInventory += itemInfo[i][4] * itemInfo[i][1];
			totalItems += itemInfo[i][1];
        }
        System.out.printf("\nTotal Inventory: $%.2f\n", totalInventory);
        System.out.printf("Total number of items in the store: %.2f\n", totalItems);
	}//end of ProcessDisplayCommand method
}//end class
