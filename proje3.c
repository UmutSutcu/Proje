#include <stdio.h>
#include <stdlib.h>
#define MAXSIZE 28

int on = -1;
int arka = -1;
int counter=0 ;
void kontrol() ;

struct ucakList{
    int oncelik_id ;
    int ucak_id ;
    int talep_edilen_inis_saati ;
    int sayim;

};

struct ucakList list[28];
struct ucakList kuyruk[24];


int izin_isteyen_ucak_sayisi = 0;

int ucakBilgileri[28][4] ;

void dosyaOkuma(){ // dosyayý açma ve satýr satýr okuma
    FILE *fp;
    fp=fopen("input.txt","r");
    char dizi[50];
    for (int i =0;i<28;i++){
        ucakBilgileri[i][3]=0;
    }

    if(fp==NULL){  //boþsa dosya ; uyarý ver.
        printf("Dosya bulunamadi");
    }
    else{
        fgets(dizi,50,fp);
        printf("%s",dizi);
        for(int i = 0;i<28;i++){
            for(int j=0;j<3;j++){
                fscanf(fp,"%d",&ucakBilgileri[i][j]);
            }
        }
    }


    fclose(fp);

}

void satirOkuma(){
   list[counter].oncelik_id= ucakBilgileri[counter][0];
   list[counter].ucak_id= ucakBilgileri[counter][1];
   list[counter].talep_edilen_inis_saati= ucakBilgileri[counter][2];
   list[counter].sayim=ucakBilgileri[counter][3];
    counter++;
}

void atama(){
    for(int i=0;i<MAXSIZE;i++){
        list[i].oncelik_id= ucakBilgileri[i][0];
        list[i].ucak_id= ucakBilgileri[i][1];
        list[i].talep_edilen_inis_saati= ucakBilgileri[i][2];
        list[i].sayim=ucakBilgileri[i][3];
    }

}

void inis_saati(){
    int i,j,gecici;

    for(i=1;i<MAXSIZE;i++){
        for(j=0;j<MAXSIZE-1;j++){
            if(ucakBilgileri[j][2]>ucakBilgileri[j+1][2]){

                gecici=ucakBilgileri[j][0];
                ucakBilgileri[j][0]=ucakBilgileri[j+1][0];
                ucakBilgileri[j+1][0]=gecici;

                gecici=ucakBilgileri[j][1];
                ucakBilgileri[j][1]=ucakBilgileri[j+1][1];
                ucakBilgileri[j+1][1]=gecici;

                gecici=ucakBilgileri[j][2];
                ucakBilgileri[j][2]=ucakBilgileri[j+1][2];
                ucakBilgileri[j+1][2]=gecici;
            }
        }
    }


}

void oncelik_id(){

    int gecici;

    for(int i=0;i<MAXSIZE;i++){
        for(int j=0;j<MAXSIZE;j++){
            if(ucakBilgileri[j][2]==ucakBilgileri[j+1][2]){
                if(ucakBilgileri[j][0]>ucakBilgileri[j+1][0]){

                    gecici=ucakBilgileri[j][0];
                    ucakBilgileri[j][0]=ucakBilgileri[j+1][0];
                    ucakBilgileri[j+1][0]=gecici;

                    gecici=ucakBilgileri[j][1];
                    ucakBilgileri[j][1]=ucakBilgileri[j+1][1];
                    ucakBilgileri[j+1][1]=gecici;

                    gecici=ucakBilgileri[j][2];
                    ucakBilgileri[j][2]=ucakBilgileri[j+1][2];
                    ucakBilgileri[j+1][2]=gecici;

                }
            }
        }
    }

    atama();
}
int doluMu(){ // kuyruk dolu mu diye kontrol
    if (arka>=MAXSIZE-1){
        return 1;
    }

    else{
        return 0 ;
    }


}

int bosMu(){  // kuyruk boþ mu diye kontrol
    if (on==-1 && arka==-1)
    {
        return 1;
    }
    else{
        return 0 ;
    }

}


