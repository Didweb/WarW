package graficos;



class Iso2D{
    
    
    private int totalTils;
    private int relativaX;
    private int relativaY;
    private int altoTil;
    private int anchoTil;
    private int scrollX;
    private int scrollY;
    
    private int[] mapa;
    private int[][] res;
    

    public Iso2D(int[] mapa, int anchoTil, int altoTil, int scrollX, int scrollY){
    	
    	this.anchoTil = anchoTil;
    	this.altoTil = altoTil;
    	this.scrollX = scrollX;
    	this.scrollY = scrollY;
    	this.mapa = mapa;
    	this.totalTils = mapa.length;
    	

    }
    
    public void calcularIso(){

    	int cuadrante = (int) Math.sqrt(totalTils);
    	int diago = cuadrante;
    	
    	res = new int[totalTils][3];
    	
    	int loopDiago = 0;
    	int n = 0;
    	
    	
    	
    	    
    	for (int f=0;f<diago;f++){

    	    int[] alma ;
    	    int[] almaX;
    	    int[] almaY;
    	    alma = new int[f+1];
    	    almaX = new int[f+1];
    	    almaY = new int[f+1];
    	 
     	 
     	 
    	    for (int lp=loopDiago;lp>=0;lp--){

            	if(f==0){
            	    alma[f]=0;
            		}
            	
            	relativaX = (f-lp)*(anchoTil/2)+(scrollX-(lp*anchoTil/2));
            	relativaY = (f)*(altoTil)+scrollY;
            	
            	alma[lp]=(lp*cuadrante);
            	
            	if(lp==0 && f>0){
            	    alma[lp]=f;   
            		} else if(lp>0){
            	    
            	    alma[lp]=alma[lp]+((f)-lp);
            		}
            	
            	almaX[lp]=relativaX;
            	almaY[lp]=relativaY;
            	
            	
            	
            	res[n][0] = alma[lp];
            	res[n][1] = almaX[lp];
            	res[n][2] = almaY[lp];
            	n++;
    	    }
    	   

    	 if (loopDiago<cuadrante){
    	 loopDiago++;  
    	 } else {
    	 loopDiago=1; 
    	 }
    	    
    	 
    	 
    	}
    	
    	
    	int ultimaX ;
    	if(diago%2==0){
        	ultimaX = scrollX-((diago/2)*anchoTil)+(anchoTil);  
        	} else {
        	ultimaX = scrollX-(((diago-1)/2)*anchoTil)+(anchoTil/2);
        	}
    	
    	int inrever = diago;
    	int valn = diago;
    	int nn = n;
    	int fijdiagos=diago;
    	
    	for(int f=0;f<diago;f++){

       	 	int[] alma ;
       	 	int[] almaX;
       	 	int[] almaY;
       	 	alma = new int[inrever];
        	almaX = new int[inrever];
        	almaY = new int[inrever];    
        	 
        	
        	for (int x=0;x<inrever-1;x++){
        	    
            	    
            	    if (f==0){
            		alma[x] = res[n-(valn)][0]+1;
            	    	} else {
            		alma[x] = res[nn-(fijdiagos)][0]+1;
            	    	}
            	    	
                    	relativaX = (anchoTil*x)+(ultimaX);
                    	relativaY = (f)*(altoTil)+scrollY+(diago*altoTil);
                    	
            	    	almaX[x] = relativaX;
            	    	almaY[x] = relativaY;
                	
            	
                    res[nn][0] = alma[x];
                	res[nn][1] = almaX[x];
                	res[nn][2] = almaY[x];
                	
                	valn--;
                	nn++;
                	
                	
            	}
        	ultimaX = ultimaX + (anchoTil/2);
        	inrever--;
        	n++;
        	valn=2;
        	fijdiagos--;
    	

	
    	}
    	

    }
    


    public int[][] getRes() {
        return res;
    }
    
    
    public int[] getMapa() {
        return mapa;
    }
    
}
    

