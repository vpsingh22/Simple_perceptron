package vp;
import static vp.Perceptron.height;
import static vp.Perceptron.width;

/**
 *
 * @author vpsingh
 */
public class Data {
    static int[] x, y, sign;
    static int size;
    static double[] bias;
    Data(int n) {
        size = n;
        x = new int[n];
        y = new int[n];
        sign = new int[n];
        bias = new double[n];
        for(int i = 0; i < n; i++) {
            x[i] = (int) (Math.random() * width);
            y[i] = (int) (Math.random() * height);
            bias[i] = 1;
        }
    }

    static void func(double slope, double y_intercept) {
        for(int i = 0; i < size; i++) {
            if(y[i] < slope * x[i] + y_intercept)
                sign[i] = -1;
            else sign[i] = 1;
        }
    }

}
