
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

    public void setVecinos(){
        for (int i = 0; i < this.dimension; i++){
            for (int j = 0; j < this.dimension;j++){
                if( i < this.dimension-1 && i > 0 && j < this.dimension-1 && j > 0){
                    this.espacio[i][j].addVecino(this.espacio[i][j-1]);
                    this.espacio[i][j].addVecino(this.espacio[i][j+1]);
                    this.espacio[i][j].addVecino(this.espacio[i-1][j]);
                    this.espacio[i][j].addVecino(this.espacio[i+1][j]);
                }
                if (i == 0){
                    this.espacio[0][j].addVecino(this.espacio[1][j]);
                    if (j == 0){
                        this.espacio[0][0].addVecino(this.espacio[0][1]);
                    }
                    else if(j == this.dimension-1){
                        this.espacio[0][this.dimension-1].addVecino(this.espacio[0][this.dimension-2]);
                    }
                    else{
                        this.espacio[0][j].addVecino(this.espacio[0][j-1]);
                        this.espacio[0][j].addVecino(this.espacio[0][j+1]);
                    }
                }
                if (i == this.dimension-1){
                    this.espacio[this.dimension-1][j].addVecino(this.espacio[this.dimension-2][j]);
                    if (j == 0){
                        this.espacio[this.dimension-1][0].addVecino(this.espacio[this.dimension-1][1]);
                    }
                    else if(j == this.dimension-1){
                        this.espacio[this.dimension-1][this.dimension-1].addVecino(this.espacio[this.dimension-1][this.dimension-2]);
                    }
                    else{
                        this.espacio[this.dimension-1][j].addVecino(this.espacio[this.dimension-1][j-1]);
                        this.espacio[this.dimension-1][j].addVecino(this.espacio[this.dimension-1][j+1]);
                    }
                }
                /*if (i== this.dimension-1 && j == this.dimension-1){
                    this.espacio[0][0].addVecino(this.espacio[this.dimension-1][this.dimension-2]);
                    this.espacio[0][0].addVecino(this.espacio[this.dimension-2][this.dimension-1]);    
                }*/
                if (j == 0 && i > 0 && i < this.dimension-1){
                    this.espacio[i][0].addVecino(this.espacio[i][1]);
                    this.espacio[i][0].addVecino(this.espacio[i-1][0]);
                    this.espacio[i][0].addVecino(this.espacio[i+1][0]);
                }
                if (j == this.dimension-1 && i > 0 && i < this.dimension-1){
                    this.espacio[i][this.dimension-1].addVecino(this.espacio[i][this.dimension-2]);
                    this.espacio[i][this.dimension-1].addVecino(this.espacio[i-1][this.dimension-1]);
                    this.espacio[i][this.dimension-1].addVecino(this.espacio[i+1][this.dimension-1]);   
                }
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
