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

collections=db["laptop_laptop"]


for i in collections.find():
    print(i)