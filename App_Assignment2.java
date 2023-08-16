import java.util.Scanner;

public class App_Assignment2{
    private static final Scanner SCANNER=new Scanner(System.in);
    public static void main(String[] args) {
        final String CLEAR="\033[H\033[2J";
        final String COLOR_BLUE_BOLD="\033[34;1m";
        final String COLOR_GREEN_BOLD="\033[32;1m";
        final String COLOR_RED_BOLD="\033[31;1m";
        final String RESET="\033[0m";
        final String DASHBOARD="💰 Welcome to Smart Banking App";
        final String OPEN_ACCOUNT="📂 Open New Account";
        final String DEPOSIT_MONEY="⏬ Deposit Money";
        final String WITHDRAW_MONEY="⏫ Deposit Money";
        final String TRANSFER_MONEY="🔁 Transfer Money";
        final String ACCOUNT_BALANCE="💲 Check Account Balance";
        final String DELETE_ACCOUNT="❌ Drop Existing Account";
        String screen=DASHBOARD;
        final String LINE="-".repeat(40);
        final String ERROR_MSG=String.format("%s%s%s",COLOR_RED_BOLD,"%s",RESET);
        final String SUCCESS_MSG=String.format("%s%s%s",COLOR_GREEN_BOLD,"%s",RESET);
        int[] accountId=new int[0];
        String[] accountName=new String[0];
        double[] accountBalance=new double[0];


        do{
            final String APP_TITLE=String.format("%s%s%s",COLOR_BLUE_BOLD,screen,RESET);

            System.out.println(CLEAR);
            System.out.println(LINE);
            System.out.println(" ".repeat((40-APP_TITLE.length()+11)/2).concat(APP_TITLE));
            System.out.println(LINE);

            switch(screen){
                case DASHBOARD:
                System.out.println("\n[1]. Open New Account");
                System.out.println("[2]. Deposit Money");
                System.out.println("[3]. Withdraw Money");
                System.out.println("[4]. Transfer Money");
                System.out.println("[5]. Check Account Balance");
                System.out.println("[6]. Drop Existing Account");
                System.out.println("[7]. Exit \n");
                System.out.print("Enter an option to continue: ");
                int option=SCANNER.nextInt();
                SCANNER.nextLine();
                switch(option){
                    case 1: screen=OPEN_ACCOUNT;break;
                    case 2: screen=DEPOSIT_MONEY;break;
                    case 3: screen=WITHDRAW_MONEY;break;
                    case 4: screen=TRANSFER_MONEY;break;
                    case 5: screen=ACCOUNT_BALANCE;break;
                    case 6: screen=DELETE_ACCOUNT;break;
                    case 7: System.exit(0);
                    default: continue;
                }
                break;
                
                case OPEN_ACCOUNT:
                    String id;
                    String name;
                    int deposit;
                    boolean valid;
                    System.out.printf("ID : SDB-%05d \n",accountId.length+1);

                    //Name Validation
                    do{
                    valid=true;
                    System.out.print("Name : ");
                    name=SCANNER.nextLine().strip();

                    if(name.isBlank()){
                        System.out.printf(ERROR_MSG+"\n","Name can't be empty");
                        valid=false;
                        continue;
                    }

                    for(int i=0;i<name.length();i++){
                        if(!(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i)))){
                            System.out.printf(ERROR_MSG+"\n","Invalid name");
                            valid=false;
                            break;
                        }
                    }
                }while(!valid);

                //Initial Deposit Validation
                do{
                    valid=true;
                    System.out.print("Initial Deposit : ");
                    deposit=SCANNER.nextInt();
                    SCANNER.nextLine();
                    if(deposit<5000){
                        System.out.printf(ERROR_MSG+"\n","Insuficient amount");
                        System.out.printf(ERROR_MSG+"\n","Please deposit at least Rs.5,000.00");
                        valid=false;
                        continue;
                    }

                }while(!valid);

                int[] newAccountId=new int[accountId.length+1];
                String[] newAccountName=new String[accountId.length+1];
                double[] newAccountBalance=new double[accountId.length+1];


                    for(int i=0;i<newAccountId.length-1;i++){
                        newAccountId[i]=accountId[i];
                        newAccountName[i]=accountName[i];
                        newAccountBalance[i]=accountBalance[i];
                    }
                    newAccountId[newAccountId.length-1]=newAccountId.length+1;
                    newAccountName[newAccountName.length-1]=name;
                    newAccountBalance[newAccountBalance.length-1]=deposit;

                    accountId=newAccountId;
                    accountName=newAccountName;
                    accountBalance=newAccountBalance;

                    System.out.printf(SUCCESS_MSG,String.format("Account SDB-%05d for %s has been created sucessfully.\nDo you want to add new student (Y/n)? ",accountId.length,name));
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;

                break;

                default:
                  System.exit(0);
            }

           
        }while(true);
        
    }
}