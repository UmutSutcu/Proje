import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import re
from nltk.tokenize import sent_tokenize, word_tokenize
from nltk.corpus import stopwords
from pathlib import Path  

data = pd.read_csv("C:\\Users\\umuts\\OneDrive\\Masaüstü\\Yeni klasör\\ders\\Python\\2.Proje\\rows.csv")

print(data.head())
print(data.shape)
data.info()

data = data.drop('Sub-product',axis=1)
data = data.drop('Sub-issue',axis=1)
data = data.drop('Consumer complaint narrative',axis=1)
data = data.drop('Company public response',axis=1)
data = data.drop('Tags',axis=1)
data = data.drop('Consumer consent provided?',axis=1)
data = data.drop('Submitted via',axis=1)
data = data.drop('Consumer disputed?',axis=1)
data = data.drop('Company response to consumer',axis=1)
data = data.drop('Date sent to company',axis=1)
data = data.drop('Timely response?',axis=1)
data = data.drop('Date received',axis=1)

data1 = data.dropna()

data1['Product']=[re.sub('[^\w\s]+', '', s) for s in data1['Product'].tolist()]
data1['Issue']=[re.sub('[^\w\s]+', '', s) for s in data1['Issue'].tolist()]
data1['Company']=[re.sub('[^\w\s]+', '', s) for s in data1['Company'].tolist()]
data1['State']=[re.sub('[^\w\s]+', '', s) for s in data1['State'].tolist()]
data1['ZIP code']=[re.sub('[^\w\s]+', '', s) for s in data1['ZIP code'].tolist()]


print(data1.shape)
data1.info()

print(data1.head())

stopWords = set(stopwords.words('english'))

column_list = ['Product', 'Issue', 'Company']
for k in column_list:
    data1[k] = data1[k].apply(lambda x: ' '.join([word for word in x.split() if word not in (stopWords)]))

print(data1.shape)
data1.info()

print(data1.head())


filepath = Path("C:\\Users\\umuts\\OneDrive\\Masaüstü\\Yeni klasör\\ders\\Python\\2.Proje\\rows2.csv")  
filepath.parent.mkdir(parents=True, exist_ok=True)  
data1.to_csv(filepath)