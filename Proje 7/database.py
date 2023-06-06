from re import A
from numpy import append
import requests
from bs4 import BeautifulSoup
import collections
import pymongo
from pymongo import MongoClient
#trendyol-n11
sifre="kocaeli41"

cluster=MongoClient("mongodb+srv://Umut:"+sifre+"@cluster0.wblcaic.mongodb.net/?retryWrites=true&w=majority")


db=cluster["database"]

collections=db["test_n11"]
collections2=db["test_trendyol"]
collections3=db["Eşit6"]
collections4=db["Resim"]


a=collections.find_one()
count=0
count2=-1
say=0
print("basladi") 
list=[]
resim_list=[]
for i in collections4.find({},{"_id":0,"adres":1}):
    resim_list.append(i["adres"])


for i in collections.find({},{"_id":0}):
    count2+=1
    for j in collections2.find({},{"_id":0,"isim":1,"adres":1,"fiyat":1}):
        say+=1
        if(i["Model"]!=""):
            if(i["Model"] in j["isim"]):
                data={"id":count,"Marka":i["Marka"],"Model":i["Model"],"n11_adres":i["adres"],"trendyol_adres":j["adres"],"vatan_adren":"Bulunamadı","isim":j["isim"],
                "n11_fiyat":i["fiyat"],"trendyol_fiyat":j["fiyat"],"vatan_fiyat":"Bulanamadı","Cpu":i["Cpu"],"Cpu_cekirdek":i["Cpu_cekirdek"],
                "Disk_Tur":i["Disk_Tur"],"Bellek_Tur":i["Bellek_Tur"],"Ekran_Karti_Model":i["Ekran_Karti_Model"],"Bellek_Kapasite":i["Bellek_Kapasite"],"Cpu_Model":i["Cpu_Model"],
                "Disk_Kapasite":i["Disk_Kapasite"],"Ekran_Boyut":i["Ekran_Boyut"],"resim_linki":resim_list[count]}
                collections3.insert_one(data)
                print(i["Model"])
                print(str(count)+"\t"+i["Model"]+"\t==\t"+j["isim"]+""+str(count2))
                print("evraka")
                print(j["adres"])
                list.append(j["adres"])
                count+=1

print(count)
print("bitti")
print(say) 