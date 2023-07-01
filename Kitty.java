import java.util.*;
	class Kitty{
    private static Scanner store = new Scanner(System.in);
    private static String[] loginDtl ={"1","1"}; // Login Details
    private static String[][] suppliers = new String[0][0]; // Supplier array
	private static String[][] itemcat = new String[0][0]; // Item category array
    private static String[][] items = new String[0][0]; // Items array
    private static boolean itemExists = false; // Used this boolean for supplierbyID and Category by id
    private static String supplierID=""; // Used this varible in few supplier related methods. thats why its here
    private static String CategoryN=""; // Used this varible in few category related methods. thats why its here
    private static String itemCode=""; // Used this varible in few methods. thats why its here
    private static String suppliernumber = "0";

    public static boolean checkUserName(){ // Used this method to check user is exisisting or not
        System.out.print("User name :");
        String userName = store.next();

        if(loginDtl[0].equals(userName)){
            return true;
        }else{
            System.out.print("User name is invalid. please try again! \n ");
            System.out.println("\n");

            return checkUserName();
        }
    }

    public static void checkPassword(){ // Used this method to check user is exisisting or not
        System.out.print("Password :");
        String pw = store.next();

        if(pw.equals(loginDtl[1])){
            return;
        }else{
            System.out.print("Password is incorrect. please try again!\n");
            checkPassword();
        }
    }
    public static void clearConsole(){
        final String os = System.getProperty("os.name");
        try {
            if (os.equals("Linux")) {
                System.out.print("\033\143");
            } else if (os.equals("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {

            System.err.println(e.getMessage());
        }
    }
    public static void mainMenu(){ // Main Menu method

        System.out.println("+---------------------------------------------------------------+");
        System.out.print("|\tWELCOME TO IJSE STOCK MANAGEMENT SYSTEM\t\t\t|\n");
        System.out.println("+---------------------------------------------------------------+\n\n");
        System.out.println("[1] Change the Credentials\t\t [2] Supplier Manage");
        System.out.println("[3] Stock Manage\t\t\t [4] Log Out");
        System.out.println("[5] Exit the System\n");

        System.out.println("Login dtl : "+Arrays.toString(loginDtl));
        System.out.println("Suppliers : ");
        for (int i = 0;i<suppliers.length;i++){
            System.out.println(Arrays.toString(suppliers[i]));
        }
        for (int i = 0;i<itemcat.length;i++){
            System.out.println(Arrays.toString(itemcat[i]));
        }

        System.out.print("Enter an option to continue > ");
        int num= store.nextInt();
        clearConsole();

        switch(num){
            case 1:
                System.out.println("+-----------------------------------------------------------------------+");
                System.out.print("|\t\t\tCREDENTIAL MANAGE\t\t\t\t|\n");
                System.out.println("+-----------------------------------------------------------------------+\n");
                verifyYourUsername();
            case 2:
                supplierManageHome();
                clearConsole();
            case 3:
                stockManagement();
            case 4:
                System.out.println("+---------------------------------------------------------------+");
                System.out.print("|\t\t\tLOGOUT PAGE\t\t\t\t|\n");
                System.out.println("+---------------------------------------------------------------+\n");
                logoutSystem();
            case 5:
                System.exit(0);
        }

    }
    public static void verifyYourUsername(){ // used this method in credential change. 
        System.out.print("Please enter the username to verify it's you : ");
        String userName = store.next();
        if (userName.equals(loginDtl[0])){
            System.out.println("Hey "+ loginDtl[0]);
            verifyYourPassword();
        }else {
            System.out.print("Invalid username. please try again ! \n");
            verifyYourUsername();
        }

    }

    public static void verifyYourPassword(){ // used this method in credential change. 
        System.out.print("Enter your current password : ");
        String passWord= store.next();
        if (passWord.equals(loginDtl[1])){
            changeYourPassword();
        }
        else {
            System.out.println("Incorrect password. Try again!");
            verifyYourPassword();
        }
    }

    public static void changeYourPassword(){ // used this method in credential change. 
        System.out.print("Enter your new password : ");
        String newPassword= store.next();
        loginDtl[1]=newPassword;
        System.out.print("Password changed successfully! Do you want to go to home page (Y/N) : ");
        char yn= store.next().charAt(0);
        if (yn=='Y' || yn=='y'){
            clearConsole();
            mainMenu();
        }else{
            changeYourPassword();
        }
    }

    public static void logoutSystem(){ // Logout method
        checkUserName();
        checkPassword();
        clearConsole();
        mainMenu();
    }
    
    public static void supplierManageHome(){
        System.out.println("+---------------------------------------------------------------+");
        System.out.print("|\t\t\tSUPPLIER MANAGE\t\t\t\t|\n");
        System.out.println("+---------------------------------------------------------------+\n\n");
        System.out.println("[1] Add Supplier\t\t\t [2] Update Supplier");
        System.out.println("[3] Delete Supplier\t\t\t [4] View Suppliers");
        System.out.println("[5] Search Supplier\t\t\t [6] Home Page\n");
        System.out.print("Enter an option to continue > ");
        int supplierManage= store.nextInt();
        switch (supplierManage){
            case 1:
                clearConsole();
                addSupplierHeader();
                break;
            case 2:
                clearConsole();
                updateSupplier();
                break;
            case 3:
                clearConsole();
                deleteSupplier();
                break;
            case 4:
                clearConsole();
                viewSupplier();
                break;
            case 5:
                clearConsole();
                searchSupplier();
                break;
            case 6:
                clearConsole();
                homePage();
                break;
            case 7:
                clearConsole();
                viewSupplier();
                break;
            }
    }
    /**
 * Expands the suppliers array by creating a new 2D array with an additional row compared to the original array.
 * It copies all the elements from the suppliers array to the corresponding positions in the new array,
 * and returns the expanded array.
 */
    public static  String[][] grow(){
        String[][]temp = new String[suppliers.length+1][2];
        for (int i = 0; i < suppliers.length; i++){
            for (int j = 0; j < suppliers[i].length; j++){
                temp[i][j]=suppliers[i][j];
            }
        }
        return temp;
    }
    public static  String[][] additemArray(){
        String[][]temp1 = new String[items.length+1][6];
        for (int i = 0; i < items.length; i++){
            for (int j = 0; j < items[i].length; j++){
                temp1[i][j]=items[i][j];
            }
        }
        return temp1;
    }

    public static void addSupplierHeader(){

        

        addSupplier();
    }
    public static void addSupplier(){
		System.out.println("+---------------------------------------------------------------+");
        System.out.print("|\t\t\tADD SUPPLIER\t\t\t\t|\n");
        System.out.println("+---------------------------------------------------------------+\n\n");
        L1: do {
			
			suppliers=grow();
            System.out.print("Suppier ID : ");
            String SupplierId= store.next();

            for (int i=0;i<suppliers.length;i++){
                if (SupplierId.equals(suppliers[i][0])){
                    System.out.println("Already exists. Try another supplier id.");
                    continue L1;
                }
            }
			
            suppliers[suppliers.length-1][0]=SupplierId;
            System.out.print("Supplier Name : ");
            suppliers[suppliers.length-1][1]=store.next();
            

            System.out.print("Added successfully! Do you want to add another supplier(Y/N)? : ");
            char yn= store.next().charAt(0);

            if (yn=='Y'||yn=='y'){
                //suppliers=grow();
                clearConsole();
                addSupplier();
            }
            else if (yn=='N'||yn=='n'){
                clearConsole();
                supplierManageHome();

            }else{
				System.out.println("wrong input ,Please input Y or N");
				//clearConsole();
				addSupplier();
			}

        } while(true);
    }

    public static void updateSupplier(){

        System.out.println("+---------------------------------------------------------------+");
        System.out.print("|\t\t\tUPDATE SUPPLIER\t\t\t\t|\n");
        System.out.println("+---------------------------------------------------------------+\n\n");

        updatesup();
    }
    public static void updatesup(){
        do{
            System.out.print("Supplier Id :");
            String Sp = store.next();

            for (int i = 0; i < suppliers.length; i++){
                if(Sp.equals(suppliers[i][0])){
                    System.out.print("Supplier name : "+suppliers[i][1]+"\n");
                    System.out.print("Enter the new supplier name :");
                    String newName = store.next();

                    suppliers[i][1]=newName;
                    System.out.print("Update Successfully! Do you want to update another supplier ?(Y/N)");
                    char yn = store.next().charAt(0);

                    if (yn=='Y'||yn=='y'){
                        updatesup();
                        clearConsole();
                    }
                    else if (yn=='N'||yn=='n'){
                        clearConsole();
                        supplierManageHome();


                    }

                }
            }
            System.out.print("can't find supplier id. try again! \n ");

        }while(true);
    }
    public static void deleteSupplier(){

        System.out.println("+---------------------------------------------------------------+");
        System.out.print("|\t\t\tDELETE SUPPLIER\t\t\t\t|\n");
        System.out.println("+---------------------------------------------------------------+\n\n");

        deletingSupplier();
    }
    
    public static void deletingSupplier() {
    

    System.out.print("Supplier ID: ");
    String supId = "";
    supId=store.next();
    boolean supplierFound = false;
    
    /* I used this Code to check if the array is empty. 
       Then i checked availabilty of what i gonna delete*/
    if (suppliers.length > 0) {
    for (int i = 0; i < suppliers.length; i++){
       if(supId.equals(suppliers[i][0])){
          supplierFound= true;
          
       }
    }
	} else {
    System.out.print("Can't Find Supplier id. Try Again2\n");
    deletingSupplier() ; // Run Again
	}
	
	if (supplierFound== false){
		System.out.print("Can't Find Supplier id. Try Again\n");
		deletingSupplier();
		}

    
    /* i used this codes to delete the data i wanna delete. i put every data from current array
     * to a tempory array except the one i want to delete. after that Update old array with the modified array */
	String[][] temp = new String[suppliers.length - 1][0];
    int j = 0;
    

    for (int i = 0; i < suppliers.length; i++) {
        if (supId.equals(suppliers[i][0])) {
            supplierFound = true;
            continue; // Skip copying the row to delete it
        }
        temp[j++] = suppliers[i]; // Copy other rows to the temp array
    }

    if (supplierFound) {
        suppliers = temp; // Update suppliers with the modified array
        System.out.println("Delete successful!");

        System.out.print(" Do you want to delete another supplier? (Y/N):  ");
        char yn = store.next().charAt(0);

        if (yn == 'Y' || yn == 'y') {
            clearConsole();
            deletingSupplier();
        } else {
            clearConsole();
            supplierManageHome();
        }
    } else {
        System.out.print("Can't Find Supplier id. Try Again\n");
        supplierManageHome();
    }
}

    
    public static void viewSupplier(){

        System.out.println("+---------------------------------------------------------------+");
        System.out.print("|\t\t\tVIEW SUPPLIER\t\t\t\t|\n");
        System.out.println("+---------------------------------------------------------------+\n\n");


        System.out.println("+-------------------------+-------------------------+");
        System.out.println("|       SUPPLIER ID       |      SUPPLIER NAME      |");
        System.out.println("+-------------------------+-------------------------+");

        for (int i = 0; i < suppliers.length; i++){
            System.out.println("|\t"+suppliers[i][0]+"\t\t|\t"+suppliers[i][1]+"\t\t|");
        }
        System.out.println("+-------------------------+-------------------------+");

        System.out.print("Do you want to go supplier manage page ( Y/N ) : ");
        char viewop= store.next().charAt(0);

        if((viewop=='Y') || (viewop=='y')){
            clearConsole();
            supplierManageHome();
        }
        else{
            viewSupplier();
        }
    }

    public static void searchSupplier(){

        System.out.println("+---------------------------------------------------------------+");
        System.out.print("|\t\t\tSEARCH SUPPLIER\t\t\t\t|\n");
        System.out.println("+---------------------------------------------------------------+\n\n");

        do{
            System.out.print("Supplier Id :");
            String Sp = store.next();

            for (int i = 0; i < suppliers.length; i++){
                if(Sp.equals(suppliers[i][0])){
                    System.out.print("Supplier name : "+suppliers[i][1]);

                    System.out.print("Do you want to search another supplier ?(Y/N)");
                    char yn = store.next().charAt(0);

                    if (yn=='Y'||yn=='y'){
                        clearConsole();
                        searchSupplier();
                        clearConsole();
                    }
                    else if (yn=='N'||yn=='n'){
                        clearConsole();
                        supplierManageHome();
                    }else{
						System.out.print("Wrong input :(");
						clearConsole();
						searchSupplier();
					}
                }
            }
            System.out.print("can't find supplier id. try again! \n");

        }while(true);


    }
    public static void stockManagement(){

        System.out.println("+-----------------------------------------------------------------------+");
        System.out.print("|\t\t\tSTOCK MANAGEMENT\t\t\t\t|\n");
        System.out.println("+-----------------------------------------------------------------------+\n\n");
        System.out.println("[1] Manage Item Categories\t\t\t [2] Add Item");
        System.out.println("[3] Get Item Supplier Wise\t\t\t [4] View Items");
        System.out.println("[5] Rank Item Per Unit Price\t\t\t [6] Home Page\n");

        System.out.print("Enter an option to continue >");
        int op = store.nextInt();

        switch (op){
            case 1:
                clearConsole();
                manageItemCategories();
                break;
            case 2:
                clearConsole();
                addItem();
                break;
            case 3:
                clearConsole();
                getItemSupplierWise();
                break;
            case 4:
                clearConsole();
                viewItem();
                break;
            case 5:
                clearConsole();
                rankItemPerUnit();
                break;
            case 6:
                clearConsole();
                homePage();
                break;
            }
    }

    public static void manageItemCategories(){

        System.out.println("+---------------------------------------------------------------+");
        System.out.print("|\t\t\tMANAGE ITEM CATEGORIES\t\t\t\t|\n");
        System.out.println("+---------------------------------------------------------------+\n");
        System.out.println("[1] Add New Item Category\t\t\t [2] Delete Item Category");
        System.out.println("[3] Update Item Category\t\t\t [4] Stock Management");
		System.out.println("[5] View Item Category  \t\t\t [6] Go Back");
        System.out.print("Enter an option to continue > ");
        int itemcatManage= store.nextInt();
		
		switch(itemcatManage){
			
			case 1:
				clearConsole();
				addNewItemCategory();
				break;
			
			case 2:
				clearConsole();
				deleteItemCategory();
				
				break;
			
			case 3:
				clearConsole();
				updateItemCategory();
				
				break;
				
			case 4:
				clearConsole();
				stockManage();				
				break;
			case 5:
				clearConsole();
				viewitemcategory();				
				break;
			case 6:
				clearConsole();
				stockManagement();				
				break;
			}
    }

    private static void stockManage() {

    }

    private static void updateItemCategory() {

        do{
            System.out.print("Item Category :");
            String icat = store.next();

            for (int i = 0; i < itemcat.length; i++){
                if(icat.equals(itemcat[i][0])){
                    System.out.print("Item Category : "+itemcat[i][0]+"\n");
                    System.out.print("Enter the new Category name :");
                    String newName = store.next();

                    itemcat[i][0]=newName;
                    System.out.print("Update Successfully! Do you want to update another Category ?(Y/N)");
                    char yn = store.next().charAt(0);

                    if (yn=='Y'||yn=='y'){
                        updateItemCategory();
                        clearConsole();
                    }
                    else if (yn=='N'||yn=='n'){
                        clearConsole();
                        manageItemCategories();

                    }

                }
            }
            System.out.print("can't find category. try again! \n ");
            manageItemCategories();

        }while(true);
	
    }

    private static void viewitemcategory(){ // created to view item category because i wanted to see what i do
        for (int i = 0; i < itemcat.length; i++){
            System.out.println("|\t"+itemcat[i][0]+"\t\t|\t"+itemcat[i][1]+"\t\t|");
        }
        manageItemCategories();
    }

    private static void deleteItemCategory() {
    String[][] temp = new String[itemcat.length - 1][2];

    System.out.print("Item Category: ");
    String category = "0";
    category=store.next();
	boolean categoryFound = false;
	/* I used this Code to check if the array is empty. 
       Then i checked availabilty of what i gonna delete*/
    if (itemcat.length > 0) {
    for (int i = 0; i < itemcat.length; i++){
       if(category.equals(itemcat[i][0])){
          categoryFound= true;
          
       }
    }
	} else {
    System.out.print("Can't Find Category. Try Again\n");
    deleteItemCategory() ; // Run Again
	}
	
	if (categoryFound== false){
		System.out.print("Can't Find Supplier id. Try Again\n");
		deleteItemCategory();
		}
	
	
    int j = 0;
   
    /* i used this codes to delete the data i wanna delete. i put every data from current array
     * to a tempory array except the one i want to delete. after that Update old array with the modified array */
    

    for (int i = 0; i < itemcat.length; i++) {
        if (category.equals(itemcat[i][0])) {
            categoryFound = true;
            continue; // Skip copying the row to delete it
        }
        temp[j++] = itemcat[i]; // Copy other rows to the temp array
    }

    if (categoryFound) {
        itemcat = temp; // Update itemcat with the modified array
        System.out.println("Delete successful!");

        System.out.print("Do you want to delete another category? (Y/N): ");
        char yn = store.next().charAt(0);

        if (yn == 'Y' || yn == 'y') {
            clearConsole();
            deleteItemCategory();
        } else {
            clearConsole();
            manageItemCategories();
        }
    } else {
        System.out.println("Category does not exist");
        deleteItemCategory();
    }
}

  
    
    public static void supplierbyId() {
    
    System.out.print("Enter Supplier Number: ");
    suppliernumber = "0";
    suppliernumber=store.nextLine();

    if (suppliernumber.isEmpty()) {
        System.out.println("Invalid input. Supplier Number cannot be empty.");
       // return; // Exit the method if the input is invalid
		supplierbyId();
    }

    int suppnum1 = 0;

    try {
        suppnum1 = Integer.parseInt(suppliernumber);
        suppnum1 = suppnum1 - 1;
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Supplier Number must be a valid integer.");
        supplierbyId();
    }

    supplierID = suppliers[suppnum1][0];
    itemExists = false;

    for (int i = 0; i < suppliers.length; i++) {
            
            if(supplierID.equals(suppliers[i][0])) {
                // Item found in the array
                itemExists = true;
                break; // Exit the loop since we found the item
            }
            else{
			
             //System.out.println(" \t\t"+ supplierID+ " Invalid supplier. Please choose from the available suppliers.");
             
            }
        }
}

    public static void categorybyId(){
    System.out.print("Enter Category Number: ");
    String itemCategoryNo = "";
    itemCategoryNo=store.nextLine();
    
    if (itemCategoryNo.isEmpty()) {
        System.out.println("Invalid input. Category Number cannot be empty.");
       // return; // Exit the method if the input is invalid
		categorybyId();
    }
    int catnum=0;
    try {
        catnum = Integer.parseInt(itemCategoryNo);
        catnum = catnum - 1;
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Category Number must be a valid integer.");
        categorybyId();
    }

    CategoryN = itemcat[catnum][0];
    itemExists = false;
        

    // Check if the entered item category exists
    for (int i = 0; i < itemcat.length; i++) {
            if(CategoryN.equals(itemcat[i][0])) {
                // Item found in the array
                itemExists = true;
                break; // Exit the loop since we found the item
            }
            else{
             //System.out.println(" \t\t"+ CategoryN+" Invalid Category. Please choose from the available Categories.");
             //break;
            }
        }
    }
    /**
     * 
     */
    public static void addItemcode(){
            
        
    }
    public static void addItem(){
	System.out.println("+---------------------------------------------------------------+");
    System.out.print("|\t\t\tADD ITEM\t\t\t\t|\n");
    System.out.println("+---------------------------------------------------------------+\n");
	    
    // Check if item categories and suppliers are available
    if (itemcat.length == 0 ) {
        System.out.println("OOPS! It seems that you dont have any item categories in the system.");
        System.out.print("Do you want to add a new item category(Y/N)? : ");
        char yn= store.next().charAt(0);
		
		if (yn=='Y'||yn=='y'){
			
			clearConsole();
			addNewItemCategory();
		}
		else if (yn=='N'||yn=='n'){
			clearConsole();
			stockManagement();
			
		}
        return;
    }
    else if (suppliers.length== 0 ) {
        System.out.println("OOPS! It seems that you dont have any suppliers  in the system.");
        System.out.print("Do you want to add a new Supplier (Y/N)? : ");
        char yn= store.next().charAt(0);
		
		if (yn=='Y'||yn=='y'){
			
			clearConsole();
			addSupplierHeader();
		}
		else if (yn=='N'||yn=='n'){
			clearConsole();
			stockManagement();
			
		}
        return;

    }
    else{
        
    }

        System.out.print("Item Code: "); 
		itemCode = store.nextLine();
        
        if (itemCode == ""){
			clearConsole();
			addItem();
			}
		else{
			
			for (int i = 0; i< items.length; i++){
			if (itemCode.equals(items[i][0])){
				System.out.println("Already exists. Try another Name.");
				clearConsole();
				addItem();
			}
		}
			
		}
        
        

    
    
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        System.out.println("|          #              |       SUPPLIER ID       |       SUPPLIER NAME     |");
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        int counter = 1;
        
        for (int i = 0; i < suppliers.length; i++){
            System.out.println("|\t"+counter+"|"+suppliers[i][0]+"\t\t|\t"+suppliers[i][1]+"\t\t|");
            counter++;
        }
        System.out.println("+-------------------------+-------------------------+-------------------------+");

        supplierbyId();
    
    
        System.out.println("+-------------------------+-------------------------+");
        System.out.println("|          #              |        CATEGORY         |");
        System.out.println("+-------------------------+-------------------------+");
        int counter2 = 1;
        
        for (int i = 0; i < itemcat.length; i++){
            System.out.println("|\t"+counter2+"|\t"+itemcat[i][0]+"\t\t|\t"+itemcat[i][1]+"\t\t|");
            counter2++;
        }
        System.out.println("+-------------------------+-------------------------+");

    
        categorybyId();
    
    
    
    // Store the item in the system
    System.out.print("Description: ");
    String iDescription = store.nextLine();
    System.out.print("Unit Price: ");
    String iUprice = store.nextLine();
    System.out.print("QTY on Hand: ");
    String iqty = store.nextLine();
    

    /*Add Items to array */
        items=additemArray();
        items[items.length-1][0]=itemCode;
        items[items.length-1][1]=supplierID;
        items[items.length-1][2]=CategoryN;
        items[items.length-1][3]=iDescription;
        items[items.length-1][4]=iUprice;
        items[items.length-1][5]=iqty;

    System.out.println("Item added successfully!");
    homePage();
   


    }

    public static void getItemSupplierWise(){
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("|\t\t\tVIEW ITEM SUPPLIER WISE\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------+\n");

        do{
            System.out.print("Enter Supplier Id :\n");
            String Sp = store.next();
            boolean supplierFound = false; // Flag to check if the supplier is found


            for (int i = 0; i < suppliers.length; i++) {
            if (Sp.equals(suppliers[i][0])) {
                supplierFound = true;
                System.out.println("Supplier name: " + suppliers[i][1] + "\n");

                System.out.println("+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
                System.out.println("|         ITEMCODE        |        SUPPLIER         |         CATEGORY        |       DESCRIPTION       |       UNIT PRICE        |        QTY ON HAND      |");
                System.out.println("+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");

                for (int k = 0; k < items.length; k++) {
                    // Check if the item's supplier matches the entered supplier ID
                    if (items[k][1].equals(Sp)) {
                        System.out.println("|           " + items[k][0] +  "            |       " + items[k][1] + "              |        " + items[k][2] + "             |      " + items[k][3] + "       |         " + items[k][4] + "             |                " + items[k][5] + "       |");
                    }
                }

                System.out.println("+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
                System.out.print("Search Succesfull. Do you want to Search a Another (Y/N)? : ");
                char yn= store.next().charAt(0);
		
		            if (yn=='Y'||yn=='y'){
			
			        clearConsole();
			        getItemSupplierWise();
		            }
		            else if (yn=='N'||yn=='n'){
			        clearConsole();
			        stockManagement();
			
		            }
                    return;
            }
        }
            if (!supplierFound) {
            System.out.println("Cannot find supplier ID. Please try again!\n");
            stockManagement();
            } else {
            stockManagement();
            }
       

        }while(true);

    }

    public static void viewItem(){
    System.out.println("+---------------------------------------------------------------+");
    System.out.println("|\t\t\tVIEW ITEMS BY CATEGORY\t\t\t|");
    System.out.println("+---------------------------------------------------------------+\n");

    // Find unique categories
    String[] uniqueCategories = findUniqueCategories();

    // Print items grouped by category
    for (String category : uniqueCategories) {
        
        System.out.println("|\t" + category + "\t|");
        System.out.println("+-------------------------+");

        System.out.println("+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
        System.out.println("|       SUPPLIER ID       |        ITEMCODE         |       DESCRIPTION       |       UNIT PRICE        |        QTY ON HAND      |");
        System.out.println("+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");

        for (String[] item : items) {
            if (item[2].equals(category)) {
                System.out.println("|        " + item[1] + "         |       " + item[0] + "          |      " + item[3] + "       |         " + item[4] + "             |        " + item[5] + "       |");
            }
        }

        System.out.println("+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
    }

    System.out.print("Do you want to go to the Stock Management page (Y/N): ");
    char viewOp = store.next().charAt(0);

    if (viewOp == 'Y' || viewOp == 'y') {
        clearConsole();
        stockManagement();
    }
    else{
		homePage();
	}
    }
    private static String[] findUniqueCategories() {
		String[] uniqueCategories = new String[items.length];
		int count = 0;

    // Find unique categories
		for (String[] item : items) {
			boolean categoryExists = false;
			for (int i = 0; i < count; i++) {
				if (item[2].equals(uniqueCategories[i])) {
					categoryExists = true;
					break;
				}
			}
			if (!categoryExists) {
            uniqueCategories[count] = item[2];
            count++;
			}
		}

    // Trim array to remove null values googled it
		String[] trimmedCategories = new String[count];
		System.arraycopy(uniqueCategories, 0, trimmedCategories, 0, count);

		return trimmedCategories;
	}

    public static void rankItemPerUnit(){
		System.out.println("+---------------------------------------------------------------+");
		System.out.println("|\t\t\tRANK ITEMS BY UNIT PRICE\t\t\t|");
		System.out.println("+---------------------------------------------------------------+\n");

    // Perform insertion sort on items array based on unit price
		for (int i = 1; i < items.length; i++) {
        String[] current = items[i];
        double unitPrice = Double.parseDouble(current[4]);
        int j = i - 1;
        while (j >= 0 && Double.parseDouble(items[j][4]) > unitPrice) {
            items[j + 1] = items[j];
            j--;
        }
        items[j + 1] = current;
    }

    System.out.println("+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
    System.out.println("|        ITEMCODE        |         SUPPLIER        |        CATEGORY         |       DESCRIPTION       |       UNIT PRICE        |");
    System.out.println("+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");

    for (String[] item : items) {
        System.out.println("|        " + item[0] + "         |       " + item[1] + "             |        " + item[2] + "             |      " + item[3] + "       |         " + item[4] + "             |");
    }

    System.out.println("+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");

    System.out.print("Do you want to go to the stock management page (Y/N): ");
    char viewOp = store.next().charAt(0);

    if (viewOp == 'Y' || viewOp == 'y') {
        clearConsole();
        stockManagement();
    }
    else {
        homePage();
    }
    }
    public static void homePage(){
		clearConsole();
        mainMenu();
    }
    public static void addNewItemCategory(){

        

        addingNewItemcat();

    }
	public static  String[][] growItem(){
		String[][]temp = new String[itemcat.length+1][2];
		for (int i = 0; i < itemcat.length; i++){
			for (int j = 0; j < itemcat[i].length; j++){
				temp[i][j]= itemcat[i][j];
			}
		}
		return temp;
		}
	
	public static void addingNewItemcat(){
		System.out.println("+---------------------------------------------------------------+");
        System.out.print("|\t\t\tADD NEW ITEM CATEGORY\t\t\t\t|\n");
        System.out.println("+---------------------------------------------------------------+\n");
		L1: do {
		System.out.print("Enter the new item category : ");
		String newItem= store.next();
		
		for (int i = 0; i< itemcat.length; i++){
			if (newItem.equals(itemcat[i][0])){
				System.out.println("Already exists. Try another Name.");
				break L1;
			}
		}
        itemcat =growItem();
		itemcat[itemcat.length-1][0]=newItem;
			
		System.out.print("Added successfully! Do you want to add another category(Y/N)? : ");
		char yn= store.next().charAt(0);
		
		if (yn=='Y'||yn=='y'){
			
			clearConsole();
			addingNewItemcat();
		}
		else if (yn=='N'||yn=='n'){
			clearConsole();
			stockManagement();
			
		}
		
		} while(true);
	}

    public static void main(String args[]){


        System.out.println("+---------------------------------------------------------------+");
        System.out.print("|\t\t\tLOGIN PAGE\t\t\t\t|\n");
        System.out.println("+---------------------------------------------------------------+\n");
/* 
        suppliers[0][0] = "S001";
        suppliers [0][1]= "Samitha";
        suppliers [1][0]=  "S002";
        suppliers [1][1]=  "Dasun";

        itemcat[0][0] = "Food";
        itemcat [1][0]=  "Gift";

        items[0][0]="i001";
        items[0][1]="S001";
        items[0][2]="Food";
        items[0][3]="iDescription";
        items[0][4]="100";
        items[0][5]="10";
        items[1][0]="I002";
        items[1][1]="S002";
        items[1][2]="Gift";
        items[1][3]="iDescription";
        items[1][4]="50";
        items[1][5]="20";*/ // used this data to save time while programming the stock manage part
        

        checkUserName();
        checkPassword();
        clearConsole();
        mainMenu();
        }
}