void add(){ // kuyruða uçak ekleme ÖNCELÝKLÝ sýraya göre

    if (doluMu()){
        satirOkuma();
        printf("%d \t", list[counter-1].oncelik_id);
        printf("%d \t", list[counter-1].ucak_id);
        printf("%d \t", list[counter-1].talep_edilen_inis_saati);
        printf("Gunde yalnizca 24 ucak inis talep edebilir o yuzden kabul edilmemistir\n");
        return;
    }

    if (bosMu()){
        on++;
        arka++;
        kuyruk[on].oncelik_id = ucakBilgileri[on][0];
        kuyruk[on].oncelik_id = ucakBilgileri[on][1];
        kuyruk[on].oncelik_id = ucakBilgileri[on][2];

        satirOkuma();
        printf("%d \t", list[on].oncelik_id);
        printf("%d \t", list[on].ucak_id);
        printf("%d \t", list[on].talep_edilen_inis_saati);
        printf("Talebiniz Onaylanmistir\n");
        return;

    }

     else{
         kuyruk[arka].oncelik_id =ucakBilgileri[counter-1][0] ;
            kuyruk[arka].ucak_id =ucakBilgileri[counter-1][1] ;
               kuyruk[arka].talep_edilen_inis_saati =ucakBilgileri[counter-1][2] ;
        satirOkuma();
        printf("%d \t", list[counter-1].oncelik_id);
        printf("%d \t", list[counter-1].ucak_id);
        printf("%d \t", list[counter-1].talep_edilen_inis_saati);
        printf("Talebiniz Onaylanmistir\n");
        arka++;
     }


}

void diz(){
    inis_saati();
    oncelik_id();

}

void kalkis(){ // kuyruktan kalkan uçaðý sildirme
    for(int i=0;i<MAXSIZE-4;i++){
        if(list[i].sayim<4){
            if(list[i].talep_edilen_inis_saati>24){
                printf("Yeni gune gecildigi icin inisiniz farkli bir havaalanina yonlendirilmistir.\n");
                printf("%d oncelikli %d idli ucak %d saatinde Sabiha gocken havaalanina inis yapmistir.\t",list[i].oncelik_id,list[i].ucak_id,list[i].talep_edilen_inis_saati%24);
                printf("Ertelenme sayisi: %d\n",list[i].sayim);
            }
            else{
                printf("%d oncelikli %d idli ucak %d saatinde inis yapmistir.\t\t\t\t\t",list[i].oncelik_id,list[i].ucak_id,list[i].talep_edilen_inis_saati);
                printf("Ertelenme sayisi: %d\n",list[i].sayim);
                for(int j=i+1;j<MAXSIZE;j++){
                    if(list[i].talep_edilen_inis_saati == list[j].talep_edilen_inis_saati){
                        ucakBilgileri[j][2]++;
                        ucakBilgileri[j][3]++;
                    }
                }
                diz();

            }
        }
        else{
            printf("%d oncelikli %d idli ucak %d saatinde Sabiha gocken havaalanina inis yapmistir.\t\t",list[i].oncelik_id,list[i].ucak_id,list[i].talep_edilen_inis_saati);
            printf("Ertelenme sayisi: %d\n",list[i].sayim);
        }
    }
    for(int i=MAXSIZE-4;i<MAXSIZE;i++){
        printf("Gunluk talep sayisi asildigi icin ");
        printf("%d oncelikli %d idli ucak %d saatinde inis talebiniz reddedilmistir Sabiha gocken havaalanina yonlendiriliyorsunuz \n"
               ,list[i].oncelik_id,list[i].ucak_id,list[i].talep_edilen_inis_saati);
    }
    diz();
}


void outputYazdirma(){
    FILE *cikti;
    cikti=fopen("output.txt","w");

    if(cikti == NULL){
        printf("Dosya acilamadi..");
    }
    else{
        on++;
        fprintf(cikti,"oncelik_id ucak_id talep_edilen_inis_saati inis_saati gecikme_suresi kalkis_saati\n");
        printf("oncelik_id ucak_id talep_edilen_inis_saati inis_saati gecikme_suresi kalkis_saati\n");
        for(int i=0;i<MAXSIZE;i++){
            printf("%d %d %d %d %d %d\n",list[i].oncelik_id,list[i].ucak_id,list[i].talep_edilen_inis_saati-list[i].sayim
                   ,list[i].talep_edilen_inis_saati,list[i].sayim,list[i].talep_edilen_inis_saati+1);
            fprintf(cikti,"%d %d %d ",list[i].oncelik_id,list[i].ucak_id,list[i].talep_edilen_inis_saati-list[i].sayim);
            fprintf(cikti,"%d %d %d \n",list[i].talep_edilen_inis_saati,list[i].sayim,list[i].talep_edilen_inis_saati+1);
        }
    }
    fclose(cikti);
}


int main(){


    printf("------------------------------------------------------\n\n\n");
    printf(" Istanbul Havalimani Ucus Yonetim Sistemine Hosgeldiniz \n\n\n");
    printf("------------------------------------------------------\n");
    dosyaOkuma();

    for (int i = 0; i < 28; i++){
        add();
    }

    diz();
    printf("\n---------------------\n\n");
    kalkis();
    printf("\n---------------------\n\n");
    outputYazdirma();


    return 0;


}
