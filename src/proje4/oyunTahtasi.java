/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje4;

/**
 *
 * @author RECEP
 */
public class oyunTahtasi {
    
    int n=Proje4.n;
    
    private char oynTahtasi[][];
    
    public oyunTahtasi(){
        this.oynTahtasi = new char[n][n];
        for(int i=0;i<oynTahtasi.length;i++){
            for(int j=0;j<oynTahtasi.length;j++){
                oynTahtasi[i][j]=' ';
            }
        }
    }
    
    public oyunTahtasi(char[][] oynTahtasi){
        this.oynTahtasi=oynTahtasi;
    }
    
    public char[][] oyunTahtasiniAl(){
        return oynTahtasi;
    }
    
    public void oyunTahtasiniYazdir(){
        for (int i = 0; i < n; i++) {
            for(int j=0;j<n;j++){
                if(j==n-1){
                    System.out.print("+---+");
                }else{
                    System.out.print("+---");
                }
            } 
            System.out.println("");
            for (int j = 0; j < n; j++){
                if(j==n-1){
                    System.out.print("| "+oynTahtasi[i][j]+" |");
                }else{
                    System.out.print("| "+oynTahtasi[i][j]+" ");
                }
            }
            System.out.println("");  
        }
        for(int j=0;j<n;j++){
                if(j==n-1){
                    System.out.print("+---+");
                }else{
                    System.out.print("+---");
                }
            }
        System.out.println("\n\n");
    }
    
    public boolean hamleyiYaz(String koordinat,char oyuncu){
        String parcalar[]=koordinat.split(",");
        int xNok=Integer.parseInt(parcalar[0]);
        int yNok=Integer.parseInt(parcalar[1]);
        boolean durum;
        
        if(oynTahtasi[xNok][yNok]==' '){
            oynTahtasi[xNok][yNok]=oyuncu;
            durum=true;
            return durum;
        }else{
            durum=false;
            return durum;
        }
        

    }
    
    public boolean kazanan(char oyuncu){
        int satirKazananX=0;
        int satirKazananO=0;
        int sutunKazananX=0;
        int sutunKazananO=0;
        int kosegenKazananX=0;
        int kosegenKazananO=0;
        boolean durum=false;
        for(int i=0;i<oynTahtasi.length;i++){
            for(int j=0;j<oynTahtasi.length;j++){
                if(oynTahtasi[i][j]=='X'){
                    satirKazananX++;
                }else if(oynTahtasi[i][j]=='O'){
                    satirKazananO++;
                }
            }
            if(satirKazananX==n && oyuncu=='X'){
                durum = true;
                return durum;
            }else if(satirKazananX==n && oyuncu=='O'){
                durum = false;
                return durum;
            }
            if(satirKazananO==n && oyuncu=='O'){
                durum = true;
                return durum;
            }else if(satirKazananO==n && oyuncu=='X'){
                durum = false;
                return durum;
            }
            satirKazananX=0;
            satirKazananO=0;
        }
        
        for(int i=0;i<oynTahtasi.length;i++){
            for(int j=0;j<oynTahtasi.length;j++){
                if(oynTahtasi[j][i]=='X'){
                    sutunKazananX++;
                }else if(oynTahtasi[j][i]=='O'){
                    sutunKazananO++;
                }
            }
            if(sutunKazananX==n && oyuncu=='X'){
                durum = true;
                return durum;
            }else if(sutunKazananX==n && oyuncu=='O'){
                durum = false;
                
                return durum;
            }
            if(sutunKazananO==n && oyuncu=='O'){
                durum = true;
                return durum;
            }else if(sutunKazananO==n && oyuncu=='X'){
                durum = false;
                return durum;
            }
            sutunKazananX=0;
            sutunKazananO=0;
        }
        
        for(int i=0;i<oynTahtasi.length;i++){
            
            if(oynTahtasi[i][i]=='X'){
                kosegenKazananX++;
            }else if(oynTahtasi[i][i]=='O'){
                kosegenKazananO++;
            }
            
            if(kosegenKazananX==n && oyuncu=='X'){
                durum = true;
                return durum;
            }else if(kosegenKazananX==n && oyuncu=='O'){
                durum = false;
                return durum;
            }
            if(kosegenKazananO==n && oyuncu=='O'){
                durum = true;
                return durum;
            }else if(kosegenKazananO==n && oyuncu=='X'){
                durum = false;
                return durum;
            }
        } 
        kosegenKazananX=0;
        kosegenKazananO=0;
        for(int i=0;i<oynTahtasi.length;i++){
            
            if(oynTahtasi[i][n-i-1]=='X'){
                kosegenKazananX++;
            }else if(oynTahtasi[i][n-i-1]=='O'){
                kosegenKazananO++;
            }
            
            if(kosegenKazananX==n && oyuncu=='X'){
                durum = true;
                return durum;
            }else if(kosegenKazananX==n && oyuncu=='O'){
                durum = false;
                return durum;
            }
            if(kosegenKazananO==n && oyuncu=='O'){
                durum = true;
                return durum;
            }else if(kosegenKazananO==n && oyuncu=='X'){
                durum = false;
                return durum;
            }         
        }
        
        return durum;
    }
    
    public boolean beraberlikKontrol(int i){
        if(i==((n*n)/2) && kazanan('X')==false && kazanan('O')==false){
            return true;
        }else{
            return false;
        }
    }
    
}
