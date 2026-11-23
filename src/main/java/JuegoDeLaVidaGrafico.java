import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JuegoDeLaVidaGrafico extends JPanel {
    private static final Color FONDO = new Color(243, 247, 244);
    private static final Color VIVA = new Color(28, 112, 86);
    private final JComboBox<Integer> tamanos = new JComboBox<>(new Integer[]{12, 24, 36, 48});
    private final JComboBox<String> reglas = new JComboBox<>(new String[]{"Conway (8 vecinos)", "Original (4 vecinos)"});
    private final JLabel estado = new JLabel();
    private final JButton iniciar = new JButton("Iniciar");
    private final JButton paso = new JButton("Paso");
    private final Tablero tablero = new Tablero();
    private final Timer reloj = new Timer(250, e -> avanzar());
    private int dimension = 24;
    private Mundo mundo;
    private long generacion;

    public JuegoDeLaVidaGrafico() {
        super(new BorderLayout(8, 8));
        setBorder(new EmptyBorder(14, 18, 14, 18));
        setBackground(FONDO);
        JPanel cabecera = new JPanel(new GridLayout(0, 1, 0, 3));
        cabecera.setOpaque(false);
        JLabel titulo = new JLabel("Juego de la vida");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 26f));
        cabecera.add(titulo);
        cabecera.add(new JLabel("Haz clic para activar o apagar una célula. Arrastra para dibujar."));
        JPanel ajustes = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 3));
        ajustes.setOpaque(false);
        tamanos.setSelectedItem(dimension);
        ajustes.add(new JLabel("Lado"));
        ajustes.add(tamanos);
        ajustes.add(new JLabel("Reglas"));
        ajustes.add(reglas);
        cabecera.add(ajustes);
        add(cabecera, BorderLayout.NORTH);
        add(tablero, BorderLayout.CENTER);

        JPanel pie = new JPanel(new BorderLayout());
        pie.setOpaque(false);
        pie.add(estado, BorderLayout.NORTH);
        JPanel controles = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        controles.setOpaque(false);
        JButton limpiar = new JButton("Limpiar");
        JSpinner velocidad = new JSpinner(new SpinnerNumberModel(4, 1, 20, 1));
        controles.add(iniciar);
        controles.add(paso);
        controles.add(limpiar);
        controles.add(new JLabel("Generaciones/s"));
        controles.add(velocidad);
        pie.add(controles, BorderLayout.SOUTH);
        add(pie, BorderLayout.SOUTH);

        iniciar.addActionListener(e -> {
            if (reloj.isRunning()) pausar();
            else {
                reloj.start();
                iniciar.setText("Pausar");
                paso.setEnabled(false);
            }
        });
        paso.addActionListener(e -> avanzar());
        velocidad.addChangeListener(e -> reloj.setDelay(1000 / (Integer) velocidad.getValue()));
        limpiar.addActionListener(e -> nuevoMundo());
        tamanos.addActionListener(e -> nuevoMundo());
        reglas.addActionListener(e -> {
            pausar();
            mundo.setVecinos(reglas.getSelectedIndex() == 0);
            actualizar();
        });
        nuevoMundo();
        // Un parpadeador permite observar la evolución desde el primer paso.
        for (int c = 11; c <= 13; c++) mundo.setEstadoCelula(12, c, true);
        actualizar();
    }

    private void pausar() {
        reloj.stop();
        iniciar.setText("Iniciar");
        paso.setEnabled(true);
    }

    private void nuevoMundo() {
        pausar();
        dimension = (Integer) tamanos.getSelectedItem();
        mundo = new Mundo(dimension);
        mundo.setVecinos(reglas.getSelectedIndex() == 0);
        generacion = 0;
        actualizar();
    }

    private void avanzar() {
        mundo.siguienteEstado();
        generacion++;
        actualizar();
    }

    private void actualizar() {
        int vivas = 0;
        for (int f = 0; f < dimension; f++)
            for (int c = 0; c < dimension; c++)
                if (mundo.estaViva(f, c)) vivas++;
        estado.setText("Generación " + generacion + "     ·     Células vivas " + vivas);
        tablero.repaint();
    }

    private class Tablero extends JPanel {
        private boolean pintarViva;
        private boolean dibujando;

        Tablero() {
            setPreferredSize(new Dimension(480, 480));
            setBackground(FONDO);
            MouseAdapter raton = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (!SwingUtilities.isLeftMouseButton(e)) return;
                    Point p = celda(e);
                    if (p == null) return;
                    pausar();
                    pintarViva = !mundo.estaViva(p.y, p.x);
                    dibujando = true;
                    pintar(p);
                }
                public void mouseDragged(MouseEvent e) {
                    Point p = celda(e);
                    if (dibujando && p != null) pintar(p);
                }
                public void mouseReleased(MouseEvent e) {
                    dibujando = false;
                }
            };
            addMouseListener(raton);
            addMouseMotionListener(raton);
        }

        private int anchoCelda() {
            return Math.min(getWidth(), getHeight()) / dimension;
        }

        private Point celda(MouseEvent e) {
            int ancho = anchoCelda();
            int lado = ancho * dimension;
            int x = e.getX() - (getWidth() - lado) / 2;
            int y = e.getY() - (getHeight() - lado) / 2;
            if (x < 0 || y < 0 || x >= lado || y >= lado) return null;
            return new Point(x / ancho, y / ancho);
        }

        private void pintar(Point p) {
            mundo.setEstadoCelula(p.y, p.x, pintarViva);
            actualizar();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int ancho = anchoCelda();
            int lado = dimension * ancho;
            int x = (getWidth() - lado) / 2;
            int y = (getHeight() - lado) / 2;
            g.setColor(new Color(211, 222, 216));
            g.fillRect(x, y, lado, lado);
            for (int f = 0; f < dimension; f++) {
                for (int c = 0; c < dimension; c++) {
                    g.setColor(mundo.estaViva(f, c) ? VIVA : Color.WHITE);
                    g.fillRect(x + c * ancho, y + f * ancho, ancho - 1, ancho - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Juego de la vida");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setContentPane(new JuegoDeLaVidaGrafico());
            ventana.pack();
            ventana.setMinimumSize(new Dimension(460, 440));
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}