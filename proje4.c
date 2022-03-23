#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>


//option 1 :
int counter[10]= {0,0,0,0,0,0,0,0,0,0};
int hafizacounter=0;
int varmi=0;

int uzunlukbul(char kelime[])
{
    int uzunluk=0;

    while(kelime[uzunluk]!='\0')
    {
        uzunluk++;
    }
    return uzunluk;
}

int KelimeAra(char cumle[],char kelime[])  //for while bulmak için
{
    int uz1=uzunlukbul(cumle),uz2=uzunlukbul(kelime);
    int i,j;

    for(i=0; i<=uz1-uz2; i++)
    {
        for(j=0; j<uz2; j++)
        {
            if(cumle[i+j]!=kelime[j])
                break;
        }
        if(j==uz2)
            return 1;
    }

    return 0;

}

int k=-1;

void recursive_fonksiyon()
{

    //return sonrası () arayarak bulabiliyoruz
    //void tipli fonksiyonlarda patladığı için kod bloğunu eklemedik.

}


int logn_sayaci =0 ;
void ZamanKarmasikligi(char dizi[])
{
    char kelime1[5]="for";
    char kelime2[8]="while";
    char karakter='}';
    char *x=strchr(dizi,karakter);


    if(x)
    {
        //  printf("buraya girdim\n");
        varmi=0;
    }
    if((KelimeAra(dizi,kelime2) || KelimeAra(dizi,kelime1)==1) && varmi>0)
    {

        char mul='*';
        char div = '/';
        if(KelimeAra(dizi,"for")==1)
        {
            if(strchr(dizi,mul)|| strchr(dizi,div)!=NULL)
            {
                logn_sayaci+=1;
            }
        }

        counter[k]++;
    }
    else if((KelimeAra(dizi,kelime1)==1 || KelimeAra(dizi,kelime2))==1)
    {
        k++;
        counter[k]++;
        varmi++;
    }

    //printf("counter degeri: %d\n",counter[k]);
}

int BuyukBul(int dizi[])
{
    int deger=dizi[0];

    for(int i=0; i<10; i++)
    {
        if(deger<dizi[i])
        {
            deger=dizi[i];
        }
    }
    return deger;
}

void zamanYazdir(int x)
{
    printf("\n\n");
    if(logn_sayaci>0)
    {
        x=x-logn_sayaci;
        switch(x)
        {
        case 0:
            printf("O(logn^%d)\n",logn_sayaci);
            break;

        case 1:
            printf("O(n*logn^%d)\n",logn_sayaci);
              break;

        default :
            printf("O(n^%d*logn^%d)\n",x,logn_sayaci);
              break;
        }
        }
        else
        {
            switch(x)
            {
            case 0:
                printf("O(1)\n");
                break;

            case 1:
                printf("O(n)\n");
                  break;
            default :
                printf("O(n^%d)\n",x);
                  break;
            }
        }




}
//option 2  :
int kalinparantez[10]= {0,0,0,0,0,0,0,0,0,0};
char matrisdeger=' ';

void hafizaYazdir(int x)
{
    printf("\n\n");
    switch(x)
    {
    case 0:
        printf("Hafiza Karmasikligi: O(1) %c", matrisdeger);
        break;
    case 1:
        printf("Hafiza Karmasikligi: O(n) %cn", matrisdeger);
        break;
    case 2:
        printf("Hafiza Karmasikligi:O(n^2) %cn^2", matrisdeger);
        break;
    case 3:
        printf("Hafiza Karmasikligi: O(n^3) %cn^3",matrisdeger);
        break;
    }
}

int sayma=-1;

void hafizaKontrol(char dizi[],char kelime[],char karakter, int p)
{

    if(KelimeAra(dizi,kelime)==1 && strchr(dizi,';')!= NULL )
    {
        sayma++;

        for(int i= 0; i<80; i++)
        {
            if(dizi[i]=='[')
            {
                kalinparantez[sayma]+=1;
                matrisdeger=karakter;
            }


            else if(dizi[i]==',')
            {
                hafizacounter+=p;
            }
        }
        if(kalinparantez[sayma]>=1)
        {
            hafizacounter-=p;
        }
        hafizacounter+=p;
    }


}

void HafizaKarmasikligi(char dizi[])
{
    char kelime1[]="int ";
    char kelime2[]="double ";
    char kelime3[]="float ";
    char kelime4[]="char ";

    hafizaKontrol(dizi,kelime1,'4',4);
    hafizaKontrol(dizi,kelime2,'8',8);
    hafizaKontrol(dizi,kelime3,'8',8);
    hafizaKontrol(dizi,kelime4,' ',1);

}

//option 3 :
void gecen_sure_yazdir(int x){
    printf("\n\n");
    if(logn_sayaci>0)
    {
        x=x-logn_sayaci;
        switch(x)
        {
        case 0:
            printf("logn^%dT\n",logn_sayaci);
            break;

        case 1:
            printf("n*logn^%dT\n",logn_sayaci);
              break;

        default :
            printf("n^%d*logn^%dT\n",x,logn_sayaci);
              break;
        }
        }
        else
        {
            switch(x)
            {
            case 0:
                printf("O(1)T\n");
                break;

            case 1:
                printf("O(n)T\n");
                  break;
            default :
                printf("O(n^%dT)\n",x);
            }

        }

}
void show_time_of_execution()
{
    clock_t start = clock();
    // paste the piece of code
    clock_t endTime = clock();
    //printf("su kadar sure gecti  : %f\n", (double)(endTime - start) / CLOCKS_PER_SEC);
}

int main()
{
    FILE *file;
    file = fopen("code.txt", "r");
    if (file == NULL)
    {
        printf("Dosya acilamadi\n");
        exit(1);
    }
    else
    {
        printf("Dosya basariyla acildi\n");
    }

    int a=1;
    char dizi[100];
    do
    {
        //printf("adim: %d\n",a);
        a++;
        fgets(dizi, 80, file); // fgets() fonksiyonu ile dosyadan satir okuma
        printf(dizi);
        ZamanKarmasikligi(dizi);
        HafizaKarmasikligi(dizi);
    }
    while (!feof(file));


    if (dizi == '\0') // dizinin içerisi boþ ise bunu bildirmemiz gerektiði istendi.
    {
        printf("Dosyanizda herhangi bir kod bloguna rastlanmadi.\n");
        exit(-1);
    }
    else
    {
        //printf("Dosyanin içerisinde þöyle bir kod blogu bulundu.\n");
        // printf("%s\n", dizi);
    }


    int option;

    do
    {
        printf("\nChoose an option below :\n");
        printf("click 1 to see complexity time. \n");
        printf("click 2 to see complexity space. \n");
        printf("click 3 to see the execution time of code. \n");
        printf("click 0 to exit. \n");
        scanf("%d", &option);

        switch (option)
        {
        case 1:
            zamanYazdir(BuyukBul(counter));
            break;

        case 2:
            hafizaYazdir(BuyukBul(kalinparantez));
            printf(" + %d\n",hafizacounter);
            break;

        case 3:
            show_time_of_execution();
                gecen_sure_yazdir(BuyukBul(kalinparantez));
            break;

        default:
            break;
        }
    }
    while (option != 0);

    return 0;
}


