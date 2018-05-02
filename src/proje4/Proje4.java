/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author RECEP
 */
public class Proje4 {

    /**
     * @param args the command line arguments
     */

    public static int n=3;
    
    
    public static void main(String[] args) throws InterruptedException, IOException {
        // TODO code application logic here
        
        Scanner scan=new Scanner(System.in);
        String isim = null;
        char harf = 0;
        boolean insan=true;
        int secim = 0,kayitAc = 0;
        oyuncu player = null;
        boolean flag = true;
        int iGonderilecek=0;
        System.out.println("Yeni oyun başlatmak için:1, kayıtlı oyundan devam etmek için 2 giriniz: ");
        do{
            try{
                flag = true;
                kayitAc=scan.nextInt();
                if(kayitAc <= 0 || kayitAc > 2){
                    throw new NumberFormatException(" Yanlis sayi girdiniz");
                }
            }catch(NumberFormatException e1){
                System.out.println("Yeniden giriniz." + e1.getMessage());
                flag = false;
                //scan.next();
            }
            catch(InputMismatchException e){
                scan.next();
                flag = false;
                System.out.println("Lütfen sayı giriniz:");
            }
            }while(flag == false);
        File dosya=new File("save.txt");
        oyunTahtasi tahta = null;
        if(kayitAc==1){
            System.out.println("Oyun tahtasının boyutunu giriniz: ");
            do{
                try{
                    flag = true;
                    n=scan.nextInt();
                }catch(InputMismatchException e){
                    scan.next();
                    flag = false;
                    System.out.println("Lütfen sayı giriniz:");
                }
            }while(flag == false);
            System.out.println("İsminizi giriniz: ");
            
            do{
                flag = true;
                isim=scan.next();
                Pattern p=Pattern.compile("^[a-zA-Z]*$");
                Matcher m=p.matcher(isim);
                if(m.find()){
                    
                } else {
                    System.out.println("İsim numara veya sembol içermemeli ");
                    flag = false;
                }
            }while(flag == false);
            System.out.println("Harfinizi kendiniz seçmek ister misiniz? (1=Evet , 0=Hayır): ");
            do{
            try{
                flag = true;
                secim=scan.nextInt();
                if(secim < 0 || secim > 2){
                    throw new NumberFormatException(" Yanlis sayi girdiniz");
                }
            }catch(NumberFormatException e1){
                System.out.println("yeniden giriniz" + e1.getMessage());
                flag = false;
                //scan.next();
            }
            catch(InputMismatchException e){
                scan.next();
                flag = false;
                System.out.println("Lütfen sayı giriniz:");
            }
            }while(flag == false);
            if(secim==0){
                player=new oyuncu();
            }else{
                System.out.println("Kullanmak istediğiniz harfi giriniz (X/O):");
                do{
                    flag = true;
                    harf=scan.next().charAt(0);
                    if(harf == 'X' || harf == 'O' ){

                    } else {
                        System.out.println("tekrar deneyiniz ");
                        flag = false;
                    }
                }while(flag == false);
                player=new oyuncu(insan,harf);
            }
            
            tahta=new oyunTahtasi();
        }else if(kayitAc == 2){
            int sayac=1;
            int indis=0;
            BufferedReader okuyucu=null;
            try{
                okuyucu=new BufferedReader(new FileReader(dosya));
                String gelensatir=okuyucu.readLine();
                n=Integer.parseInt(gelensatir);
                char yeniTahta[][]=new char[n][n];
                char ayrilanChr[]=new char[n];
                while(gelensatir!=null){
                    gelensatir=okuyucu.readLine();
                    if(sayac==1){
                        isim=gelensatir;
                    }else if(sayac==2){
                        harf=gelensatir.charAt(0);
                    }else if(sayac>2 && sayac<n+3){
                        String ayrilan[]=gelensatir.split(",");
                        for(int i=0;i<n;i++){
                            ayrilanChr[i]=ayrilan[i].charAt(0);
                            yeniTahta[indis][i]=ayrilanChr[i];
                        }
                        indis++;
                    }
                    sayac++;
                }
                insan=true;
                player=new oyuncu(insan,harf);

                tahta=new oyunTahtasi(yeniTahta);
            }catch(Exception e){
                System.out.println("Kayıtlı oyun bulunamadı.");
                System.exit(0);
            }
            
            
            
        }
        
        int baslangic=0;
        char yeniTahta[][]=tahta.oyunTahtasiniAl();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(yeniTahta[i][j]=='X' || yeniTahta[i][j]=='O'){
                    baslangic++;
                }
            }
        }

        tahta.oyunTahtasiniYazdir();
        String koordinat = null;
        String bilgisayarKoordinat;
        char bilgisayarKarakter;
        if(player.karakteriAl()=='X'){
            bilgisayarKarakter='O';
        }else{
            bilgisayarKarakter='X';
        }
        
        oyuncu bilgisayar=new oyuncu(false,bilgisayarKarakter);
        
        boolean durum,durum2;
        int cikis=0;
        int kaydedilsinMi=0;
        
        for(int i=baslangic/2;i<((n*n)/2)+1;i++){
            do{
                do{
                    try{
                        koordinat=player.insanOyuncuHamlesiniKontrol();
                        String parcalar[]=koordinat.split(",");
                        int xNok=Integer.parseInt(parcalar[0]);
                        int yNok=Integer.parseInt(parcalar[1]);
                        if(xNok >= 0 && xNok < n && yNok>=0 && yNok<n){
                            flag = true;
                        }else{
                            System.out.println("hatali sayi girdiniz");
                            flag = false;
                        }
                    }catch(Exception e){
                        System.out.println("hatali giris ");
                        flag = false;
                    }
                }while(flag == false);
                
                durum=tahta.hamleyiYaz(koordinat, player.karakteriAl());
                if(durum==false){
                    System.out.println("Belirttiğiniz koordinat dolu lütfen tekrar giriş yapınız: ");
                }
            }while(durum==false && player.oyuncuTurunuAl()==true);
            tahta.oyunTahtasiniYazdir();
            iGonderilecek=i;
            if(i==((n*n)/2)){
                break;
            }
            if(tahta.kazanan(player.karakteriAl())){
                System.out.println("Tebrikler oyunu kazandınız!");
                break;
            }
            Thread.sleep(1000);
            do{
                bilgisayarKoordinat=bilgisayar.bilgisayarHamlesiUret();
                durum2=tahta.hamleyiYaz(bilgisayarKoordinat,bilgisayarKarakter);
            }while(durum2==false && i!=(n*n)/2 && bilgisayar.oyuncuTurunuAl()==false);
            tahta.oyunTahtasiniYazdir();
            if(tahta.kazanan(bilgisayar.karakteriAl())){
                System.out.println("Maalesef oyunu kaybettiniz...");
                break;
            }
            System.out.println("Cikis yapmak istiyor musunuz? (0=hayır / 1=Evet)");
            do{
                try{
                    flag = true;
                    cikis=scan.nextInt();
                    if(cikis <= -1 || cikis > 1){
                        throw new NumberFormatException(" Yanlis sayi girdiniz");
                    }
                }catch(NumberFormatException e1){
                    System.out.println("yeniden giriniz" + e1.getMessage());
                    flag = false;
                    //scan.next();
                }
                catch(InputMismatchException e){
                    scan.next();
                    flag = false;
                    System.out.println("Lütfen sayı giriniz:!");
                }
            }while(flag == false);
            if(cikis==1){
                System.out.println("Oyunu kaydetmek istiyor musunuz? (0=hayır / 1=Evet)");
                do{
                    try{
                        flag = true;
                        kaydedilsinMi=scan.nextInt();
                        if(kaydedilsinMi <= -1 || kaydedilsinMi > 1){
                            throw new NumberFormatException(" Yanlis sayi girdiniz");
                        }
                    }catch(NumberFormatException e1){
                        System.out.println("yeniden giriniz" + e1.getMessage());
                        flag = false;
                        //scan.next();
                    }
                    catch(InputMismatchException e){
                        scan.next();
                        flag = false;
                        System.out.println("Lütfen sayı giriniz:!");
                    }
                }while(flag == false);
                
            }
            if(cikis==1 && kaydedilsinMi==1){
                FileWriter yazici=new FileWriter(dosya);
                yazici.write(Integer.toString(n));
                yazici.write(System.getProperty( "line.separator" ));
                yazici.write(isim);
                yazici.write(System.getProperty( "line.separator" ));
                yazici.write(player.karakteriAl());
                yazici.write(System.getProperty( "line.separator" ));
                yeniTahta=tahta.oyunTahtasiniAl();
                for(int j=0;j<n;j++){
                    for(int k=0;k<n;k++){
                        if(k==n-1){
                            yazici.write(yeniTahta[j][k]);
                        }else{
                            yazici.write(yeniTahta[j][k]+",");
                        }
                    }
                    if(j!=n-1){
                        yazici.write(System.getProperty( "line.separator" ));
                    }
                }
                yazici.close();
                break;
            }else if(cikis==1 && kaydedilsinMi==0){
                break;
            }
            
        }
        if(tahta.beraberlikKontrol(iGonderilecek)){
            System.out.println("Berabere kaldınız...");
        }
        
    }
    
}
