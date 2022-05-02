#include "LiquidCrystal.h"
#include "Keypad.h"
#define sifre_uzunlugu 5

int keypadLedGreen = 31;
int keypadLedRed  = 32 ;

char girilen_sifre[sifre_uzunlugu];
char kontrol_sifre[sifre_uzunlugu] = "4141";
int sifre_count = 0;
//byte master_count = 0;
bool Pass_is_good;
char customKey;

const byte satir = 4;
const byte sutun = 3;

char Keys[satir][sutun] = {
  {'1', '2', '3'},
  {'4', '5', '6'},
  {'7', '8', '9'},
  {'*', '0', '#'}
};

byte satirpin[satir] = {27, 28, 29, 30};
byte sutunpin[sutun] = {24, 25, 26};

Keypad customKeypad = Keypad(makeKeymap(Keys), satirpin, sutunpin, satir, sutun);


const int rs = 2, en = 3, d4 = 4, d5 = 5, d6 = 6, d7 = 7;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);
//global değişkenleri tanımla
float temp;
//hareket global
int pirled = 13;
// #define pirsensor A1
int pirsensor = 53 ;
int pir_sensorval = 0 ;

//fire global
int fire_Buzzer = 11; 
// #define fire_FlamePin A0
int fire_FlamePin = 52 ;
int fire_Flame = LOW; 

//fonksiyonların setup kısmını burada bir fonksiyon ile yaz, karışmasın ve clean code olsun.
//gerekliyse de parametre ekle fonksiyonlara.


void lcd_setup() {
  Serial.begin(9600) ;
  analogReference(INTERNAL1V1);
  lcd.begin(16, 2);
  lcd.setCursor(0, 0);
  lcd.print("Umut SUTCU 200202038   ");
  lcd.setCursor(0, 1);
  lcd.print("Omer ARAN  190202012    ");
  delay (1000);
  lcd.clear();
}

void lm35_setup() {
  pinMode(A0, INPUT);
  Serial.begin(9600);
}
void fire_alarm_setup() {

  //pin,led tanımla
  pinMode(fire_Buzzer, OUTPUT);
  pinMode(fire_FlamePin, INPUT);
  Serial.begin(9600);
}

void pir_sensor_setup() {
  //sensor,pin,inp,out tanımla
  pinMode(pirled, OUTPUT);
  pinMode(pirsensor, INPUT);
  Serial.begin(9600);
}



void keypad_setup() {
  //input_password.reserve(32);
  analogReference(INTERNAL1V1);
  pinMode(keypadLedGreen, OUTPUT);
  pinMode(keypadLedRed, OUTPUT);
  Serial.begin(9600);

}




void setup() {
  // put your setup code here, to run once:
  lm35_setup();
  fire_alarm_setup();
  pir_sensor_setup();
  keypad_setup();
  lcd_setup();

}


void lcd_loop() {
  lcd.clear();                                                   //temp = temp * 0.48828125;  //temp=temp*(5.0/1023.0)*100;
  temp = analogRead(A0) / 9.31;
  lcd.print("Sicaklik: ");
  lcd.print(temp);
  lcd.println("*C");
  if (temp > 30) {
    lcd.setCursor(0, 1);
    lcd.print("Sicaklik yukseldi");
  }
  if (temp < 20) {
    lcd.setCursor(0, 1);
    lcd.print("Sicaklik dustu");
  }
  delay(300);

}
void fire_alarm_loop() {
  //pin,led tanımla
  fire_Flame = digitalRead(fire_FlamePin);
  if (fire_Flame == HIGH)
  {
    digitalWrite(fire_Buzzer, HIGH);
    delay(2000);
    digitalWrite(fire_Buzzer, LOW);
  }
  else
  {
    digitalWrite(fire_Buzzer, LOW);
  }

}

void pir_sensor_loop() {
  //sensor,pin,inp,out tanımla
  pir_sensorval = digitalRead(pirsensor);
  Serial.println(pir_sensorval);

  if (pir_sensorval == HIGH) {
    digitalWrite(pirled, HIGH);
    delay(1500);
    digitalWrite(pirled, LOW);
  }
  else {
    digitalWrite(pirled, LOW);
    // delay(500);
  }
}

int anahtar = 0;

void keypad_loop() {
  //lcd.setCursor(0,0);
  //lcd.print("Sifre:");

  customKey = customKeypad.getKey();
  if (customKey) {
    girilen_sifre[sifre_count] = customKey;
    // lcd.setCursor(data_count,1);
    //lcd.print(Data[data_count]);
    sifre_count++;
  }

  if (sifre_count == sifre_uzunlugu - 1) {
    sifre_count = 0;
    delay(100);
    //lcd.clear();

    if (strcmp(girilen_sifre, kontrol_sifre) == 0) {
      // lcd.print("Dogru");
      digitalWrite(keypadLedGreen, HIGH);
      delay(500);
      digitalWrite(keypadLedGreen, LOW);
      anahtar = 1;

    }
    else {
      digitalWrite(keypadLedRed, HIGH);
      //lcd.print("Yanlis");
      delay(500);
      digitalWrite(keypadLedRed, LOW);
    }

   // lcd.clear();

  }

}



void loop() {
  // put your main code here, to run repeatedly:
  keypad_loop();
  if (anahtar == 1) {
    fire_alarm_loop();
    pir_sensor_loop();
    lcd_loop();
  }
  //fire_alarm_loop();
  //pir_sensor_loop();
  //lcd_loop();

}
