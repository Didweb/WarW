package graficos;

public class Dijkstra {

	private int[][] mapaDi;
	private boolean[][] visitado;
	
	private int nLados;
	private int LogicaX;
	private int LogicaY;
	
	
	public Dijkstra(int nLados, int LogicaX, int LogicaY){
		this.nLados = nLados;
		this.LogicaX = LogicaX;
		this.LogicaY = LogicaY;
		iniTodo();
		
	}
	
	public void iniTodo(){
		
		mapaDi = new int[nLados][nLados];
		visitado = new boolean[nLados][nLados];
		
		int x=0;
		int y=0;
		for(int i=0;i<(nLados*nLados);i++){
			
			if(x==nLados){
			x=0;
			y++;
			}
			mapaDi[x][y]=-1;
			visitado[x][y] = false;
			
			x++;
			
		}
		mapaDi[LogicaX][LogicaY]=0;
		visitado[LogicaX][LogicaY] = true;
		
	}
	

	public void crear(int[][] obstaculos){
		System.out.println("Total obsta="+obstaculos.length+" - "+"Total mapaDi="+mapaDi.length);
		int xx=0;
		int yy=0;
		for(int i=0;i<(nLados*nLados);i++){
			if(xx==nLados){
				xx=0;
				yy++;
				}
			System.out.println("*- "+yy+" "+xx+" = "+obstaculos[yy][xx]);
			xx++;
		}
		
		int porHacer=nLados*nLados;
		System.out.println("porHacer------------------------------------------------: "+porHacer);
		
		while(porHacer>0){
			
			//System.out.println(porHacer);
				int x=0;
				int y=0;
				
				for(int i=0;i<(nLados*nLados);i++){
					if(x==nLados){
						x=0;
						y++;
						}
					
				//	System.out.println("x= "+x+" , Y:"+y+" nLados: "+nLados+" mapaDi["+LogicaX+"]["+LogicaY+"] ");
					if(obstaculos[x][y]==0){
						visitado[x][y]=true;
					}
					
					if(visitado[x][y]==true && obstaculos[x][y]!=0){
						if((x-1)>=0 ){
							if(visitado[x-1][y]==false && obstaculos[x-1][y]!=0){
								mapaDi[x-1][y]=mapaDi[x][y]+1;
								visitado[x-1][y]=true;
							}
						}
						if((x+1)<nLados){
							if(visitado[x+1][y]==false   && obstaculos[x+1][y]!=0){
								mapaDi[x+1][y]=mapaDi[x][y]+1;
								visitado[x+1][y]=true;
							}
						}
						if((y-1)>=0){
							if(visitado[x][y-1]==false  && obstaculos[x][y-1]!=0){
								mapaDi[x][y-1]=mapaDi[x][y]+1;
								visitado[x][y-1]=true;
							}
						}
						if((y+1)<nLados){
							if(visitado[x][y+1]==false  && obstaculos[x][y+1]!=0){
								mapaDi[x][y+1]=mapaDi[x][y]+1;
								visitado[x][y+1]=true;
							}
						}
					
						
					}
					//System.out.println(x+","+y+" : "+visitado[x][y]+" Obstaculo "+obstaculos[x][y]);
					x++;
					
				}
				x=0;
				y=0;
				porHacer=0;
						for(int i=0;i<(nLados*nLados);i++){
							if(x==nLados){
								x=0;
								y++;
								}
							if(visitado[x][y]==false){
								porHacer++;
								
							}
							x++;
						}
						//System.out.println("COntrol: "+control);
						
			
						//System.out.println("porHacer------------------------------------------------: "+porHacer);	
				}
		System.out.println("Salimos del Loop Distancia");
		}

	public int[][] getMapaDi() {
		return mapaDi;
	}
}
