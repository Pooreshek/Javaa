import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ComplexMatrix matrixA = null;
        ComplexMatrix matrixB = null;
        
        boolean running = true;
        while (running == true) {
            System.out.println("1. Ввести матрицу А");
            System.out.println("2. Ввести матрицу В");
            System.out.println("3. Показать текущие матрицы");
            System.out.println("4. Сложение (A + B)");
            System.out.println("5. Умножение (A * B)");
            System.out.println("6. Деление (A / B)");
            System.out.println("7. Транспонировать матрицу A");
            System.out.println("8. Определитель матрицы A");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");
            
            int action = scanner.nextInt();

            if (action == 1) {
                System.out.print("Строк в А: ");
                int r = scanner.nextInt();
                System.out.print("Столбцов в А: ");
                int c = scanner.nextInt();
                matrixA = new ComplexMatrix(r, c);
                fillMatrix(scanner, matrixA, "A");
            } 
            else if (action == 2) {
                System.out.print("Строк в B: ");
                int r = scanner.nextInt();
                System.out.print("Столбцов в B: ");
                int c = scanner.nextInt();
                matrixB = new ComplexMatrix(r, c);
                fillMatrix(scanner, matrixB, "B");
            } 
            else if (action == 3) {
                System.out.println("\nМатрица A:");
                if (matrixA != null) matrixA.print(); else System.out.println("Пусто");
                System.out.println("\nМатрица B:");
                if (matrixB != null) matrixB.print(); else System.out.println("Пусто");
            } 
            else if (action == 4) {
                if (matrixA == null || matrixB == null) {
                    System.out.println("Введите обе матрицы");
                } else {
                    ComplexMatrix res = matrixA.add(matrixB);
                    if (res != null) res.print();
                }
            } 
            else if (action == 5) {
                if (matrixA == null || matrixB == null) {
                    System.out.println("Введите обе матрицы");
                } else {
                    ComplexMatrix res = matrixA.multiply(matrixB);
                    if (res != null) res.print();
                }
            } 
            else if (action == 6) {
                if (matrixA == null || matrixB == null) {
                    System.out.println("Введите обе матрицы");
                } else {
                    ComplexMatrix res = matrixA.divide(matrixB);
                    if (res != null) res.print();
                }
            } 
            else if (action == 7) {
                if (matrixA == null) {
                    System.out.println("Введите матрицу А");
                } else {
                    matrixA.transpose().print();
                }
            } 
            else if (action == 8) {
                if (matrixA == null) {
                    System.out.println("Введите матрицу А");
                } else {
                    ComplexNumber det = matrixA.determinant();
                    if (det != null) {
                        System.out.println("Определитель = " + det.asText());
                    }
                }
            } 
            else if (action == 0) {
                System.out.println("Выход из программы.");
                running = false;
            } 
            else {
                System.out.println("Неизвестная команда!");
            }
        }
        scanner.close();
    }
    public static void fillMatrix(Scanner sc, ComplexMatrix m, String name) {
        System.out.println("Вводите Реал, затем Мним для каждой ячейки:");
        for (int i = 0; i < m.rows; i = i + 1) {
            for (int j = 0; j < m.cols; j = j + 1) {
                System.out.print(name + "[" + i + "][" + j + "] Реал: ");
                double r = sc.nextDouble();
                System.out.print(name + "[" + i + "][" + j + "] Мним: ");
                double img = sc.nextDouble();
                m.data[i][j] = new ComplexNumber(r, img);
            }
        }
        System.out.println("Матрица сохранена.");
    }
}
