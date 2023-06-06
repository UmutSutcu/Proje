from itertools import count
from traceback import print_tb
import requests
from bs4 import BeautifulSoup
import collections
import pymongo
from pymongo import MongoClient
sifre="kocaeli41"

cluster=MongoClient("mongodb+srv://Umut:"+sifre+"@cluster0.wblcaic.mongodb.net/?retryWrites=true&w=majority")

db=cluster["database"]



x=0
link_listesi =[]
fiyat_listesi =[]
islemci_hizi_listesi =[]
collections=db["Eşit5"]
collections2=db["Resim"]
list=[]

for i in collections.find({},{"_id":0,"n11_adres":1}):
    list.append(i["n11_adres"])
    
    
siteUrl="https://www.n11.com/bilgisayar/dizustu-bilgisayar?pg="
print("başladı")
count=0
for i in list:
    print(i)
    cevap=requests.get(i)
    print(cevap.status_code)
    if cevap.status_code==200:
        yenicerik=cevap.content
        soup=BeautifulSoup(yenicerik,"html.parser")
        for don in soup.find_all("div",{"class":"imgObj"}):
            temp=don.find("a")
            print(temp.get("href"))
            data={"_id":count,"adres":temp.get("href")}
            collections2.insert_one(data)
            count+=1
            
            
            
            
            
print("bitti")
"""
#sayfacevabi=requests.get(siteUrl)
for i in range(3):
    new=siteUrl+str(i)
    sayfacevabi=requests.get(new)
    print(new)
    print(sayfacevabi.status_code)
    if sayfacevabi.status_code==200:
        htmlicerik=sayfacevabi.content
        soup=BeautifulSoup(htmlicerik,"html.parser")
        for icerik in soup.find_all("div",{"class":"pro"}):
            bilgisayarlinki=icerik.find("a")
            yenilink=bilgisayarlinki.get("href")
            cevap=requests.get(yenilink)
            print(bilgisayarlinki.get("href"))
            x=x+1
            if cevap.status_code==200:
                yenicerik=cevap.content
                soup2=BeautifulSoup(yenicerik,"html.parser")
                for don in soup2.find_all("h1",{"class":"proName"}):
                    isim_listesi.append(don.text)
                    #data={"name":don.text}
                    #collections.insert_one(data)
                    #print(don.text)
                for don in soup2.find_all("div",{"class":"unf-p-summary-price"}):
                    fiyat_listesi.append(don.text)
                    #data={"_id":x,"price":don.text}
                    #collections.insert_one(data)
                    #print(don.text)
                for don in soup2.find_all("li",{"class":"unf-prop-list-item"}) :
                    if don.find("p",{"class":"unf-prop-list-title"}).text == "İşlemci Hızı":
                        islemci_hizi_listesi.append(don.find("p",{"class":"unf-prop-list-prop"}).text)
                        #print("buldum")
                    #data={"_id":x,don.find("p",{"class":"unf-prop-list-title"}.text:don.find("p",{"class":"unf-prop-list-prop"}).text}
                    #collections.insert_one(data)


for i in range(x):
    data={"_id":i,"name":isim_listesi[i],"price":fiyat_listesi[i],"Cpu":islemci_hizi_listesi[i]}
    collections.insert_one(data)
"""
print(x)      
print("işlem sonlandı")
