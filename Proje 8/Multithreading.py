import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import threading
from concurrent.futures import ThreadPoolExecutor
import time
import contextlib
from concurrent.futures import ProcessPoolExecutor

from typing import Generator
from tkinter import *

data = pd.read_csv(
    "C:\\Users\\umuts\\OneDrive\\Masaüstü\\Yeni klasör\\ders\\Python\\kadwir akın\\rows2.csv")
data = data.drop('Unnamed: 0', axis=1)

data = data.dropna()

# print(data.shape)
data.info()
# print(data.head())

root = Tk()

zaman_list = []

def raise_frame(frame):
    frame.tkraise()
    

window = Frame(root)
window2 = Frame(root)

for frame in (window, window2,):
    frame.grid(row=0, column=0, sticky='news')


root.geometry('400x400')


def Giris():
    lb1 = Label(window2, text="Kayıt: ")
    lb1.grid(column=0, row=0)
    kayit = Entry(window2)
    kayit.grid(column=1,row=0)
    global isim
    isim = kayit.get()
    lb2 = Label(window2, text="Thread Sayisi: ")
    lb2.grid(column=0, row=1)
    j = Entry(window2)
    j.grid(column=1,row=1)
    global thread_sayisi
    thread_sayisi = j.get()
    Button(window2, text='Go to frame 1', command=lambda:raise_frame(window)).pack()


@contextlib.contextmanager
def time_it(what: str) -> Generator[None, None, None]:
    t0 = time.monotonic()
    try:
        yield
    finally:
        temp = f'{what} == {time.monotonic() - t0}'
        zaman_list.append(temp)
        print(temp)



oran_list = []
birinci_list = []
ikinci_list = []


def dongu(a, b, isim, Benzerlik):
    with time_it('thread'):
        count = 0
        for i in range(a, b):
            ilist = data[isim][i].split(" ")
            for k in range(a, b):
                count += 1
                klist = data[isim][k].split(" ")
                if (len(ilist) > len(klist)):
                    if(len(list(set(ilist) & set(klist)))/len(ilist)*100 > Benzerlik ):
                        oran_list.append(len(list(set(ilist) & set(klist)))/len(ilist)*100)
                        birinci_list.append(ilist)
                        ikinci_list.append(klist)
                elif (len(ilist) < len(klist)):
                    if(len(list(set(ilist) & set(klist)))/len(klist)*100 > Benzerlik ):
                        oran_list.append(len(list(set(ilist) & set(klist)))/len(klist)*100)
                        birinci_list.append(ilist)
                        ikinci_list.append(klist)
                else:
                    if(len(list(set(ilist) & set(klist)))/len(ilist)*100 > Benzerlik ):
                        oran_list.append(len(list(set(ilist) & set(klist)))/len(ilist)*100)
                        birinci_list.append(ilist)
                        ikinci_list.append(klist)


def com_dongu(a, b, isim, Benzerlik ,id):
    with time_it('thread'):               
        data['Complaint ID'] 
        for i in range(a, b):
            if(data['Complaint ID'][i]==id):  
                ilist = data[isim][i].split(" ")
                for k in range(a, b):
                    klist = data[isim][k].split(" ")
                    if (len(ilist) > len(klist)):
                        if(len(list(set(ilist) & set(klist)))/len(ilist)*100 > Benzerlik ):
                            oran_list.append(len(list(set(ilist) & set(klist)))/len(ilist)*100)
                            birinci_list.append(ilist)
                            ikinci_list.append(klist)
                    elif (len(ilist) < len(klist)):
                        if(len(list(set(ilist) & set(klist)))/len(klist)*100 > Benzerlik ):
                            oran_list.append(len(list(set(ilist) & set(klist)))/len(klist)*100)
                            birinci_list.append(ilist)
                            ikinci_list.append(klist)
                    else:
                        if(len(list(set(ilist) & set(klist)))/len(ilist)*100 > Benzerlik ):
                            oran_list.append(len(list(set(ilist) & set(klist)))/len(ilist)*100)
                            birinci_list.append(ilist)
                            ikinci_list.append(klist)



def BilgiEkrani():
    y = 1
    for i in range(len(zaman_list)):
        lb1 = Label(window, text=zaman_list[i])
        lb1.grid(column=3, row=y)
        y += 1

    
    lb1 = Label(window, text="Benzerlik Oranı")
    lb1.grid(column=0, row=0)
    lb1 = Label(window, text="Kayıt 1")
    lb1.grid(column=1, row=0)
    lb1 = Label(window, text="Kayıt 2")
    lb1.grid(column=2, row=0)
    lb1 = Label(window, text="Thread süreleri")
    lb1.grid(column=3, row=0)

    y = 2
    sayı= len(oran_list)
    for i in range(150):
        lb1 = Label(window, text=oran_list[i])
        lb2 = Label(window, text=birinci_list[i])
        lb3 = Label(window, text=ikinci_list[i])
        lb1.grid(column=0, row=y)
        lb2.grid(column=1, row=y)
        lb3.grid(column=2, row=y)
        y += 1



def com_Multithread(thread_sayisi,isim,Benzerlik,id):
    with time_it('main Thread'):
        a = 100
        b = 0
        with ThreadPoolExecutor(thread_sayisi) as ex:
            for _ in range(10):
                b += a
                x = b-a
                if(id==0):
                    tanim = [x, b, isim, Benzerlik]
                    ex.submit(dongu, *tanim)
                else:  
                    tanim = [x, b, isim, Benzerlik,id]
                    ex.submit(com_dongu, *tanim)
    
    raise_frame(window)
    BilgiEkrani()
    

def arayuz():
    lb1 = Label(window2, text="Kayıt: ")
    lb1.grid(column=0, row=0)
    kayit = Entry(window2)
    kayit.grid(column=1,row=0)
    lb2 = Label(window2, text="Thread Sayisi: ")
    lb2.grid(column=0, row=1)
    j = Entry(window2)
    j.grid(column=1,row=1)
    lb2 = Label(window2, text="Benzerlik Oranı: ")
    lb2.grid(column=0, row=2)
    k = Entry(window2)
    k.grid(column=1,row=2)
    lb3 = Label(window2, text="Complaint Id: ")
    lb3.grid(column=0, row=3)
    z = Entry(window2)
    z.grid(column=1,row=3)

    Button(window2, text='Onayla', command=lambda:com_Multithread(int(j.get()),kayit.get(),int(k.get()),int(z.get()))).grid(column=0, row=4)
        

def main():            
    raise_frame(window2)
    arayuz()   
    window.mainloop()


if __name__ == '__main__':
    main()