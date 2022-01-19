import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    int row;
    int col;

    MineSweeper(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void toString(String[][] matris){
        for(int i = 0; i  < matris.length; i++){
            for(int j = 0; j < matris[i].length; j++){
                System.out.print(matris[i][j]);
            }
            System.out.println();
        }
    }
    public String[][] toString2(String[][] matris, int[] mayin, String view){
        for(int i = 0; i  < matris.length; i++){
            for(int j = 0; j < matris[i].length; j++){
                boolean state = false;
                for(int z = 0; z < mayin.length; z++){
                    if(((row-1)*i + j) == mayin[z]){
                        state = true;
                    }
                }
                if(state){
                    matris[i][j] = " * ";
                }else{
                    matris[i][j] = " _ ";
                }
            }
        }
        return matris;
    }
    public String[][] addElement(String[][] matris, int a, int b, int sum){
        for(int i = 0; i  < matris.length; i++){
            for(int j = 0; j < matris[i].length; j++){
                if(i == a && j == b){
                    matris[a][b] = " " + sum + " ";
                }else{
                    matris[i][j] = " _ ";
                }
            }
        }
        return matris;
    }
    public int sumArroundMine(String[][] matris, int[] mayin, int a, int b){
        int sum = 0;
        for(int i = a-1; i <= a+1; i++){
            for(int j = b-1; j <= b+1; j++){
                if(i < 0 || j < 0 || i >= matris.length
                        || j >= matris[i].length
                        || (b+1) == matris[i].length){
                    continue;
                }
                for(int z = 0; z < mayin.length; z++){
                    if((this.row-1)*i+j == mayin[z]){
                        sum++;
                    }
                }
            }
        }

        System.out.println(sum + "   ---");
       return sum;
    }

    public int[] mayinUret(int mayinSayisi){
        int[] mayin = new int[mayinSayisi];
        for(int i = 0; i < mayinSayisi; i++){
            Random random = new Random();
            int temp = random.nextInt((mayinSayisi*4)-1);
            boolean state = true;
            for(int j = 0; j < mayin.length; j++){
                if(mayin[j] == temp){
                    state = false;
                    i -= 1;
                    continue;
                }
            }
            if(state){
                mayin[i] = temp;
            }
        }
        return mayin;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        String[][] matris = new String[this.row][this.col];
        int mayinSayisi = this.row*this.col/4;
        int[] mayin = mayinUret(mayinSayisi);
        System.out.println("**************");
        for(int i = 0; i < mayin.length; i++){
            System.out.println(mayin[i]);
        }
        System.out.println("********************");
        toString(toString2(matris, mayin, " * "));
        System.out.println("*********************");
        int count = 0;
        boolean isWin = false;
        toString2(matris, mayin, " _ ");
        System.out.println("###########################");
        for(int i = 0; i < row; i++){
            if (isWin){
                break;
            }
            for(int j = 0; j < col; j++){
                System.out.print("Satır : ");
                int chosenRow = scanner.nextInt();
                System.out.print("Sütun : ");
                int chosenCol = scanner.nextInt();
                System.out.println("=======================");
                if(chosenRow >= row || chosenRow < 0 ||
                        chosenCol >= col || chosenCol < 0){
                    System.out.println("Geçersiz Koordinat");
                    j -= 1;
                    continue;
                }
                for(int z = 0; z < mayinSayisi; z++){
                    if(mayin[z] == (chosenRow*col) + chosenCol){
                        System.out.println("Mayın Patladı. \nGame Over!");
                        return;
                    }
                }
                int sum = sumArroundMine(matris, mayin, i, j);
                toString(addElement(matris, chosenRow, chosenCol, sum));
                count++;
                if(count == row*col-mayinSayisi){
                    isWin = true;
                    break;
                }
            }
        }
        if(isWin){
            System.out.println("Tebrikler Kazandınız.");
        }

        System.out.println("=========================");
    }
}
