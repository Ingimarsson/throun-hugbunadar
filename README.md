# Þróun Hugbúnaðar

[![Build Status](https://travis-ci.org/Ingimarsson/throun-hugbunadar.svg?branch=master)](https://travis-ci.org/Ingimarsson/throun-hugbunadar)

Flugleitarvél. Vor 2020.

## Hvernig á að nota git

### Uppsetning

1. Búið til ykkar eigið fork af verkefninu
2. Setjið upp git á tölvunni ykkar
3. Sækið verkefnið í tölvuna ykkar með git clone
4. Setjið upp ykkar eigið branch (master er bara fyrir tilbúinn kóða)

Skipanirnar eru eftirfarandi

    git clone https://github.com/<username>/throun-hugbunadar.git
    git branch <username>
    git checkout <username>

Líka hægt að búa til nýtt branch fyrir hvert task t.d. patch-xx

### Senda inn kóða (commit)

Til að sjá hverju þið eruð búin að breyta skrifið

    git status

Skrár sem þið ætlið að senda inn ættu að vera grænar, ef þær eru rauðar þarf að bæta þeim við með

    git add src/File.java

Þá er hægt að committa

    git commit -m "lagadi ehv unit test"
    git push -u origin <branch-name>

### Sameina kóða (merge)

Eftir commit er kóðinn ykkar kominn á github, í ykkar branch á ykkar fork. Þá þarf að senda pull request til að fá hann í master branchið á sameiginlega repository-inu. Það ætti að vera `send pull request` takki á GitHub.

### Sækja nýjasta kóðann (fetch)

Til að fá nýjasta kóðann þarf að keyra

    git merge upstream/master

