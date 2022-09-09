// Nombre: Lopez Ontiveros Javier Eduardo
// materia: topicos avanzados de programacion
// semestre: 5to
// maestro: Clemente Garcia Gerardo
//tema: creacion de un espiral de numeros
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class App extends JFrame implements ActionListener
{
    private int lado;
    private JButton btn1;
    private JTextField TF1;
    private JPanel f2;
    private int[][] matriz;
    public static void main(String[] args) throws Exception
    {
        App app = new App();
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public App()
    {
        super("ESPIRAL");
        Interfaz();
        Eventos();
    }
    private void Interfaz()
    {
        setSize(500, 1000);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 0));// frame principal, se guarda las eqiquetas y la matriz
        JPanel p1 = new JPanel();
        btn1 = new JButton("Crear");
        TF1 = new JTextField();
        p1.setLayout(new GridLayout(6, 0, 0, 10));// panel donde se guarda el btn dentro del frame
        p1.add(new JLabel("Ingresa el lado"));
        p1.add(TF1);
        p1.add(btn1);
        add(p1);
        setVisible(true);
    }
    private void Eventos()
    {
        btn1.addActionListener(this);
    }
    @Override public void actionPerformed(ActionEvent e)
    {
        if (f2 != null) borrar(); // si al entrar el panel tiene contenido lo elimina para poner una nueva matriz
        lado = Integer.parseInt(TF1.getText());// obtiene el valor del textfield
        matriz = new int[lado][lado];// [fila] [columna]
        f2 = new JPanel();// se crea un nuevo grid donde se dibuja la matriz
        f2.setLayout(new GridLayout(lado, lado));
        f2.setBackground(Color.WHITE);
        add(f2);
        dibuja();
        setVisible(true);
        pinta();
    }
    private void borrar()// borra la matriz vieja para dibujar una nueva
    {
        for (int i = 0; i < lado * lado; i++)
        {
            JButton Aux = (JButton) f2.getComponent(i);
            Aux.setBackground(Color.WHITE);
            Aux.setBorder(null);
            Aux.setText(null);
            Aux.update(Aux.getGraphics());
        }
        remove(f2); // esto es necesario para que solo haya una matriz al mismo tiempo
    }
    private void dibuja()
    {// coloca los N*N cuadros en la matriz cuadrada
        int indexY = 0, indexX = 0, numero = 1;
        for (int i = 0; i < Math.pow(lado, 2); i++)// rellena la matriz
        {
            matriz[indexY][indexX] = i; // valor de orden
            if (indexX < lado - numero && indexY == numero - 1)// rellena la primer fila desde (0,0) hasta (0,N)
                indexX++;
            else if (indexY < lado - numero && indexX == lado - numero)// rellena la ultima columna desde (0,N) hasta (N,N)
                indexY++;
            else if (indexX > -1 + numero && indexY == lado - numero)// rellena la ultima fila desde (N,N) hasta (N,0)
                indexX--;
            else if (indexY >= 0 && indexX == numero - 1)// rellena la ultima columna desde (N,0) hasta (0,0)
            {
                if (indexY == numero && indexX == numero - 1)// en caso de ser el ultimo numero de cada espiral se mueve a la otra espiral
                {
                    numero++;
                    indexX++;
                } else indexY--;
            }
        }
        for (int i = 0; i < matriz.length; i++)// filas
            for (int j = 0; j < matriz.length; j++)// columnas
                f2.add(new JButton(matriz[i][j] + ""));// añade los botones al Grid
    }
    private void pinta()
    {
        int indexY = 0, indexX = 0, numero = 1, auxNum = 0;
        int num1 = (int) (Math.random() * 255);
        int num2 = (int) (Math.random() * 255);
        int num3 = (int) (Math.random() * 255);
        Color c = new Color(num1, num2, num3);
        for (int i = 0; i < Math.pow(lado, 2); i++)
        {
            JButton Aux = (JButton) f2.getComponent(auxNum);// obtiene el boton con ayuda de un indice auxiliar de la matriz(Grid)
            Aux.setBackground(c);
            Aux.setBorder(null);
            Aux.setEnabled(false);
            Aux.update(Aux.getGraphics());
            if (indexX < lado - numero && indexY == numero - 1)// rellena la primer fila desde (0,0) hasta (0,N)
            {
                auxNum++;
                indexX++;
            } else if (indexY < lado - numero && indexX == lado - numero)// rellena la ultima columna desde (0,N) hasta (N,N)
            {
                auxNum += lado;
                indexY++;
            } else if (indexX > -1 + numero && indexY == lado - numero)// rellena la ultima fila desde (N,N) hasta (N,0)
            {
                indexX--;
                auxNum--;
            } else if (indexY >= 0 && indexX == numero - 1)// rellena la ultima columna desde (N,0) hasta (0,0)
            {
                if (indexY == numero && indexX == numero - 1)// en caso de ser el ultimo numero de cada espiral se mueve a la otra espiral
                {
                    numero++;
                    indexX++;
                    auxNum++;
                    num1 = (int) (Math.random() * 255);
                    num2 = (int) (Math.random() * 255);
                    num3 = (int) (Math.random() * 255);
                    c = new Color(num1, num2, num3);
                } else
                {
                    indexY--;
                    auxNum -= lado;
                }
            }
            try
            {
                Thread.sleep(300 / lado);
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
        }
        add(f2);
    }
}
