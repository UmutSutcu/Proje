from django.shortcuts import render

# Create your views here.

def home(request):
    return render(request,"index.html")

def movies(request):
    return render(request,"movies.html")