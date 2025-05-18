# Projekt i Sigurisë së të Dhënave - Faza3
 ## Menaxhimi i Përdoruesve në një Aplikacion Desktop Windows dhe Ruajtja e Fjalëkalimeve si Salted Hash në një Databazë

 - Ky aplikacion i thjeshtë, i zhvilluar me Java dhe JavaFX, përfaqëson një sistem bazik menaxhimi të përdoruesve që synon të ilustrojë konceptet thelbësore të autentifikimit 
    dhe sigurisë në një mjedis desktop. Ai ofron funksionalitete kyçe si regjistrimi i përdoruesve, autentifikimi përmes kredencialeve të mbrojtura dhe ruajtja e tyre në një bazë të dhënash të strukturuar. 
 - Përdorimi i një baze të dhënash SQL lejon ruajtjen e të dhënave në mënyrë të qëndrueshme, të organizuar dhe të shkallëzueshme. Të dhënat e ndjeshme, si fjalëkalimet, nuk ruhen në formë të pastër 
  (plaintext), por përpunohen përmes algoritmit të sigurisë SHA-256, i kombinuar me një "salt" unik për çdo përdorues, duke rritur ndjeshëm sigurinë
 - Ky aplikacion gjithashtu shërben si një shembull praktik për studentët dhe zhvilluesit e rinj që duan të kuptojnë bazat e menaxhimit të identitetit dhe implementimin e praktikave të sigurta në nivel 
 aplikacioni desktop. Arkitektura modulare, ndarja e logjikës në klasa dhe përdorimi i shërbimeve për ndërveprim me bazën e të dhënave, e bëjnë kodin të lehtë për mirëmbajtje dhe zgjerim në të ardhmen.

---
 ### Funksionalitetet Kryesore:
 - #####  1.Krijimi i një përdoruesi të ri (Sign Up).
    Përdoruesit kanë mundësinë të regjistrohen duke futur një emër përdoruesi unik ( të pa përdorur më parë ) dhe një fjalëkalim:
    Gjenerohet një salt i rastësishëm. Fjalëkalimi bashkohet me salti dhe më pas hash-ohet duke përdorur algoritmin SHA-256.  Hash-i dhe salti ruhen në tabelën e përdoruesve. 
    Ky proces siguron që edhe nëse dy përdorues zgjedhin të njëjtin fjalëkalim, hash-i i ruajtur në bazë do të jetë i ndryshëm për shkak të salt-it të personalizuar.

 - ##### 2. Hyrja në sistem (Sign In).
   Përmes username dhe password-it:
Sistemi kërkon emrin e përdoruesit në databazë , të cilin e merrë salt-in përkatës nga rreshti i përdoruesit, dhe më pas
fjalëkalimi i futur nga përdoruesi kombinohet me salti dhe hash-ohet.
Hash-i i gjeneruar krahasohet me atë të ruajtur në bazën e të dhënave.
Nëse hash-et përputhen, përdoruesi lejohet të hyjë në aplikacion.

 - ##### 3. Ndryshimi i fjalëkalimit:
   Përdoruesi mund të ndryshojë fjalëkalimin e tij duke futur një fjalëkalim të ri. Procesi është i njëjtë:
Gjenerohet një salt i ri , fjalëkalimi i ri hash-ohet me salt-in e ri dhe të dhënat përkatëse në bazën e të dhënave përditësohen.

----
 ### Teknologjitë e Përdorura
    - Java – Gjuhë bazë e programimit
    - JavaFX – Për ndërtimin e ndërfaqes grafike të përdoruesit
    - Scene Builder – Për dizajnimin vizual të paraqitjeve JavaFX (FXML)
    - SQL (PostgreSQL) – Për ruajtjen e të dhënave
    - SHA-256 + Salt – Për hashimin e sigurt të fjalëkalimeve
    - IntelliJ IDEA – Mjedis zhvillimi (IDE)

 ---
 ## User Interface
JavaFX është përdorur për ndërfaqen grafike të përdoruesit (GUI), duke ofruar një përvojë të thjeshtë dhe intuitive. Ndërfaqet që kemi përfshirë janë:
 - Faqja e regjistrimit ( Sign up )
 - Faqja e hyrjes (Sign in )
 - Paneli pas kyçjes ( Hello )
 - Forma për ndryshimin e fjalëkalimit ( Change password )

--- 
 ## Struktura e Projektit
 - ### Model 
    Përmban klasat që përfaqësojnë të dhënat (p.sh. User ). Këto klasa janë pasqyrim i drejtpërdrejtë i tabelave në databazë.
 - ### Repository 
    Përgjegjës për komunikimin direkt me databazën. Këtu ndodhen funksionet për CRUD (Create, Update ).
 - ### Service
    Ndërmjetëson mes controller-it dhe repository-t. Përmban logjikën e biznesit dhe validimet para se të dërgohen ose merren të dhëna nga databaza.
 - ### Controller
    Merr inputin nga përdoruesi përmes ndërfaqes grafike dhe vepron nëpërmjet shtresës Service. Përgjegjës për menaxhimin e ndërveprimit të përdoruesit.
 - ### Views (FXML)
    Skedarët FXML që përshkruajnë paraqitjen vizuale të aplikacionit. Janë dizajnuar me dorë ose përmes Scene Builder.
