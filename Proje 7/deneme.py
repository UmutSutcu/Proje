from re import A
from numpy import append
import requests
from bs4 import BeautifulSoup
import collections
import pymongo
from pymongo import MongoClient

sifre="kocaeli41"

cluster=MongoClient("mongodb+srv://Umut:"+sifre+"@cluster0.wblcaic.mongodb.net/?retryWrites=true&w=majority")


db=cluster["database"]

collections=db["kadir"]

x=0
isim_listesi =[]
fiyat_listesi =[]
islemci_hizi_listesi =[]
urun_link_listesi=[]
islemci_cekirdek_sayi_listesi=[]
disk_turu_listesi=[]
bellek_turu_listesi=[]
ekran_karti_modeli_listesi=[]
bellek_kapasitesi_listesi=[]
islemci_modeli_listesi=[]
disk_kapasitesi_listesi=[]
ekran_boyutu_listesi=[]
model_listesi=[]
marka_listesi=[]


siteUrl="https://www.trendyol.com/sr?q=laptop&qt=laptop&st=laptop&os=1&pi="

for i in range(1,2):
    new=siteUrl+str(i)
    sayfacevabi=requests.get(new)
    print(new)
    print(sayfacevabi.status_code)
    if sayfacevabi.status_code==200:
        htmlicerik=sayfacevabi.content
        soup=BeautifulSoup(htmlicerik,"html.parser")
        for icerik in soup.find_all("div",{"class":"p-card-chldrn-cntnr card-border"}):
            bilgisayarlinki=icerik.find("a")
            yenilink=bilgisayarlinki.get("href")
            yenilink="https://www.trendyol.com"+yenilink
            urun_link_listesi.append(yenilink)
            x=x+1
            cevap=requests.get(yenilink)
            print(yenilink)
            if cevap.status_code==200:
                yenicerik=cevap.content
                soup2=BeautifulSoup(yenicerik,"html.parser")
                for don in soup2.find_all("h1",{"class":"pr-new-br"}):
                    isim_listesi.append(don.text)
                for don in soup2.find_all("div",{"product-price-container"}):
                    fiyat_listesi.append(don.find("span",{"class":"prc-dsc"}).text)
                for don in soup2.find_all("h1",{"class":"pr-new-br"}):
                    if don.find("a"):
                        marka_listesi.append(don.find("a").text)
                    else:
                        marka_listesi.append("Belirtilmemis")                    
                for don in soup2.find_all("li",{"class":"detail-attr-item"}) :
                    if don.find("span").text=="İşlemci Tipi":
                        temp=don.find("b").text
                        if don.find("b").text=="Apple":
                            islemci_modeli_listesi.append(don.find("b").text)
                    if don.find("span").text=="İşlemci Modeli":
                        temp=temp+"-"+don.find("b").text
                        islemci_modeli_listesi.append(temp)
                    if don.find("span").text=="Temel İşlemci Hızı (GHz)":
                        islemci_hizi_listesi.append(don.find("b").text)
                    if don.find("span").text=="İşlemci Çekirdek Sayısı":
                        islemci_cekirdek_sayi_listesi.append(don.find("b").text)
                    if don.find("span").text=="SSD Kapasitesi":
                        temp2="SSD"
                        disk_turu_listesi.append(temp2)
                        disk_kapasitesi_listesi.append(don.find("b").text)
                    if don.find("span").text=="Ram (Sistem Belleği) Tipi":
                        bellek_turu_listesi.append(don.find("b").text)
                    if don.find("span").text=="Ekran Kartı":
                        ekran_karti_modeli_listesi.append(don.find("b").text)
                    if don.find("span").text=="Ram (Sistem Belleği)":
                        bellek_kapasitesi_listesi.append(don.find("b").text)
                    if don.find("span").text=="Ekran Boyutu":
                        ekran_boyutu_listesi.append(don.find("b").text)
       

                 
print(x)      


for i in range(x-50):
    data={"_id":i,"Marka":marka_listesi[i],"adres":urun_link_listesi[i],"isim":isim_listesi[i],
          "fiyat":fiyat_listesi[i],"Cpu":islemci_hizi_listesi[i],"Cpu_cekirdek":islemci_cekirdek_sayi_listesi[i],
          "Disk_Tur":disk_turu_listesi[i],"Bellek_Tur":bellek_turu_listesi[i],"Ekran_Karti_Model":ekran_karti_modeli_listesi[i],"Bellek_Kapasite":bellek_kapasitesi_listesi[i],"Cpu_Model":islemci_modeli_listesi[i],
          "Disk_Kapasite":disk_kapasitesi_listesi[i],"Ekran_Boyut":ekran_boyutu_listesi[i]}
    collections.insert_one(data)

print("işlem sonlandı")