package control;

public class Developer {

    private boolean devActivo=false;

    public boolean isDevActivo() {
        return devActivo;
    }

    public void setDevActivo() {
        if(devActivo){
            this.devActivo = false; 
        } else {
            this.devActivo = true; 
        }
	
    }
    
    
    
    
}
