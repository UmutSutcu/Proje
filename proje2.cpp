#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <graphics.h>
#define maxcharuzunluk 256

struct sonekDugumu {
    struct sonekDugumu *uye[maxcharuzunluk];

    struct sonekDugumu *sonekBaglanti;
    int bas;
    int *son;

    int sonekAdres;
};

typedef struct sonekDugumu dugum;

char metin[1000];
dugum *kok = NULL;


dugum *sonNode = NULL;
dugum *hazirNode = NULL;

int hazirUc = -1;
int hazirUzunluk = 0;

int kalanSonekSayac = 0;
int yaprakSon = -1;
int *kokSon = NULL;
int *ayrikSon = NULL;
int boyut = -1;

dugum *yeniNode(int basla, int *end){
    dugum *node =(dugum*) malloc(sizeof(dugum));
    int i;
    for (i = 0; i < maxcharuzunluk; i++)
          node->uye[i] = NULL;

    node->sonekBaglanti = kok;
    node->bas = basla;
    node->son = end;

    node->sonekAdres = -1;
    return node;
}

int kokUzunlugu(dugum *n) {
    if(n == kok)
        return 0;
    return *(n->son) - (n->bas) + 1;
}

int altSatir(dugum *acikNode)
{
    if (hazirUzunluk >= kokUzunlugu(acikNode))
    {
        hazirUc += kokUzunlugu(acikNode);
        hazirUzunluk -= kokUzunlugu(acikNode);
        hazirNode = acikNode;
        return 1;
    }
    return 0;
}

void agaciGenislet(int konum)
{

    yaprakSon = konum;

    kalanSonekSayac++;

    sonNode = NULL;

    while(kalanSonekSayac > 0) {

        if (hazirUzunluk == 0)
            hazirUc = konum;
        if (hazirNode->uye[metin[hazirUc]] == NULL) {

            hazirNode->uye[metin[hazirUc]] = yeniNode(konum, &yaprakSon);

            if (sonNode != NULL) {
                sonNode->sonekBaglanti = hazirNode;
                sonNode = NULL;
            }
        }

        else{

            dugum *sonraki = hazirNode->uye[metin[hazirUc]];
            if (altSatir(sonraki)){
                continue;
            }

            if (metin[sonraki->bas + hazirUzunluk] == metin[konum]) {
                if(sonNode != NULL && hazirNode != kok) {
                    sonNode->sonekBaglanti = hazirNode;
                    sonNode = NULL;
                }

                hazirUzunluk++;
                break;
            }

            ayrikSon = (int*) malloc(sizeof(int));
            *ayrikSon = sonraki->bas + hazirUzunluk - 1;

            dugum *ayir = yeniNode(sonraki->bas, ayrikSon);
            hazirNode->uye[metin[hazirUc]] = ayir;

            ayir->uye[metin[konum]] = yeniNode(konum, &yaprakSon);
            sonraki->bas += hazirUzunluk;
            ayir->uye[metin[sonraki->bas]] = sonraki;

            if (sonNode != NULL){
                sonNode->sonekBaglanti = ayir;
            }
            sonNode = ayir;
        }

        kalanSonekSayac--;
        if (hazirNode == kok && hazirUzunluk > 0) {
            hazirUzunluk--;
            hazirUc = konum - kalanSonekSayac + 1;
        }
        else if (hazirNode != kok) {
            hazirNode = hazirNode->sonekBaglanti;
        }
    }
}
int xkordinant=50;
int ykordinant=0;
int deger;
char yazilacak[100];

void yazdir(int i, int j){
    int k;
    int x=0;
    for (k=i; k<=j; k++){
        yazilacak[x]=metin[k];
        printf("%c", metin[k]);
        x++;
    }

}
int oncekideger =0;
int onceki2deger=0;
void grafik(){
    int k;
   // int oncekideger=0;
    if(deger==0){
        circle(500,50,50);
        outtextxy(490,50,yazilacak);
    }
    if(deger == -1){
        line(445,95,345,195);
        circle(290,240,50);
        outtextxy(280,240,yazilacak);
    }
    if(deger == onceki2deger){
        line(245,295,145,395);
        circle(95,345,50);
        outtextxy(90,345,yazilacak);
    }
    if(deger>0 && oncekideger ==-1){
        line(335,285,435,390);
        circle(485,440,50);
        outtextxy(475,440,yazilacak);
    }
    if(deger>0){
        line(545,95,645,195);
        circle(695,245,50);
        outtextxy(685,245,yazilacak);

   }

   // oncekideger=deger;

}

void sonekAdresiAyarla(dugum *n, int yukseklik){
    if (n == NULL)  return;

    if (n->bas != -1) {
       yazdir(n->bas, *(n->son));
    }
    int yaprak = 1;
    int i;
    for (i = 0; i < maxcharuzunluk; i++){
        if (n->uye[i] != NULL){
            if (yaprak == 1 && n->bas != -1){
                printf(" [%d]\n", n->sonekAdres);
                deger=n->sonekAdres;
                onceki2deger=oncekideger;
                grafik();
                oncekideger=deger;

            }

            yaprak = 0;
            sonekAdresiAyarla(n->uye[i], yukseklik +kokUzunlugu(n->uye[i]));
        }
    }
    if (yaprak == 1){
        n->sonekAdres = boyut - yukseklik;
        printf(" [%d]\n", n->sonekAdres);
        deger=n->sonekAdres;
        onceki2deger=oncekideger;
        grafik();
        oncekideger=deger;
    }
}

