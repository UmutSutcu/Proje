from re import A
from numpy import append
import requests
from bs4 import BeautifulSoup
import collections
import pymongo
from pymongo import MongoClient
#vatan-n11
sifre="kocaeli41"

cluster=MongoClient("mongodb+srv://Umut:"+sifre+"@cluster0.wblcaic.mongodb.net/?retryWrites=true&w=majority")


db=cluster["database"]

collections=db["test_n11"]
collections2=db["vatan"]
collections3=db["deneme_eşit"]

a=collections.find_one()
count=0
count2=-1
print("basladi")
list=[]
for j in collections2.find({},{"_id":0,"Model":1,"adres":1}):
    print(j)


for i in collections.find({},{"_id":0}):
    count2+=1
    for j in collections2.find({},{"_id":0,"Model":1,"adres":1}):
        if(i["Model"]!=""):
            if(j["Model"] in i["Model"]):
                data={"_id":count,"Marka":i["Marka"],"n11_adres":i["adres"],"trendyol_adres":"Bulunamadı","vatan_adren":j["adres"],"isim":j["isim"],
                "fiyat":i["fiyat"],"Cpu":i["Cpu"],"Cpu_cekirdek":i["Cpu_cekirdek"],
                "Disk_Tur":i["Disk_Tur"],"Bellek_Tur":i["Bellek_Tur"],"Ekran_Karti_Model":i["Ekran_Karti_Model"],"Bellek_Kapasite":i["Bellek_Kapasite"],"Cpu_Model":i["Cpu_Model"],
                "Disk_Kapasite":i["Disk_Kapasite"],"Ekran_Boyut":i["Ekran_Boyut"]}
                collections3.insert_one(data)
                print(i["Model"])
                print(str(count)+"\t"+i["Model"]+"\t==\t"+j["Model"]+""+str(count2))
                print("evraka")
                print(j["adres"])
                list.append(j["adres"])
                count+=1

print(count)
print("bitti")