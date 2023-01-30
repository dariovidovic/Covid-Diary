package com.example.covid_journal

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
private lateinit var database: DatabaseReference


class PollActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poll)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance("https://covid-journal-e4523-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
        val currentUserID = auth.currentUser?.uid.toString()
        var dayCounter = 0


        val temperature = findViewById<EditText>(R.id.enterTemperature)

        val difficultyBreathing = findViewById<RadioGroup>(R.id.difficultyBreathing)
        val mobility = findViewById<RadioGroup>(R.id.mobility)
        val chestPain = findViewById<RadioGroup>(R.id.chestPain)
        val lossOfSense = findViewById<RadioGroup>(R.id.lossOfSense)
        val cough = findViewById<RadioGroup>(R.id.cough)
        val headache = findViewById<RadioGroup>(R.id.headache)
        val soreThroat = findViewById<RadioGroup>(R.id.soreThroat)
        val tiredness = findViewById<RadioGroup>(R.id.tiredness)
        val bodyAches = findViewById<RadioGroup>(R.id.bodyAches)
        val runnyNose = findViewById<RadioGroup>(R.id.runnyNose)
        val nausea = findViewById<RadioGroup>(R.id.nausea)

        database.child(currentUserID).get().addOnSuccessListener {
            dayCounter = it.child("dayCounter").value.toString().toInt()
        }.addOnFailureListener {
            dayCounter = 0
        }


        val goBackFromPoll = findViewById<Button>(R.id.goBackFromPollButton)
        val savePoll = findViewById<Button>(R.id.savePollButton)

        var allAdvices = ""
        var emergencyAdvice = "Pozovite hitnu medicinsku pomoć! Prije odlaska doktoru/zvanja medicinske pomoći obavijestite da možda imate COVID-19.\n\n"

        var headacheAdvices = "Lagana šetnja pomaže pri glavobolji jer smanjiva bol i napetost u mišićima, ublažuje stres te promiče san. Vodite računa da ne pretjerate da ne bi postigli suprotan efekt – još goru glavobolju.\n" +
                "Izbjegavajte prekomjernu konzumaciju kafeina.\n" +
                "Topli tuš djeluje na ublažavanje napetosti mišića. To može Vašu glavobolju učiniti lakšom i kraćom.\n" +
                "Tablete za glavobolju, poput Lupoceta, pomažu u smanjivanju bolova izazvanih glavoboljom.\n" +
                "Održavanje hidratacije i dovoljno sna pomaže u borbi protiv glavobolje.\n\n"

        var coughAdvices = "Ostanite dobro hidrirani. Unos puno tekućine razrjeđuje sluz, što doprinosi iskašljavanju.\n" +
                "Ukoliko vam kašalj otežava spavanje, spavajte s podignutim uzglavljem.\n" +
                "Pijte tople napitke! Osim što zagrijavaju dišne puteve, drže vas hidratiziranim te razgrađuju sluz koju imate u grlui gornjim dišnim putevima.\n" +
                "U prehrani se preporučuju juhe, tople kaše i med.\n" +
                "Ukoliko imate suhi kašalj, probajte lijekove ili prirodne sirupe kako bi vam smanjili ili blokirali refleks kašlja.\n\n"

        var smokerAdvices = "Pušenje uzrokuje probleme s plućima, što može biti veoma opasno jer COVID-19 napada pluća." +
            "Prestanak pušenja u borbi s COVID-19 je najbolje rješenje. \n\n"

        var lossOfSenseAdvice = "Gubitak osjeta njuha/okusa je česti simptom COVID-19. Samoizolirajte se i testirajte se na COVID-19.\n\n"

        var nauseaAdvices = "Jedite blagu hranu jer ona obično ne iritira želudac i pomaže u ublažavanju mučnine.\n" +
                "Pijte puno vode kako biste ostali hidrirani, osobito ako ste povraćali.\n" +
                "Izbjegavajte masnu, začinjenu ili slatku hranu jer ona može pogoršati mučninu.\n" +
                "Izbjegavajte pića s kofeinom jer ona mogu uznemiriti želudac.\n" +
                "Jedite male obroke jer oni omogućavaju vašem želucu da probavi hranu postupno.\n\n"

        var bodyAchesAdvices = "Unos puno tekućine i odmor obično su dovoljni za potpun oporavak od bolova u tijelu.\n"+
                "Uzmite lijek za smanjenje boli u tijelu i mišićima.\n\n"

        var runnyNoseAdvices = "Koristite sprej za nos jer oni pridonose kontrakciji krvnih žila u sluznici nosa i olakšavaju disanje. Nemojte koristiti sprej za nos preko 7 dana bez savjetovanja s liječnikom.\n" +
                "Topli tuš ili udisanje pare pomaže pri lakšem disanju jer inhalacija vodene pare umiruje i otvara nosne prolaze.\n" +
                "Spavanje ili odmaranje s uzdignutom glavom pomaže pri začepljenom nosu.\n\n"

        var tirednessAdvices = "Zdrava rutina spavanja smanjuje iscrpljenost i umor.\n" +
                "Ostanite hidrirani pijući vodu i jedite hranjive namirnice.\n" +
                "Odmor je vrlo važan za vaše tijelo jer se bori protiv infekcije. Opuštanje, disanje i meditacija mogu omogućiti kvalitetan odmor.\n" +
                "Polako dnevno kretanje održava vaše tijelo i pomaže pri cirkulaciji.\n\n"


        var soreThroatAdvices = "Izbjegavajte boravak u blizini ljudi koji puše. Pušenje ili udisanje dima može pogoršati bol u grlu.\n" +
                "Važno je da ostanete dobro hidrirani, stoga pijte puno vode.\n" +
                "Ispiranje grla slanom vodom pomaže pri boli u grlu.\n" +
                "Konzumirajte meku ili rashlađenu hranu, poput sladoleda, te izbjegavajte hranu koja uzrokuje bol prilikom gutanja.\n" +
                "Izbjegavajte alkohol jer on iritira upaljena tkiva grla te dehidrira tijelo.\n\n"

        var temperatureAdvices = "Imate povišenu temperaturu, testirajte se na COVID-19.\n" +
                "Najčešća metoda za snižavanje temperature je uporaba lijekova za snižavanje temperature (Paracetamol, Lupocet...). Prije uzimanja lijekova pazite na preporučeno doziranje, tjelesnu kilažu te uvjete uzimanja tableta.\n" +
                "Najvažnije se dovoljno odmarati. U većini slučajeva temperatura postoji zbog infekcije, stoga je tijelu potreban adekvatan odmor za borbu protiv infekcije.\n" +
                "Dobra hidratacija je dobar način za regulaciju tjelesne temperature. (Sprječava nagli rast temperature)\n" +
                "Također, može biti korisno nositi laganu odjeću, rashladiti kuću i spavati s laganim pokrivačem.\n\n"

        var hivAdvices = "Pobrinite se da imate dulju zalihu svog lijeka protiv HIV-a. Pitajte svog liječnika o dobivanju lijeka poštom.\n" +
                "Razgovarajte sa svojim liječnikom i provjerite jesu li sva vaša cijepljenja ažurirana, uključujući cijepljenja protiv sezonske gripe i bakterijske upale pluća. Ove bolesti koje se mogu spriječiti cijepljenjem pogađaju osobe s HIV-om više od ostalih.\n" +
                "Kada je to moguće, držite se liječničkih termina.\n" +
                "Ako se razbolite, ostanite u kontaktu telefonom ili e-poštom s ljudima koji Vam mogu pomoći.\n" +
                "Ukoliko sumnjate da imate simptome COVID-19, nazovite svog liječnika. Važno je da nastavite uzimati lijek protiv HIV-a kako Vam je propisano jer to će Vam pomoći da Vaš imunološki sustav bude zdrav.\n\n"

        var diabetesAdvices = "Nastavite uzimati svoje lijekove, uključujući inzulin, kao i obično.\n" +
                "Testirajte i pratite razinu šećera u krvi.\n" +
                "Pobrinite se da imate barem 30-dnevnu zalihu lijekova za dijabetes, uključujići inzulin.\n" +
                "Razgovarajte s liječnikom ako imate kakvih nedoumica u vezi dijabetesa i COVID-19.\n\n"

        var weakImmuneSystemAdvices = "Ukoliko sumnjate na simptome COVID-19, testirajte se.\n" +
                "Ako imate simptome COVID-19 ili se ne osjećate dobro, a liječite se od raka ili raka koji utječe na vaš imunološki sustav, obratite se svom liječniku.\n" +
                "Provjerite jeste li primili sva cjepiva za koja imate pravo kako bi smanjili rizik i kako bi simptomi u slučaju bolesti bila blaži.\n" +
                "Nastavite uzimati lijekove i nemojte mijenjati svoj plan liječenja bez razgovora sa svojim liječnikom.\n\n"

        var pregnancyAdvices = "Nazovite Vašeg doktora! On će Vas savjetovati što učiniti i s njime možete razgovarati oko vaših nedoumica!\n" +
                "Blage simptome možete ublažiti odmaranjem i unosom puno tekućine.\n"+
                "Prije uzimanja bilo kojeg lijeka, uključujući i lijekove protiv bolova, provjerite sa svojim ljekarnikom ili liječnikom opće prakse je li prikladan.\n\n"

        var highRiskWorkplaceAdvices = "Radeći na mjestu s većom izloženosti COVID-19, preporučeno je biti u toku s preporučenim dozama protiv COVID-19 kako bi zaštitili sebe i svoje bližnje.\n" +
                "Nosite masku svaki put kada ste u blizini drugih, uključujući i vaš dom.\n" +
                "Pratite znakove i simptome bolesti COVID-19 punih 10 dana nakon posljednjeg izlaganja.\n\n"

        var oldAgeAdvices = "Važno je biti u toku s cjepivima protiv COVID-19.\n" +
                "Pridržavajte se preventivnih mjera za COVID-19.\n" +
                "Pridržavajte se uobičajenih ili zakazanih termina pregleda kod liječnika.\n" +
                "Ukoliko sumnjate da imate simptome COVID-19, izolirajte se.\n" +
                "Razmislite o dostavi namirnica i drugih bitnih stvari.\n" +
                "Izbjegavajte javna okupljanja i putovanja koja nisu nužna.\n" +
                "Održavati dobru higijenu te prakticirati fizičko  distanciranje držeći se 1.5m od drugih ljudi kada je to moguće i izbjegavati kontakt s drugim ljudima.\n\n"

        var downSyndromeAdvices = "Osobe s Downovim sindromom koje imaju nepotpuno razumijevanje svog zdravlja možda će trebati podrška kako bi se izbjegla zaraza ili upravljanje virusom ako ga dobiju.\n" +
                "Pojedinci s Downovim sindromom možda nisu u mogućnosti jasno izraziti svoju bol, nelagodu ili osjećaj uzrujanosti.\n" +
                "Ukoliko primjetite da osoba ima temperaturu, kašalj ili kratkoću daha, treba prijaviti zdravstvenom djelatniku što je prije moguće da se može napraviti rana procjena bolesti.\n" +
                "Ukoliko osoba s Downovim sindromom ima blage simptome, treba ostati kod kuće te nazvati doktora za savjet.\n\n"

        var lungDiseaseAdvices = "Ako imate pogoršanje plućne bolesti ili imate nove simptome koji vas muče – nazovite svog liječnika i zajedno smislite plan.\n" +
                "Nastavite uzimati propisane lijekove prema uputama. Razmotrite mogućnosti naručivanja lijekova putem pošte za.Ako Vam osiguranje dopušta, osigurajte 90-dnevnu zalihu lijekova na recept.\n\n"

        var chronicKidneyOrLiverDisease = "Značajno smanjiti broj ambulantnih pregleda.\n" +
                "Preglede ili konzultacije učiniti na daljinu kad god je to moguće i opravdano.\n" +
                "Planirati vrijeme pregleda, smanjiti vrijeme provedeno u čekaonici, paziti na adekvatan razmak u slučaju da se u čekaonici nalazi više ljudi.\n" +
                "Ograničiti broj osoba u pratnji.\n" +
                "Nastavite uzimati bilo koji lijek kako je propisano.\n" +
                "Nužno je provesti adekvatan epidemiološki probir, te ovisno o nalazu probira, postupiti po naputcima lokalne epidemiološke službe.\n\n"

        var cardiovascularDiseaseAdvices = "Smanjite broj ljudi koji se susreću s pacijentom.\n" +
                "Izbjegavajte bliski kontakt ukoliko je moguće.\n" +
                "Nastavite uzimati bilo koji lijek kako je propisano.\n" +
                "Razgovarajte s liječnikom kako biste utvrdili potrebu za osobnom ili virtualnom pregledom.\n"+
                "Nosite osobnu zaštitnu opremu kada je to potrebno.\n\n"


        goBackFromPoll.setOnClickListener {
            startActivity(Intent(this,Account::class.java))
            finish()
        }

        savePoll.setOnClickListener {

                val selectedDifficultyBreathing = difficultyBreathing.checkedRadioButtonId
                val difficultyBreathingButton = findViewById<RadioButton>(selectedDifficultyBreathing)

                val selectedMobility = mobility.checkedRadioButtonId
                val mobilityButton = findViewById<RadioButton>(selectedMobility)

                val selectedChestPain = chestPain.checkedRadioButtonId
                val chestPainButton = findViewById<RadioButton>(selectedChestPain)

                val selectedLossOfSense = lossOfSense.checkedRadioButtonId
                val lossOfSenseButton = findViewById<RadioButton>(selectedLossOfSense)

                val selectedCough = cough.checkedRadioButtonId
                val coughButton = findViewById<RadioButton>(selectedCough)

                val selectedHeadache = headache.checkedRadioButtonId
                val headacheButton = findViewById<RadioButton>(selectedHeadache)

                val selectedSoreThroat = soreThroat.checkedRadioButtonId
                val soreThroatButton = findViewById<RadioButton>(selectedSoreThroat)

                val selectedTiredness = tiredness.checkedRadioButtonId
                val tirednessButton = findViewById<RadioButton>(selectedTiredness)

                val selectedBodyAches = bodyAches.checkedRadioButtonId
                val bodyAchesButton = findViewById<RadioButton>(selectedBodyAches)

                val selectedRunnyNose = runnyNose.checkedRadioButtonId
                val runnyNoseButton = findViewById<RadioButton>(selectedRunnyNose)

                val selectedNausea = nausea.checkedRadioButtonId
                val nauseaButton =  findViewById<RadioButton>(selectedNausea)

                if(temperature.text.isNullOrEmpty()){
                    Toast.makeText(baseContext, "Molimo unesite temperaturu!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("temperature").setValue(temperature.text.toString().toDouble())

                if(difficultyBreathing.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo unesite imate li poteškoće s disanjem!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("difficultyBreathing").setValue(difficultyBreathingButton.text.toString())

                if(mobility.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo unesite imate li poteškoće s kretanjem!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("mobility").setValue(mobilityButton.text.toString())

                if(chestPain.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo unesite imate li bolove u prsima!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("chestPain").setValue(chestPainButton.text.toString())

                if(lossOfSense.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo unesite jeste li izgubili osjet njuha ili okusa!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("lossOfSense").setValue(lossOfSenseButton.text)

                if(cough.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo označite imate li kašalj!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("cough").setValue(coughButton.text.toString())

                if(headache.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo označite imate li glavobolju!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("headache").setValue(headacheButton.text.toString())

                if(soreThroat.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo označite imate boli li vas grlo!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("soreThroat").setValue(soreThroatButton.text.toString())

                if(tiredness.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo označite osjećate li se iscrpljeno!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("tiredness").setValue(tirednessButton.text.toString())

                if(bodyAches.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo označite osjećate li bolove u tijelu!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("bodyAches").setValue(bodyAchesButton.text.toString())

                if(runnyNose.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo označite je li vam začepljen nos!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("runnyNose").setValue(runnyNoseButton.text.toString())

                if(nausea.checkedRadioButtonId == -1){
                    Toast.makeText(baseContext, "Molimo označite osjećate li mučninu!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                    database.child(currentUserID).child("day$dayCounter").child("nausea").setValue(nauseaButton.text.toString())




            database.child(currentUserID).get().addOnSuccessListener{
                var dayBefore = dayCounter - 1

                if(it.child("Diseases").child("HIV").value.toString() == "true")
                    allAdvices += hivAdvices

                if(it.child("Diseases").child("Cancer").value.toString() == "true" ||
                    it.child("Diseases").child("Transplant").value.toString() == "true" ||
                    it.child("Diseases").child("Weak immune system").value.toString() == "true")
                    allAdvices += weakImmuneSystemAdvices

                if(it.child("Diseases").child("Chronic kidney disease").value.toString() == "true" ||
                    it.child("Diseases").child("Chronic liver disease").value.toString() == "true")
                    allAdvices += chronicKidneyOrLiverDisease

                if(it.child("Diseases").child("Cardiovascular disease").value.toString() == "true")
                    allAdvices += cardiovascularDiseaseAdvices

                if(it.child("Diseases").child("Diabetes").value.toString() == "true")
                    allAdvices += diabetesAdvices

                if(it.child("Diseases").child("Lung disease").value.toString() == "true")
                    allAdvices += lungDiseaseAdvices

                if(it.child("Diseases").child("Lung disease").value.toString() == "true" && it.child("vaccinated").value.toString() == "Ne")
                    allAdvices += "Cijepite se. Budite u tijeku s cjepivima protiv COVID-19 kako biste spriječili pojavu teških bolesti. Ne zaboravite ni na cijepljenje protiv gripe i upale pluća!\n\n"

                if(it.child("pregnancy").value.toString() == "Da")
                    allAdvices += pregnancyAdvices

                if(it.child("pregnancy").value.toString() == "Da" && it.child("vaccinated").value.toString() == "Ne")
                    allAdvices += "Strogo se preporučuje da se cijepite protiv COVID-19 kako biste zaštitili sebe i svoju bebu.\n\n"

                if(it.child("highRiskWorkplace").value.toString() == "Da" && it.child("vaccinated").value.toString() == "Da")
                    allAdvices += "Radeći na mjestu s većom izloženosti COVID-19, preporuka je da se testirate na COVID-19. Ukoliko vam je test negativan, testirajte se opet nakon par dana.\n" +
                            "Prilikom vraćanja na posao, nastavite nositi preporučenu osobnu zaštitnu opremu.\n" +
                            "Nosite masku svaki puta kada ste u blizini drugih, uključujući i vaš dom.\n\n"

                if(it.child("highRiskWorkplace").value.toString() == "Da" && it.child("vaccinated").value.toString() == "Ne")
                    allAdvices += highRiskWorkplaceAdvices

                if(it.child("Diseases").child("Down syndrome").value.toString() == "Da")
                    allAdvices += downSyndromeAdvices

                if(it.child("Diseases").child("Down syndrome").value.toString() == "Da" && it.child("vaccinated").value.toString() == "Ne")
                    allAdvices += "Cijepljenje osoba s Down sindromom je osobito važno!"

                if(it.child("day$dayCounter").child("difficultyBreathing").value.toString() == "Da" ||
                    it.child("day$dayCounter").child("mobility").value.toString() == "Da" ||
                    it.child("day$dayCounter").child("chestPain").value.toString() == "Da")
                    allAdvices += emergencyAdvice

                if(it.child("day$dayCounter").child("lossOfSense").value.toString() == "Da")
                    allAdvices += lossOfSenseAdvice

                if(it.child("day$dayCounter").child("cough").value.toString() == "Da" && it.child("smoking").value.toString() == "Da")
                    allAdvices += "Smanjite ili prestanite pušiti u potpunosti. Za savjet o prestanku pušenja posjetite : https://www.zzjzdnz.hr/zdravlje/pusenje-i-zdravlje/180 ili potražite stručnu pomoć."

                if(it.child("day$dayCounter").child("cough").value.toString() == "Da" && it.child("smoking").value.toString() == "Ne")
                    allAdvices += "Pokušajte izbjegavati boravak u blizini ljudi koji puše kako vam se kašalj ne bi pogoršao." + coughAdvices

                if(it.child("smoking").value.toString() == "Da")
                    allAdvices += smokerAdvices

                if(it.child("day$dayCounter").child("cough").value.toString() == "Da" &&
                    it.child("day$dayBefore").child("cough").value.toString() == "Da")
                    allAdvices += "Ukoliko Vam kašalj ne prestane nakon 10 dana, ili se pogorška, molimo Vas posjetite doktora.\n"

                if(it.child("day$dayCounter").child("headache").value.toString() == "Da")
                    allAdvices += headacheAdvices

                if(it.child("day$dayCounter").child("headache").value.toString() == "Da" &&
                    it.child("day$dayBefore").child("headache").value.toString() == "Da")
                    allAdvices += "Ukoliko Vam nakon više dana glavobolja ne prestane, ne reagira na lijekove ili se pogorša, obratite se liječniku.\n"

                if(it.child("day$dayCounter").child("nausea").value.toString() == "Da")
                    allAdvices += nauseaAdvices

                if(it.child("day$dayCounter").child("runnyNose").value.toString() == "Da")
                    allAdvices += runnyNoseAdvices

                if(it.child("day$dayCounter").child("tiredness").value.toString() == "Da")
                    allAdvices += tirednessAdvices

                if(it.child("day$dayCounter").child("bodyAches").value.toString() == "Da")
                    allAdvices += bodyAchesAdvices

                if(it.child("day$dayCounter").child("soreThroat").value.toString() == "Da")
                    allAdvices += soreThroatAdvices

                if(it.child("day$dayCounter").child("temperature").value.toString().toDouble() > 37.8)
                    allAdvices += temperatureAdvices
                else allAdvices += "Vaša temperatura je normalna.\n\n"

                if(it.child("age").value.toString().toInt() >60)
                    allAdvices += oldAgeAdvices


                database.child(currentUserID).child("day$dayCounter").child("preporuke").setValue(allAdvices)
            }


            database.child(currentUserID).child("dayCounter").setValue(dayCounter+1)

            startActivity(Intent(this,Account::class.java))
            finish()
        }



    }
}