package Model;

public class SalaRaiz {

    private int idSala;
    private int bloco;
    private int numeroSala;

    public SalaRaiz(){
        
    }
    
    public SalaRaiz(int parseInt) {
        idSala = parseInt;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getBloco() {
        return bloco;
    }

    public void setBloco(int bloco) {
        this.bloco = bloco;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }
    
    
    
    
}
