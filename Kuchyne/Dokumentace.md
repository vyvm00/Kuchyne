# Tým "Muži v ohni"   - Aplikace Kuchyně
## Seznam úkolů a jejich přiřazení členům týmu
Člen týmu | Návrh | Třídy
--- | --- | ---
Bangova Robina | Popis funkcionality aplikace, Use case diagram | Sklad
Břenek Radim | Use case diagram, Class diagram | Surovina
Oldřich Koutecký | Nákresy GUI, Struktura databáze | Recept
Martin Vyvadil | Class diagram, Struktura databáze | Jídlo

Každý člen týmu tedy bude realizovat minimálně jednu část návrhu a implementovat minimálně jednu třídu.
## Popis
Aplikace slouží pro správu receptů, potřebných surovin, skladových zásob, plánování jídel a nákupů surovin.
## Aktéři
*	Správce - má za úkol správu skladových zásob, receptů a surovin, vytváření receptů a surovin, přidávání (plánování) jídla a nákup surovin. Taktéž má možnost zobrazit informace o skladových zásobách, receptech, surovinách a plánovaných jídlech.
*	Uživatel (v praxi kuchař či servírka) – smí pouze zobrazovat informace o skladových zásobách, receptech, surovinách a plánovaných jídlech.
## UseCase Diagram
![useCase diagram](https://raw.githubusercontent.com/kouo00/obrazky/master/useCase.png)
## Scénáře
![1](https://raw.githubusercontent.com/kouo00/obrazky/master/1.png)
![2](https://raw.githubusercontent.com/kouo00/obrazky/master/2.png)
![3](https://raw.githubusercontent.com/kouo00/obrazky/master/3.png)
![4](https://raw.githubusercontent.com/kouo00/obrazky/master/4.png)
![5](https://raw.githubusercontent.com/kouo00/obrazky/master/5png)
![6](https://raw.githubusercontent.com/kouo00/obrazky/master/6.png)
![7](https://raw.githubusercontent.com/kouo00/obrazky/master/7.png)
![8](https://raw.githubusercontent.com/kouo00/obrazky/master/8.png)
![9](https://raw.githubusercontent.com/kouo00/obrazky/master/9.png)
![10](https://raw.githubusercontent.com/kouo00/obrazky/master/10.png)
![11](https://raw.githubusercontent.com/kouo00/obrazky/master/11.png)
## Tok:
1.	Správce vytvoří/přidá suroviny a z nich následně sestaví recepty
2.	Z receptů může správce vytvořit jídla
3.	Suroviny se nachází na skladě
4.	V případě nedostatku surovin je správce nakupuje na sklad
5.	Správce spravuje sklad, suroviny a recepty

Pro vytvoření jídla z receptu je nutná kalkulace surovin a v případě jejich nedostatku následuje další krok, kterým je nákup surovin. Tento úkol má na starosti správce. 

V dalším kroku se vytvářejí recepty. Je potřeba zkontrolovat, či použité suroviny na přípravu receptu se nacházejí v naší databázi a zda je jich dostatek k následnému vytvoření jídla. Uživatel, si pak recepty může zobrazit. Jestli nastane chyba, je možné správou zrušit recept.


*	přidat jídlo
*	spravovat recept
*	spravovat skladové zásoby
*	spravovat suroviny
*	vytvořit recept
*	vytvořit suroviny
*	nakoupit suroviny
*	zobrazit jídla
*	zobradit recepty
*	zobrazit skladové zásoby
*	zobrazit suroviny

## Class Diagram
![class diagram](https://raw.githubusercontent.com/kouo00/obrazky/master/classDiagram.png)

## Struktura databáze(fyzický model)
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/database.png)
## Podmínky pro dokončení:
Propojení s datbází 

## Nákresy oken aplikace
![nákresy oken aplikace, GUI](https://raw.githubusercontent.com/kouo00/obrazky/master/nakres.png)
## Konvence pro psaní a odevzdávání programů v Javě
V naší práci budeme používat konvence dostupné z https://java.vse.cz/4it101/Konvence

## Poznámka: 
Všechny výše zmíněné návrhy v průběhu zpracovávání semestrální práce mohou variovat a výsledný výstup se může v některých oblastech lišit od plánu.
