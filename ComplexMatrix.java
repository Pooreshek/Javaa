public class ComplexMatrix {
    public int rows;
    public int cols;
    public ComplexNumber[][] data;

    public ComplexMatrix(int r, int c) {
        this.rows = r;
        this.cols = c;
        this.data = new ComplexNumber[r][c];
        
        for (int i = 0; i < r; i = i + 1) {
            for (int j = 0; j < c; j = j + 1) {
                this.data[i][j] = new ComplexNumber(0, 0);
            }
        }
    }

    public ComplexMatrix add(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            System.out.println("Ошибка! Размеры матриц не совпадают");
            return null;
        }
        ComplexMatrix result = new ComplexMatrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i = i + 1) {
            for (int j = 0; j < this.cols; j = j + 1) {
                result.data[i][j] = this.data[i][j].add(other.data[i][j]);
            }
        }
        return result;
    }

    public ComplexMatrix multiply(ComplexMatrix other) {
        if (this.cols != other.rows) {
            System.out.println("Ошибка! Матрицы нельзя перемножить");
            return null;
        }
        ComplexMatrix result = new ComplexMatrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i = i + 1) {
            for (int j = 0; j < other.cols; j = j + 1) {
                ComplexNumber sum = new ComplexNumber(0, 0);
                for (int k = 0; k < this.cols; k = k + 1) {
                    ComplexNumber mult = this.data[i][k].multiply(other.data[k][j]);
                    sum = sum.add(mult);
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }

    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(this.cols, this.rows);
        for (int i = 0; i < this.rows; i = i + 1) {
            for (int j = 0; j < this.cols; j = j + 1) {
                result.data[j][i] = this.data[i][j];
            }
        }
        return result;
    }

    public ComplexNumber determinant() {
        if (this.rows != this.cols) {
            System.out.println("Ошибка! Матрица должна быть кваратной");
            return null;
        }
        return calcDet(this.data);
    }

    private ComplexNumber calcDet(ComplexNumber[][] m) {
        int n = m.length;
        
        if (n == 1) {
            return m[0][0];
        }
        if (n == 2) {
            ComplexNumber ad = m[0][0].multiply(m[1][1]);
            ComplexNumber bc = m[0][1].multiply(m[1][0]);
            return ad.subtract(bc);
        }

        ComplexNumber det = new ComplexNumber(0, 0);
        for (int j = 0; j < n; j = j + 1) {
            ComplexNumber[][] sub = getSubMatrix(m, 0, j);
            ComplexNumber slag = m[0][j].multiply(calcDet(sub));
            
            if (j % 2 == 1) {
                det = det.subtract(slag);
            } else {
                det = det.add(slag);
            }
        }
        return det;
    }

    private ComplexNumber[][] getSubMatrix(ComplexNumber[][] m, int skipRow, int skipCol) {
        int n = m.length;
        ComplexNumber[][] sub = new ComplexNumber[n - 1][n - 1];
        int r = 0;
        for (int i = 0; i < n; i = i + 1) {
            if (i == skipRow) {
                continue;
            }
            int c = 0;
            for (int j = 0; j < n; j = j + 1) {
                if (j == skipCol) {
                    continue;
                }
                sub[r][c] = m[i][j];
                c = c + 1;
            }
            r = r + 1;
        }
        return sub;
    }

    public ComplexMatrix divide(ComplexMatrix other) {
        if (other.rows != other.cols) {
            System.out.println("Ошибка! Делитель (матрица B) должен быть квадратным");
            return null;
        }
        
        ComplexNumber detB = other.determinant();
        if (detB.real == 0 && detB.imag == 0) {
            System.out.println("Ошибка! Определитель матрицы B равен 0, на ноль делить нельзя");
            return null;
        }

        int n = other.rows;
        ComplexMatrix adj = new ComplexMatrix(n, n);
        
        for (int i = 0; i < n; i = i + 1) {
            for (int j = 0; j < n; j = j + 1) {
                ComplexNumber[][] sub = getSubMatrix(other.data, i, j);
                ComplexNumber cofactor = calcDet(sub);
                
                if ((i + j) % 2 == 1) {
                    cofactor = new ComplexNumber(0, 0).subtract(cofactor);
                }
                adj.data[j][i] = cofactor;
            }
        }

        ComplexMatrix inverseB = new ComplexMatrix(n, n);
        for (int i = 0; i < n; i = i + 1) {
            for (int j = 0; j < n; j = j + 1) {
                inverseB.data[i][j] = adj.data[i][j].divide(detB);
            }
        }

        return this.multiply(inverseB);
    }

    public void print() {
        for (int i = 0; i < this.rows; i = i + 1) {
            for (int j = 0; j < this.cols; j = j + 1) {
                System.out.print("[" + this.data[i][j].asText() + "] ");
            }
            System.out.println();
        }
    }
}
