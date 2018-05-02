/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje4;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author RECEP
 */
public class oyuncu {
    private char harf;
    private boolean insanMi;
    private String kullaniciAdi;
    String sonHamle;
    
    public oyuncu(){
        harf='X';
        insanMi=true;
    }
    public oyuncu(boolean insanMiKontrolu){
        insanMi=insanMiKontrolu;
        if(insanMiKontrolu==true){
            harf='X';
        }else{
            harf='O';
        }
    }
    public oyuncu(boolean insanMiKontrolu,char kr){
        insanMi=insanMiKontrolu;
        harf=kr;
    }
    
    public char karakteriAl(){
        return harf;
    }
    public boolean oyuncuTurunuAl(){
        return insanMi;
    }
    public String oyuncununHamlesiniAl(){
        return sonHamle;
    }
    public String insanOyuncuHamlesiniKontrol(){
        Scanner scan=new Scanner(System.in);
        String hamle;
        System.out.println("Hamle yapacağınız koordinatı giriniz: (örnek: 2,1)");
        hamle=scan.nextLine();
        sonHamle=hamle;
        return hamle;
    }
    public String bilgisayarHamlesiUret(){
        Random rnd=new Random();
        int x,y;
        int n=Proje4.n;
        x=rnd.nextInt(n);
        y=rnd.nextInt(n);
        String koordinat=Integer.toString(x)+","+Integer.toString(y);
        return koordinat;
    }
}
