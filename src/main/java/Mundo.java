import java.util.ArrayList;

public class Mundo {
    private Celula[][] espacio;
    private int dimension;

    public Mundo(int n){
        this.dimension = n;
        this.espacio = new Celula[n][n];
        for (int i = 0; i < n;i++){
            for (int j = 0; j < n; j++){
                this.espacio[i][j] =  new Celula();
            }
        } 
    }

    public void setEstadoCelula(int i, int j,boolean estado){
        this.espacio[i][j].setEstado(estado);  
    }

    public boolean estaViva(int fila, int columna) {
        return espacio[fila][columna].getEstado();
    }

    public void setVecinos() {
        setVecinos(false);
    }

    public void setVecinos(boolean incluirDiagonales) {
        for (int fila = 0; fila < dimension; fila++) {
            for (int columna = 0; columna < dimension; columna++) {
                ArrayList<Celula> vecinos = new ArrayList<>();
                for (int df = -1; df <= 1; df++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        if ((df == 0 && dc == 0) || (!incluirDiagonales && df != 0 && dc != 0)) {
                            continue;
                        }
                        int f = fila + df;
                        int c = columna + dc;
                        if (f >= 0 && f < dimension && c >= 0 && c < dimension) {
                            vecinos.add(espacio[f][c]);
                        }
                    }
                }
                espacio[fila][columna].setVecinos(vecinos);
            }
        }
    }
    public void siguienteEstado(){
        for (int i = 0; i < this.dimension; i++){
            for (int j = 0; j < this.dimension;j++){
                if(this.espacio[i][j].getEstado()){
                    if(this.espacio[i][j].getVecinosVivos() == 2 || this.espacio[i][j].getVecinosVivos() == 3)
                        this.espacio[i][j].setEstadoSiguiente(true);
                    else 
                        this.espacio[i][j].setEstadoSiguiente(false);
                }else{
                    if(this.espacio[i][j].getVecinosVivos()==3){
                        this.espacio[i][j].setEstadoSiguiente(true);
                    }
                    else
                       this.espacio[i][j].setEstadoSiguiente(false);
                }
            }
        }
        for (int i = 0; i < this.dimension; i++){
            for (int j = 0; j < this.dimension;j++){
                this.espacio[i][j].actualizarEstado();
            }
        }

    }
    public void desplegarMundo() {
        for (int i=0;i<this.dimension;i++){
            for(int j =0;j<this.dimension;j++){
                if(j == 0)
                    System.out.print("\t");
                if (this.espacio[i][j].getEstado())
                    System.out.print("# ");
                else
                    System.out.print("~ ");
            }
            System.out.println();
        }
    }
}
