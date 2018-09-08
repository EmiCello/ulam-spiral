/*
    Napisz taki program, że podajemy mu liczbę, a on wyświetla spiralę Ulama liczb idącą od 1 do podanej liczby.
    W wersji lekko zaawansowanej: niech program pyta jeszcze, czy spirala ma być lewoskrętna czy prawoskrętna.
    W wersji zaawansowanej: przerób ten program tak, żeby wyświetlał tę spiralę tak, że jeśli dana liczba jest pierwsza, 
    to żeby zamiast niej była wyświetlana gwiazdka, a jeśli dana liczba nie jest pierwsza, to żeby zamiast niej była 
    wyświetlana spacja.
 */
package ulamspiral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class UlamSpiral {
    
    public static void main(String[] args) {
        int number = Integer.parseInt(JOptionPane.showInputDialog("Podaj liczbę całkowitą"));
        String left_right = JOptionPane.showInputDialog("Podaj kierunek spirali: prawy - P, lewy - L.");
        tableUlamSpiral(number, left_right);            
    }
    
private static void tableUlamSpiral(int number, String left_right){
    int row = rowAmount(number);   
    if(left_right.equalsIgnoreCase("p")){
        int rowAmount = rowAmount(number);
        if(row % 2 != 0){            
           int i = start(rowAmount);
           int j = i; 
           tableRight(rowAmount,number,i,j);
        }else{
           int i = start(rowAmount) - 1;
           int j = i;
           tableRight(rowAmount,number, i, j);
        }        
    } 
    else if(left_right.equals("l")){
        if(row % 2 != 0){
            tableUnpairedNumberLeft(number);         
        }else{
            tablePairedNumberLeft(number);
        }
    }else{
        System.out.println("Podałeś błędne dane.");
    }    
}

private static void tableRight(int rowAmount,int number, int i, int j){   
    int x = 1;   
    int up = i;
    int down = i;      
    boolean directionRight = true;
    boolean directionUp = false;
    boolean directionDown = false;
    boolean directionLeft = false;
    
    String[][] tab = new String[rowAmount][rowAmount];    
    
    while(x <= number){
        if(directionUp && i == j){
            tab = fillIt(x, i, j, tab, number);
            directionUp = false;
            directionRight = true;
            j++;
            x++;
        }
        if(directionUp){
            tab = fillIt(x, i, j, tab, number);
            i--;
            x++;
        }
        if(directionLeft && j < up){
            tab = fillIt(x, i, j, tab, number);
            directionLeft = false;
            directionUp = true;
            up--;
            i--; 
            x++;
        }
        if(directionLeft){
            tab = fillIt(x, i, j, tab, number);
            j--;
            x++;
        }
        if(directionDown && i > j){
            directionDown = false;
            directionLeft = true;
            j--;
            i--;
        }
        if(directionDown && i <= j){
            tab = fillIt(x, i, j, tab, number);
            i++;
            x++;
        }
        if(directionRight && j > down){            
            tab = fillIt(x, i, j, tab, number);  
            directionRight = false;
            directionDown = true;
            down++;            
            i++; 
            x++;
        }
        if(directionRight && j <= down){
            tab = fillIt(x, i, j, tab, number);      
            j++;
            x++;
        }               
    }
    spirala(spiralaUlamy(tablePrimeNumber(tab,number), number), number); 
}

private static void tablePairedNumberLeft(int number){
    int rowAmount = rowAmount(number); 
    int i = start(rowAmount);
    int j = i - 1;
    int x = 1;
    int up = i;
    int down = i;       
    boolean directionRight = true;
    boolean directionUp = false;
    boolean directionDown = false;
    boolean directionLeft = false;    
    
    String[][] tab = new String[rowAmount][rowAmount];   
    
    while(x <= number){
        if(directionDown && i > down ){
            tab = fillIt(x, i, j, tab, number);
            directionDown = false;
            directionRight = true;
            down++;
            j++;
            x++;
        }
        if(directionDown && i <= down){
            tab = fillIt(x, i, j, tab, number);
            i++;
            x++;
        }
        if(directionLeft && j < i){
            tab = fillIt(x, i, j, tab, number);
            directionLeft = false;
            directionDown = true;
            i++;
            x++;
        }
        if(directionLeft && j >= i){
            tab = fillIt(x, i, j, tab, number);
            j--;
            x++;
        }
        if(directionUp && i < up){
            directionUp = false;
            directionLeft = true;
            j--;
            up--;
        }
        if(directionUp){
            i--;
            tab = fillIt(x, i, j, tab, number);
            x++;        
        }
        if(directionRight && i < j){
            j--;
            directionRight = false;
            directionUp = true;            
        }
        if(directionRight && i >= j){
            tab = fillIt(x, i, j, tab, number);
            j++;
            x++;
        }
    } 
    spirala(spiralaUlamy(tablePrimeNumber(tab,number), number), number);
}

private static void tableUnpairedNumberLeft(int number){
    int rowAmount = rowAmount(number); 
    int i = start(rowAmount);
    int j = start(rowAmount);
    int x = 1;
    int up = i;
    int down = j;      
    boolean directionRight = true;
    boolean directionUp = false;
    boolean directionDown = false;
    boolean directionLeft = false;
    
    String[][] tab = new String[rowAmount][rowAmount];   
    
    while(x <= number){
            if(directionDown && i > down){
                directionDown = false;
                directionRight = true;
                down++;                
            }
            if(directionDown && i == down){
                tab = fillIt(x, i, j, tab, number);  
                i++;
                x++;               
            }
            if(directionDown && i < down){
                tab = fillIt(x, i, j, tab, number);                      
                x++;
                i++;                
            }
            if(directionLeft && i == j){ 
                tab = fillIt(x, i, j, tab, number);
                directionLeft = false;
                directionDown = true;                
                x++;
                i++;                 
            }
            if(directionLeft){     
                tab = fillIt(x, i, j, tab, number);
                j--; 
                x++;               
            }            
            if(directionUp && i < up){
                tab = fillIt(x, i, j, tab, number);
                up--;
                j--;
                directionUp = false;
                directionLeft = true;                
                x++;                
            }            
            if(directionUp){
                tab = fillIt(x, i, j, tab, number);
                i--;               
                x++;               
            }            
            if(directionRight && j > i + 1){
                directionRight = false;
                directionUp = true;                
                i--;
                j--;
            }
            if(directionRight){ 
                tab = fillIt(x, i, j, tab, number);   
                j++;
                x++;               
            }       
    }
    spirala(spiralaUlamy(tablePrimeNumber(tab,number), number), number); 
}

private static String[][] tablePrimeNumber(String[][] tab, int number){
    boolean primeNumber = primeNumber(number);
    String check = String.valueOf(number);
    if(primeNumber){        
        for (int i = 0; i < tab.length; i++) {            
            for (int j = 0; j < tab.length; j++) {                                 
                if(String.valueOf(tab[i][j]).equals(check)){
                    tab[i][j] = sign1PrimeNumber(number);
                }
            }
        }
    }
    else{
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {                
                if(String.valueOf(tab[i][j]).equals(check)){
                    tab[i][j] = sign2PrimeNumber(number);
                }
            }
        }
    }
    return tab;
}

