package vp;

/**
 *
 * @author vpsingh
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class Perceptron extends JPanel{
    
    static int N = 5000;
    Data data = new Data(N);
    static int height = 800;
    static int width = 800;
    static double slope = 0.5;
    static int y_intercept = 60;
    double weights[] = new double[3];
    double rate = 0.00004;
    static int iter = 0;
    Perceptron() {
        for(int i = 0; i < 3; i++) {
            weights[i] = 1;
        }
    }
    
    public int activation(double value) {
        return value >= 0 ? 1 : -1;
    }
    
    
    public int hypothesis(int i) {
        double sum = weights[0] * data.x[i] + weights[1] * data.y[i] + weights[2] * data.bias[i];
        return activation(sum);
    }
    
    public void train(int index, int sign) {
        int v = hypothesis(index);
        double error = sign - v;
        weights[0] += error * (double)data.x[index] * rate;
        weights[1] += error * (double)data.y[index] * rate;
        weights[2] += error * (double)data.bias[index];
    }
    
    public static void main(String args[]) {
        JFrame frame = new JFrame("Perceptron");
        frame.setSize(height, width);
        Perceptron p = new Perceptron();
        Perceptron p2 = new Perceptron();
        frame.add(p);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Data.func(slope, y_intercept);
    }
    
    public void paint(Graphics g) {
        g.clearRect(0, 0, height, width);
        g.setColor(Color.BLACK);
        g.drawLine(0, (int)f(0, slope, y_intercept), width, (int)f(width, slope, y_intercept));
        
        for(int j = 0; j < iter; j++) {
            if(data.sign[j] > 0) g.setColor(Color.RED);
            else g.setColor(Color.GREEN);
            g.fillRect(data.x[j] - 1, data.y[j] - 1, 3, 3);
        }
        
        g.setColor(Color.BLUE);
        int i = iter;
        train(i, Data.sign[i]);
        double m = -weights[0] / weights[1];
        double c = -weights[2] / weights[1];
        System.out.println(m + " " + c);
        g.drawLine(0, (int)f(0, m, c), width, (int)f(width, m, c));
        ++iter;
        if(iter < N)
            repaint();
    }
    
    static double f(double x, double slope, double intercept) {
        return slope * x + intercept;
    }
}
