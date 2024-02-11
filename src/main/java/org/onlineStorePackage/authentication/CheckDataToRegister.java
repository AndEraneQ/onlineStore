package org.onlineStorePackage.authentication;

public class CheckDataToRegister {
    public boolean stringLengthError(String stringToCheck,int length){
        if(stringToCheck.length()<length){
            System.out.println("It need to have minimum " + length + " letters. Try again");
            return true;
        }
        return false;
    }
    public boolean stringContinesSymbol(String string,Character symbol){
        if(!string.contains(String.valueOf(symbol))){
            System.out.println("It need to contain '" + symbol + "' symbol. Try again.");
            return false;
        }
        return true;
    }
}
