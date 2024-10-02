# Laboratorul 1 - Introducere
Scrieți un program Java care rezolvă cerințele de mai jos. **NB!** Argumentele în linie de comandă pot fi atât șiruri de caractere cât și numere întregi. Acestea vor fi stabilite folosind ecranul [Run Configuration...](https://www.jetbrains.com/help/idea/run-debug-configuration.html#from-class) din IntelliJ IDEA sau un alt IDE.

1. Afișați numerele prime care se regăsesc între argumentele în linia de comandă a programului. În cazul în care programul este pornit fără furnizarea a cel puțin un argument în linia de comandă, citiți aceste valori prin interfața cu utilizatorul.

2. Afișați numerele pătrate perfecte care se regăsesc între argumentele în linia de comandă a programului. În cazul în care programul este pornit fără furnizarea a cel puțin un argument în linia de comandă, citiți aceste valori prin interfața cu utilizatorul.

3. Afișați cel mai mare divizor comun al tuturor numerelor naturale care se regăsesc între argumentele în linia de comandă a programului. În cazul în care programul este pornit fără furnizarea a cel puțin un argument în linia de comandă, citiți aceste valori prin interfața cu utilizatorul.

4. Afișați cel mai mic multiplu comun al tuturor numerelor naturale care se regăsesc între argumentele în linia de comandă a programului. În cazul în care programul este pornit fără furnizarea a cel puțin un argument în linia de comandă, citiți aceste valori prin interfața cu utilizatorul.
   
5. Afișați în ordine crescătoare a lungimii, câte unul pe fiecare rând, toate șirurile de caractere care nu reprezintă numere și care se regăsesc între argumentele în linia de comandă a programului. În cazul în care programul este pornit fără furnizarea a cel puțin un argument în linia de comandă, citiți aceste valori prin interfața cu utilizatorul.

6. Afișați în ordine descrescătoare lexicografică, câte unul pe fiecare rând, toate șirurile de caractere care nu reprezintă numere și care se regăsesc între argumentele în linia de comandă a programului. În cazul în care programul este pornit fără furnizarea a cel puțin un argument în linia de comandă, citiți aceste valori prin interfața cu utilizatorul.

7. Afișați în ordine crescătoare, câte unul pe fiecare rând, toate numerele strict negative care se regăsesc între argumentele în linia de comandă a programului. În cazul în care programul este pornit fără furnizarea a cel puțin un argument în linia de comandă, citiți aceste valori prin interfața cu utilizatorul.

# Exemple 
1. În cazul primei probleme, pentru argumentele `1 4 abcd 3 10 99 11 Java Advanced Programming Methods 45 13` furnizate în linia de comandă a programului, programul va afișa `3 11 13`.
2. În cazul în care la linia de comandă a programului nu este furnizat cel puțin un argument util (ex. la problema 1 se furnizează exclusiv șiruri de caractere ce nu reprezintă numere naturale), **sau** în cazul în care la linia de comandă a programului nu este furnizat nici un argument, programul va porni și va cere utilizatorului să introducă argumentele la linia de comandă a programului, după care programul va calcula și afișa rezultatele pe ecran.

# Cerințe non-funcționale
- Separați implementarea în funcții care acceptă parametri și returnează valori.
- Determinarea valorilor care urmează a fi afișate va fi realizată cu ajutorul unei funcții care primește parametri de intrare și returnează rezultatul. Această funcție va fi separată de funcțiile care citesc date de intrare sau care afișează rezultatul pe ecran.
