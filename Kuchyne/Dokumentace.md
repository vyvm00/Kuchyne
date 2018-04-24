# Tým "Muži v ohni"   - Aplikace Kuchyně
* Bangova Robina
* Břenek Radim
* Koutecký Oldřich
* Vyvadil Martin
## Popis
Aplikace slouží pro správu receptů, potřebných surovin, skladových zásob, plánování jídel a nákupů surovin.
## Aktéři
*	Správce, má za úkol správu skladu, plánovaní jídel, kalkulace surovin, nákup surovin a evidenci receptu - zobrazení, správu a zrušení receptu. 
*	Uživatel, smí zobrazodat informace
## Vstupní podmínky:
* software 
* hardware
* správný chod aplikace
## Tok:
1.	Správce naplánuje jídla a následně přidá recepty. 
2.	Jídla se zkladají se surovin. 
3.	Sklad obsahuje suroviny. 
4.	Suroviny se dodávají na sklad. 
5.	Sklad je spravovaný správcem. 
6.	Správce spravuje recepty. 

Pro vytvoření receptu je nutná kalkulace surovin a další krok, nákup surovin. Tyto akce jsou zajišťovány správou surovin, plánovaním jídel. Úkoly má na starosti správce. V dalším kroku se vytvářejí recepty. Je potřeba zkontrolovat, či suroviny byli správně naplánované. Uživatel, si pak recepty může zobrazit. Jestli nastane chyba, je možné správou zrušit recept. Je potřeba zkontrolovat, zda suroviny byly správně naplánované. Uživatel si pak recepty může zobrazit. Pokud nastane chyba, je možné správou zrušit recept.
*	Nákup surovin 
*	Plánovaní jídal 
*	Plánování surovin 
*	Správa receptů 
*	Správa skladu 
*	vytváření receptů 
*	Zobrazení receptů 
*	Zrušit recepty 
## Podmínky pro dokončení:
Propojení s datbází 

# Konvence pro psaní a odevzdávání programů v Javě
## 1. Pojmenovávání tříd, proměnných, metod, ...
1.1 Pro identifikátory (tříd, metod, proměnných, ...) používejte jména popisující jejich význam. Vyhýbejte se zkratkám. Jména by neměla být krátká (minimálně 3 znaky) ani příliš dlouhá (do 16 znaků).

1.2 Jména tříd začínají velkým písmenem. Pokud se použije více slov, všechna začínají velkým písmenem. Pro označení třídy se obvykle používá podstatné jména v jednotném čísle, které bývá doplněno o přívlastky (např. třídy Student, SeznamStudentu).

1.3 Jména metod, proměnných, formálních parametrů metod začínají malým písmenem. Pokud jméno obsahuje více slov, druhé a další začínají velkým písmenem.

1.4 Názvy konstant se píší celé velkými písmeny, v případě více slov se jednotlivá slova oddělují znakem podtržení, např. MAXIMALNI_POCET.

## 2. Formátování
2.1 Na jednom řádku by měl být jeden příkaz, deklarace jedné proměnné.

2.2 Obsah bloku odsaďte vždy o 3 či 4 mezery. V rámci jednoho bloku by měly být všechny příkazy odsazeny stejně.

2.2 Otevírací závorka bloku je obvykle na konci řádku, uzavírací samostatná na řádku. Např.

     public static void main(String[] args) {
         int scitanec1 = 5;
         int scitanec2 = 2;
         System.out.println("Soucet je: " + (scitanec1 + scitanec2));
     }

     while (''podmínka'') {
 	''příkazy''
     }

     if (''podmínka'') {
 	''příkazy''
     }
     else {
 	''příkazy''
     }
2.3 V řídících strukturách vždy používejte složené závorky pro bloky a to i v případě, že v bloku je pouze jeden příkaz.

2.4 Používejte mezeru před otevírací závorkou a okolo operátorů.

## 3. Dokumentace, komentáře
3.1 Na začátku každé třídy uveďte:

- stručný popis třídy, 
- autora či autory, 
- označení verze (pořadové číslo či datum poslední změny).

Příklad:

 /*
   Třída SeznamAkci - obsahuje seznam přípustných příkazů adventury.
   Používá se pro rozpoznávání jednotlivých příkazů.
 
   Tato třída je součástí jednoduché textové hry.
 
 @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 @version    4.0
 @created    září 2006
 */
3.2 Před každou metodou by měl být její popis doplněný o popis významu jednotlivých parametrů a návratových hodnot. Příklad:

 /*
  Metoda hledá nejvyšší hodnotu (maximum) v poli celých čísel.
 
 @param pole Pole celých čísel, které se bude prohledávat
 @return Vrací celé číslo s nejvyšší hodnotou
 */
3.3 Popisy tříd a metod by měly být ve formátu vhodném pro javadoc.

3.4 Vlastní kód doplňte o komentář pouze v nezbytných případech. Předpokládejte, že čtenář zdrojového textu zná Javu.

Pro kontrolu konvencí můžete použít program PMD.
## Nákresy oken aplikace
![nákresy oken aplikace, GUI](https://raw.githubusercontent.com/kouo00/obrazky/master/nakres.png)
## UseCase Diagram
![useCase diagram](https://raw.githubusercontent.com/kouo00/obrazky/master/useCase.png)
## Class Diagram
![class diagram](https://raw.githubusercontent.com/kouo00/obrazky/master/classDiagram.png)
## Konceptuální model databáze
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/database.png)
## Scénáře
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/1.png)
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/2.png)
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/3.png)
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/4.png)
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/5png)
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/6.png)
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/7.png)
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/8.png)
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/9.png)
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/10.png)
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/11.png)