#include <stdio.h>
#include <stdlib.h>
int sabit = 50 ;

struct kayit{
    int ogrno;
    int derskodu;
    int puan;

};

struct kayit sinif[100];
FILE *ogrinfo;

void indexDosyasiOlustur(){
    ogrinfo = fopen("veri.txt","w");

    for(int i= 0 ;i<sabit;i++){
        fprintf(ogrinfo,"%d.ogrencinin no: %d \n",i+1,sinif[i].ogrno);
        fprintf(ogrinfo,"%d.ogrencinin dersi: %d \n",i+1,sinif[i].derskodu);
        fprintf(ogrinfo,"%d.ogrencinin notu:%d \n\n\n",i+1,sinif[i].puan);
    }
    fclose(ogrinfo);
}
void indeksDosyasiniGoster(){

    ogrinfo = fopen("veri.txt","r");

    while(!feof(ogrinfo)){
        fputchar(fgetc(ogrinfo));
    }

    fclose(ogrinfo);

}
void kayitGuncelle(int i ){
    printf("%d.ogrencinin notu:%d ",i,sinif[i].puan);
    printf("\nYeni notunu giriniz: ");
    int yeni;
    scanf("%d",&yeni);

    sinif[i].puan=yeni;
    printf("Yeni notu: %d",sinif[i].puan);

    indexDosyasiOlustur();

}
void kayitEkle(){
    sabit++;
    printf("Yeni ogrencinin no: ");
    scanf("%d",&sinif[sabit].ogrno);
    printf("Yeni ogrencinin derskodu: ");
    scanf("%d",&sinif[sabit].derskodu);
    printf("Yeni ogrencinin puani: ");
    scanf("%d",&sinif[sabit].puan);

    /*ogrinfo = fopen("veri.txt","a");
    fprintf(ogrinfo,"%d.ogrencinin no: %d \n",sabit,sinif[sabit].ogrno);
    fprintf(ogrinfo,"%d.ogrencinin dersi: %d \n",sabit,sinif[sabit].derskodu);
    fprintf(ogrinfo,"%d.ogrencinin notu:%d \n\n\n",sabit,sinif[sabit].puan);
    fclose(ogrinfo);*/
    indexDosyasiOlustur();

}


int main()
{


        for(int i= 0 ;i<50;i++){
            sinif[i].ogrno=rand()%100;
            sinif[i].derskodu=rand()%3+1;
            sinif[i].puan=rand()%100;
        }

        int sec=1;

        while(sec !=0){
            printf("\n1-)index dosyasi olustur.\n");
            printf("2-)Kayit guncelle\n");
            printf("3-)Kayit ekle\n");
            printf("4-)indeks dosyasini goster\n");
            printf("0-)Cikis\n");
            printf("Birini seciniz lutfen: ");
            scanf("%d",&sec);

            switch (sec){
                case 1:
                    indexDosyasiOlustur();
                    break;
                case 2:
                    printf("Kayit numarasini giriniz: ");
                    int a;
                    scanf("%d",&a);
                    kayitGuncelle(a);
                    break;
                case 3:
                    kayitEkle();
                case 4:
                    indeksDosyasiniGoster();


            }



        }
    return 0;

}
