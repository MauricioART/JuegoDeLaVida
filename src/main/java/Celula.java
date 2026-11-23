import java.util.ArrayList;

public class Celula {
    private boolean estado;
    private boolean estadoSiguiente;
    private ArrayList<Celula> vecinos;
    private int vecinosVivos;
    
    public Celula(){
        this.estado = false;
        this.estadoSiguiente = false;
        this.vecinos = new ArrayList<>();
        this.vecinosVivos = 0;
    }
    public boolean getEstado(){
        return this.estado;
    }
    public void setEstado(boolean estado){
        this.estado = estado;
    }
    public boolean getEstadoSiguiente(){
        return this.estadoSiguiente;
    }
    public void setEstadoSiguiente(boolean estado){
        this.estadoSiguiente = estado;
    }

    public void setVecinos(ArrayList<Celula> vecinos){
        this.vecinos = vecinos;
    }

    public void addVecino(Celula vecino){
        this.vecinos.add(vecino);
    }
    public Celula getVecino(int index){
        return this.vecinos.get(index);
    } 
    
    public void actualizarEstado(){
        this.estado = this.estadoSiguiente;
    }

    public int getVecinosVivos(){
        this.vecinosVivos = 0;
        for (Celula cell: this.vecinos ){
            if(cell.estado)
                this.vecinosVivos++;
        }
        return this.vecinosVivos;
    }
}
