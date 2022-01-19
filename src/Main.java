import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mayın Tarlası Oyunu Başlıyor...");
        int row, col;
        System.out.print("Satır Sayısı : ");
        row = scanner.nextInt();
        System.out.print("Kolon Sayısı : ");
        col = scanner.nextInt();
        MineSweeper mineSweeper = new MineSweeper(row, col);
        mineSweeper.run();
    }
}