private static String sign1PrimeNumber(int number){
    int numberLenght = String.valueOf(number).length();
    String signPrimeNumber = "";
    for (int i = 0; i < numberLenght; i++) {
        signPrimeNumber += "*";
    }
    return signPrimeNumber;
}

private static String sign2PrimeNumber(int number){
    int numberLenght = String.valueOf(number).length();
    String signPrimeNumber = "";
    for (int i = 0; i < numberLenght; i++) {
        signPrimeNumber += "-";
    }
    return signPrimeNumber;
}

private static String blankSign(int number){
    int numberLenght = String.valueOf(number).length();
    String signPrimeNumber = "";
    for (int i = 0; i < numberLenght; i++) {
        signPrimeNumber += "/";
    }
    return signPrimeNumber;
}

private static String[][] spiralaUlamy(String[][] tab, int number){
    for (int a = 0; a < tab.length; a++) {
        for (int b = 0; b < tab.length; b++) { 
            String check = tab[a][b];
            if(check == null)
                tab[a][b] = blankSign(number);
            }
    }
    return tab;
}

private static String[][] fillIt(int x, int i, int j, String[][] tab, int number){
    String newX = newX(number,x);
    tab[i][j] = newX;
    return tab;
}

private static String newX(int number, int x){
        int difference = String.valueOf(number).length() - String.valueOf(x).length();
        String sign = " ";
        String newX = "";
        if(difference > 0){
            for (int i = 0; i < difference; i++) {
                newX += sign;
            }
        }
        return newX + x;
    }

private static void spirala(String[][] spiralaUlama, int number){
    String[][] tab = spiralaUlamy(spiralaUlama, number) ;
    for (int k = 0; k < tab.length; k++) {
            System.out.println(Arrays.toString(tab[k]));
        }
}

private static int rowAmount(int number){
    int x = 1;
    while(x * x < number){
        x++;
    }
    return x;
}

private static int start(int rowAmount){
        int start;
        return start = rowAmount / 2;
}

public static boolean primeNumber(int number){
        if(number < 2){
            return false;       
        }
        for (int i = 2; i*i <= number; i++) {
                if(number % i == 0)
                {
                    return false;
                }               
            }            
        return true;               
    }
}