void sonekAgaciniBosalt(dugum *n){
    if (n == NULL)
        return;
    int i;
    for (i = 0; i < maxcharuzunluk; i++){
        if (n->uye[i] != NULL){
            sonekAgaciniBosalt(n->uye[i]);
        }
    }
    if (n->sonekAdres == -1)
        free(n->son);
    free(n);
}

void sonekAgaciniOlustur(){
    boyut = strlen(metin);
    int i;
    kokSon = (int*) malloc(sizeof(int));
    *kokSon = - 1;

    kok = yeniNode(-1, kokSon);

    hazirNode = kok;
    for (i=0; i<boyut; i++)
        agaciGenislet(i);
    int yukseklik = 0;
    sonekAdresiAyarla(kok, yukseklik);
}
int zitUc(char *str, int idx, int basla, int end){
    int k = 0;
    for(k=basla; k<=end && str[idx] != '\0'; k++, idx++){
        if(metin[k] != str[idx])
            return -1;
    }
    if(str[idx] == '\0')
        return 1;
    return 0;
}

int pozisyonBul(dugum *n){
    if(n == NULL)
        return 0;
    int count = 0;
    if(n->sonekAdres > -1){
      //  printf("\nBulundugu pozisyon: %d\n", n->sonekAdres);
        return 1;
    }
    int i = 0;
    for (i = 0; i < maxcharuzunluk; i++){
        if(n->uye[i] != NULL){
            count += pozisyonBul(n->uye[i]);
        }
    }
    return count;
}

int say(dugum *n){
    if(n == NULL)
        return 0;
    return pozisyonBul(n);
}

int gezin(dugum *n, char* str, int a){
    if(n == NULL){
        return -1;
    }
    int res = -1;
    if(n->bas != -1){
        res = zitUc(str, a, n->bas, *(n->son));
        if(res == -1)
            return -1;
        if(res == 1) {
            if(n->sonekAdres > -1)
                printf("\nKac kez gecmektedir: 1 ve pozisyonu: %d",n->sonekAdres);
            else
                printf("\nKac kez gecmektedir: %d\n", say(n));
            return 1;
        }
    }
    a = a + kokUzunlugu(n);
    if(n->uye[str[a]] != NULL)
        return gezin(n->uye[str[a]], str, a);
    else
        return -1;
}

void altKatarAra(char* str){
    int sil = gezin(kok, str, 0);
    if(sil == 1){
      }
    else
        printf("\naranan %s altkatari bulunamadi\n", str);
}
int kontrol(){
    char sonek[1000];
    char onekDizisi[1000];
    char onekDizisi_i[100][100];
    strcpy (onekDizisi, metin);

    for (int i = 0; i < strlen (metin); i = i + 1){
      strncpy (onekDizisi_i[i], onekDizisi, i + 1);

    }
    for( int i=0;i<strlen(metin);i++){
        strcpy(sonek,&metin[i]);
        for (int j = 0; j < strlen (metin)-1; j = j + 1){
            strncpy (onekDizisi_i[j], onekDizisi, j + 1);
            //printf("sonek:%s\tonek:%s\n",sonek,onekDizisi_i[j]);
            if(strcmp(sonek,onekDizisi_i[j])==0){
                return 1;
            }

        }
    }
}

void gezin2(dugum *n, int yukseklik, int* maxUzunluk,int* katarBaslama){
    if(n == NULL){
        return;
    }
    int i=0;
    if(n->sonekAdres == -1) {
        for (i = 0; i < maxcharuzunluk; i++){
            if(n->uye[i] != NULL){
                gezin2(n->uye[i], yukseklik + kokUzunlugu(n->uye[i]), maxUzunluk,katarBaslama);
            }
        }
    }
    else if(n->sonekAdres > -1 && (*maxUzunluk < yukseklik - kokUzunlugu(n))){
        *maxUzunluk = yukseklik - kokUzunlugu(n);
        *katarBaslama = n->sonekAdres;
    }
}


void enUzunTekrar(){
    int maxUzunluk = 0;
    int baslamaKatar = 0;
    gezin2(kok, 0, &maxUzunluk, &baslamaKatar);
   // printf("Uzunlugu %d, Baslangic konumu %d\n", maxHeight,substringStartIndex);
    printf("En uzun tekrar eden: ");
    int k;
    char deneme[10];
    for (k=0; k<maxUzunluk; k++){
        printf("%c", metin[k + baslamaKatar]);
        deneme[k]=metin[k+baslamaKatar];
    }
   // printf("\n\nbu bir kontroldur : %s",deneme);
    altKatarAra(deneme);

    if(k == 0)
        printf("Tekrar eden yok");
    printf("\n");
}


int main(int argc, char *argv[]){
    char kelime[1000];
    printf("Metni giriniz: ");
    gets(kelime);
    strcpy(metin, kelime);
    int a=kontrol();
    if(a==1){
        printf("Sonek agaci olusturulamaz\n");
    }
    else{
        printf("Sonek agaci olusturulabilir\n");
        printf("Aranacak altkatar: ");
        char aranacakKelime[500];
        scanf("%s",aranacakKelime);
        initwindow(1000,1000);
        sonekAgaciniOlustur();
        printf("metin: %s, Aranacak kelime: %s",kelime,aranacakKelime);
        altKatarAra(aranacakKelime);
        printf("\n\n");
        enUzunTekrar();
        getch();
        closegraph();
        sonekAgaciniBosalt(kok);
    }
    return 0;
}
