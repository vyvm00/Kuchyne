# Tým "Muži v ohni"   - Aplikace Kuchyně
## Seznam úkolů a jejich přiřazení členům týmu:
Člen týmu | Návrh | Třídy
--- | --- | ---
Bangová Robina | Popis funkcionality aplikace, Use case diagram, Scénáře | Sklad
Břenek Radim | Use case diagram, Class diagram, Scénáře | Surovina
Oldřich Koutecký | Nákresy GUI, Struktura databáze, Scénáře | Recept
Martin Vyvadil | Class diagram, Struktura databáze, Scénáře | Jídlo

Každý člen týmu tedy bude realizovat minimálně jednu část návrhu a implementovat minimálně jednu třídu.
## Popis:
Aplikace slouží pro správu receptů, potřebných surovin, skladových zásob, plánování jídel a nákupů surovin.
## Aktéři:
*	Správce - má za úkol správu skladových zásob, receptů a surovin, vytváření receptů a surovin, přidávání (plánování) jídla a nákup surovin. Taktéž má možnost zobrazit informace o skladových zásobách, receptech, surovinách a plánovaných jídlech.
*	Uživatel (v praxi kuchař či servírka) – smí pouze zobrazovat informace o skladových zásobách, receptech, surovinách a plánovaných jídlech.
## UseCase Diagram:
![useCase diagram](https://raw.githubusercontent.com/kouo00/obrazky/master/useCase.png)
## Scénáře:
Všechny scénáře k UseCase diagramu jsou dostupné na https://github.com/vyvm00/Kuchyne/blob/navrh/Kuchyne/Scenare.md
## Tok:
1.	Správce vytvoří/přidá suroviny a z nich následně sestaví recepty
2.	Z receptů může správce vytvořit jídla
3.	Suroviny se nachází na skladě
4.	V případě nedostatku surovin je správce nakupuje na sklad
5.	Správce spravuje sklad, suroviny a recepty

Pro vytvoření jídla z receptu je nutná kalkulace surovin a v případě jejich nedostatku následuje další krok, kterým je nákup potřebných surovin. Tento úkol má na starosti správce. 

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

## Class Diagram:
![class diagram](https://raw.githubusercontent.com/kouo00/obrazky/master/classDiagram.png)

## Struktura databáze (fyzický model):
![database](https://raw.githubusercontent.com/kouo00/obrazky/master/database.png)
## Podmínky pro dokončení:
Propojení s databází 

## Nákresy oken aplikace:
Jedná se o náčrty GUI uvedené v následujícím pořadí: 1. Hlavní menu, 2. Přidání jídla, 3. Vytvoření receptu, 4. Správa receptů, 5. Správa skladu, 6. Správa surovin, 7. Nákup surovin na sklad
![nákresy oken aplikace, GUI](https://raw.githubusercontent.com/kouo00/obrazky/master/nakres.png)
## Konvence pro naší týmovou práci:
V naší práci budeme používat konvence dostupné z https://java.vse.cz/4it101/Konvence

## Poznámka: 
Všechny výše zmíněné návrhy v průběhu zpracovávání semestrální práce mohou variovat a výsledný výstup se může v některých oblastech lišit od plánu.
