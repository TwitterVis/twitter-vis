/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author Ross Doherty
 */
public class MainTest {
   public static void main(String[] args) {
          PieChart demo = new PieChart("Comparison", "Example Title");
          demo.pack();
          demo.setVisible(true);
          //
          HistogramTest demo2 = new  HistogramTest("Comparison", "Example Title");
          demo2.pack();
          demo2.setVisible(true);
      }
} 