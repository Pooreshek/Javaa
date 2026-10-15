public class ComplexNumber {
    public double real; 
    public double imag; 

    public ComplexNumber(double r, double i) {
        this.real = r;
        this.imag = i;
    }

    public ComplexNumber add(ComplexNumber other) {
        double newReal = this.real + other.real;
        double newImag = this.imag + other.imag;
        return new ComplexNumber(newReal, newImag);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        double newReal = this.real - other.real;
        double newImag = this.imag - other.imag;
        return new ComplexNumber(newReal, newImag);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        double newReal = this.real * other.real - this.imag * other.imag;
        double newImag = this.real * other.imag + this.imag * other.real;
        return new ComplexNumber(newReal, newImag);
    }

    public ComplexNumber divide(ComplexNumber other) {
        double znam = other.real * other.real + other.imag * other.imag;
        if (znam == 0) {
            System.out.println("Ошибка! Деление на ноль");
            return new ComplexNumber(0, 0);
        }
        double newReal = (this.real * other.real + this.imag * other.imag) / znam;
        double newImag = (this.imag * other.real - this.real * other.imag) / znam;
        return new ComplexNumber(newReal, newImag);
    }

    public String asText() {
        if (this.imag >= 0) {
            return this.real + " + " + this.imag + "i";
        } else {
            return this.real + "" + this.imag + "i";
        }
    }
}
